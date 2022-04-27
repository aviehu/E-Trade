package Domain.Users.Users;

import Domain.Stores.Store;
import Domain.Users.ExternalService.ExtSysController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Domain.Users.Users.UserController.memberDiscount;

public class ShoppingCart {
    private ExtSysController extSystems = null;
    private List<StoreBasket> baskets;
    private int discount;

    public ShoppingCart(int discount) {
        this.discount = discount;
        this.baskets = new ArrayList<>();
        extSystems = ExtSysController.getInstance();
    }

    public boolean purchaseCart(CreditCard card,SupplyAddress address,String userName){
        int cardFrom = card.getCardNumber();
        LocalTime expDate = card.getExpDate();
        int cvv = card.getCvv();
        boolean payment = extSystems.canPay(cardFrom, expDate, cvv, getTotalPrice());
        //payment
        if(payment && canPurchase()) {
            if(!extSystems.supply(this,address))
                return false;

            for(StoreBasket b : baskets){
                extSystems.pay(cardFrom,expDate,cvv,b.getTotalPrice(),b.getStore().getCard());
                b.purchase(userName);
                }
            return true;
            }
        return false;
    }

    public String displayCart(){
        String display = "";
        for(StoreBasket basket : baskets){
            display += basket.displayBasket();
        }
        return display;
    }
    public int getTotalPrice(){
        int price = 0;
        for(StoreBasket b : baskets){
            price += b.getTotalPrice();
        }
        price = price - ((price*discount)/100);
        return price;
    }
    public boolean canPurchase(){
        List<StoreBasket> bas = new ArrayList<>();
        for(StoreBasket b : baskets){
            if(!b.canPurchase())
                return false;
        }
        return true;
    }
    public StoreBasket getBasketByStore(Store s){
        for(StoreBasket b : baskets){
            if (b.getStore().equals(s))
                return b;
        }
        return null;
    }
    public boolean addProd(Store s,int quantity,String prodName){
        StoreBasket b = getBasketByStore(s);
        if(b == null){
            b = new StoreBasket(s);
            b.addProd(quantity,prodName);
            baskets.add(b);
        }
        else
            b.addProd(quantity,prodName);
        return true;
    }
    public boolean removeProd(Store s,int quantity,String prodName){
        StoreBasket b = getBasketByStore(s);
        if(b == null){
            return false;
        }
        else
            return b.removeProd(quantity,prodName);
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
