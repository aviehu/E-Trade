package com.workshop.ETrade.Domain.Users.Users;

import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    private ExtSysController extSystems = null;
    private List<StoreBasket> baskets;
    private int discount;

    public ShoppingCart(int discount) {
        this.discount = discount;
        this.baskets = new ArrayList<>();
        extSystems = ExtSysController.getInstance();
    }

    public String purchaseCart(CreditCard card,SupplyAddress address,String userName){
        int cardFrom = card.getCardNumber();
        int month = card.getMonth();
        int year = card.getYear();
        int cvv = card.getCvv();
        int id = card.getId();
        String holderName = card.getHolderName();
        int payTransactionId = extSystems.pay(cardFrom, month,year,holderName, cvv,id);
        //payment
        if(payTransactionId != -1) {//can charge payment
            String ret = canPurchase();
            if (ret == null) { //can purchase
                int supTransactionId =extSystems.supply(userName, address);
                if (supTransactionId == -1) {
                    extSystems.cancelPayment(payTransactionId);
                    extSystems.cancelSup(supTransactionId);
                    return "Failed to supply your shopping cart\n";
                }

                for (StoreBasket b : baskets) {
                    //extSystems.pay(cardFrom, month,year, cvv, id);
                    if (!b.purchase(userName)){
                        extSystems.cancelPayment(payTransactionId);
                        extSystems.cancelSup(supTransactionId);
                        return "Failed to purchase product from " + b.getStoreName() + "\n";
                    }

                }
                return null;
            }
            else
                return ret;
        }else
            return "Failed to charge your credit card\n";
    }

    public HashMap<String,Integer> displayCart(){
        HashMap<String,Integer> items = new HashMap<>();
        for(StoreBasket basket : baskets){
            HashMap<String,Integer> basketProds = basket.displayBasket();
            for(String prodName : basketProds.keySet()) {
                items.put(prodName, basketProds.get(prodName));
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
            b = new StoreBasket(s);
            ret = b.addProd(quantity,prodName);
            baskets.add(b);
        }
        else
            ret = b.addProd(quantity,prodName);
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
        for(StoreBasket b : baskets)
            b.finishPurchase();
        baskets.clear();
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
