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

    public String purchaseCart(CreditCard card,SupplyAddress address,String userName){
        int cardFrom = card.getCardNumber();
        LocalTime expDate = card.getExpDate();
        int cvv = card.getCvv();
        boolean payment = extSystems.canPay(cardFrom, expDate, cvv, getTotalPrice());
        //payment
        if(payment) {//can charge payment
            String ret = canPurchase();
            if (ret == null) { //can purchase
                if (!extSystems.supply(this, address))
                    return "Failed to supply your shopping cart\n";

                for (StoreBasket b : baskets) {
                    extSystems.pay(cardFrom, expDate, cvv, b.getTotalPrice(), b.getStore().getCard());
                    if (!b.purchase(userName))
                        return "Failed to purchase product from " + b.getStoreName() + "\n";
                }
                return null;
            }
            else
                return ret;
        }else
            return "Failed to charge your credit card\n";
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
