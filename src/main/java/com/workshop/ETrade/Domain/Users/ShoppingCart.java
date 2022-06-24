package com.workshop.ETrade.Domain.Users;

import com.workshop.ETrade.AllRepos;
import com.workshop.ETrade.Domain.Pair;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;
import com.workshop.ETrade.Persistance.Users.StoreBasketDTO;
import com.workshop.ETrade.Service.ResultPackge.Result;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {

    private static Integer basketId = 0;

    private ExtSysController extSystems = null;

    private List<StoreBasket> baskets;
    private int discount;
    private String userName;
    public ShoppingCart(int discount, String userName) {
        this.userName = userName;
        this.discount = discount;
        this.baskets = new ArrayList<>();
        extSystems = ExtSysController.getInstance();
    }


    public Pair<Integer, Integer> payAndSupply(String cardForm, int month, int yead, String holderName, int cvv, int id, String userName, SupplyAddress address) {
        Answer payAns = new Answer();
        Answer supAns = new Answer();
        Thread payT = new PaymentThread(cardForm, month, yead,holderName,cvv,id,payAns);
        Thread supT = new SupplyThread(userName, address, supAns);
        try {
            payT.start();
            supT.start();
            payT.join();
            supT.join();

        }catch (Exception e) {

        }
        return new Pair<>(payAns.ans, supAns.ans);
    }
    public Result<List<String>> purchaseCart(CreditCard card,SupplyAddress address,String userName){
        List<String> storesToNotify = new ArrayList<>();
        String cardFrom = card.getCardNumber();
        int month = card.getMonth();
        int year = card.getYear();
        int cvv = card.getCvv();
        int id = card.getId();
        String holderName = card.getHolderName();
//        int payTransactionId = extSystems.pay(cardFrom, month,year,holderName, cvv,id);
        Pair<Integer, Integer> paySupAns = payAndSupply(cardFrom, month, year, holderName, cvv, id, userName, address);
        int payTransactionId = paySupAns.first;
        int supTransactionId = paySupAns.second;
        //payment
        if(payTransactionId != -1) {//can charge payment
            String ret = canPurchase();
            if (ret == null) { //can purchase
//                int supTransactionId =extSystems.supply(userName, address);
                if (supTransactionId == -1) {
                    extSystems.cancelPayment(payTransactionId);
                    //extSystems.cancelSup(supTransactionId);
                    return new Result<List<String>>(null, "Failed! can't supply your shopping cart\n");
                }

                for (StoreBasket b : baskets) {
                    //extSystems.pay(cardFrom, month,year, cvv, id);
                    if (!b.purchase(userName)){
                        extSystems.cancelPayment(payTransactionId);
                        extSystems.cancelSup(supTransactionId);
                        return new Result<List<String>>(null, "Failed! can't purchase product from " + b.getStoreName() + "\n");
                    }
                    storesToNotify.add(b.getStoreName());
                }
            }
            else{
                return new Result<>(null, "Failed! can't purchase your cart");
            }
        }else{

            return new Result<>(null, "Failed! can't charge your credit card\n");
           // return new Result<>(null, "Failed! can't charge your credit card\n");
        }
        return new Result<>(storesToNotify, null);
    }

    public HashMap<String, Pair<Integer,String>> displayCart(){
        HashMap<String,Pair<Integer,String>> items = new HashMap<>();
        for(StoreBasket basket : baskets){
            HashMap<String,Integer> basketProds = basket.displayBasket();
            for(String prodName : basketProds.keySet()) {
                items.put(prodName, new Pair<>(basketProds.get(prodName), basket.getStoreName()));
            }
        }
        return items;
    }
    public Double getTotalPrice(){
        Double price = 0.0;
        for(StoreBasket b : baskets){
            price += b.getTotalPrice();
        }
        price = price - (price*(discount/100));
        return price;
    }
    public String canPurchase(){
        List<StoreBasket> bas = new ArrayList<>();
        for(StoreBasket b : baskets){
            String ret = b.canPurchase();
            if(ret != null)
                return ret;
        }
        return null;
    }
    public StoreBasket getBasketByStore(Store s){
        for(StoreBasket b : baskets){
            if (b.getStore().equals(s))
                return b;
        }
        return null;
    }
    public String addProd(Store s,int quantity,String prodName){
        StoreBasket b = getBasketByStore(s);
        String ret;
        if(b == null){
            b = new StoreBasket(s, userName);
            ret = b.addProd(quantity,prodName);
            baskets.add(b);
            AllRepos.getStoreBasketRepo().save(new StoreBasketDTO(b));
        }
        else {
            ret = b.addProd(quantity, prodName);
            StoreBasketDTO dto = AllRepos.getStoreBasketRepo().findByStoreAndUser(b.getStore().getName(), b.getUserName());
            if(dto != null) {
                AllRepos.getStoreBasketRepo().save(new StoreBasketDTO(b, dto.getId()));
            }
        }
        if(ret.contains("successfully"))
            return ret+displayCart();
        else
            return ret;
    }
    public String removeProd(Store s,int quantity,String prodName){
        StoreBasket b = getBasketByStore(s);
        if(b == null){
            return "Can't remove " + prodName + ", your basket is empty\n";
        }
        else {
            String ret = b.removeProd(quantity,prodName);
            if(b.getProds().isEmpty()) {
                StoreBasketDTO dto = AllRepos.getStoreBasketRepo().findByStoreAndUser(b.getStore().getName(), b.getUserName());
                if(dto != null) {
                    AllRepos.getStoreBasketRepo().delete(new StoreBasketDTO(b, dto.getId()));
                }
            }
            else {
                StoreBasketDTO dto = AllRepos.getStoreBasketRepo().findByStoreAndUser(b.getStore().getName(), b.getUserName());
                if(dto != null) {

                    AllRepos.getStoreBasketRepo().save(new StoreBasketDTO(b, dto.getId()));
                }
            }
            if (ret == null)
                return "Successfully added "+quantity+" "+prodName+".\n"+displayCart();
            else
                return ret;
        }
    }

    public List<StoreBasket> getBaskets() {
        return baskets;
    }
    public void finishPurchase(){
        for(StoreBasket b : baskets) {
            b.finishPurchase();
            StoreBasketDTO dto = AllRepos.getStoreBasketRepo().findByStoreAndUser(b.getStore().getName(), b.getUserName());
            if(dto != null) {
                AllRepos.getStoreBasketRepo().delete(new StoreBasketDTO(b, dto.getId()));
            }
        }
        baskets.clear();

    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void addBasket(StoreBasket storeBasket) {
        baskets.add(storeBasket);
    }


}
