package Domain.Users.Users;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<StoreBasket> baskets;

    public ShoppingCart() {
        this.baskets = new ArrayList<>();
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
        return price;
    }
    public List<StoreBasket> purchase(){
        List<StoreBasket> bas = new ArrayList<>();
        for(StoreBasket b : baskets){
            if(!b.purchase())
                return bas = null;
            bas.add(b);
        }
        return bas;
    }
    public StoreBasket getBasketByStore(Store s){
        for(StoreBasket b : baskets){
            if (b.getStore().equals(s))
                return b;
        }
        return null;
    }
    public void addProd(Store s,int quantity,String prodName){
        StoreBasket b = getBasketByStore(s);
        if(b == null){
            b = new StoreBasket(s);
            b.addProd(quantity,prodName);
            baskets.add(b);
        }
        else
            b.addProd(quantity,prodName);
    }
    public void removeProd(Store s,int quantity,String prodName){
        StoreBasket b = getBasketByStore(s);
        if(b == null){
            //exception
        }
        else
            b.removeProd(quantity,prodName);
    }
}
