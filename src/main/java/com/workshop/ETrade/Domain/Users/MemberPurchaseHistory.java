package com.workshop.ETrade.Domain.Users;

import java.util.ArrayList;
import java.util.List;

public class MemberPurchaseHistory {
    private List<StoreBasket>   basketsPurchased;

    public MemberPurchaseHistory() {
        this.basketsPurchased = new ArrayList<>();
    }

    public String displayHistory(){
        String display = "";
        for(StoreBasket b : basketsPurchased){
            display += b.displayBasket();
        }
        return display;
    }
    public void addToHistory(StoreBasket b){
        basketsPurchased.add(b);
    }

    public boolean isProdPurchased(String prodName){
        for(StoreBasket b : basketsPurchased){
            if(b.isProdExisit(prodName))
                return true;
        }
        return false;
    }
    public boolean isPurchasedFromStore(String storeName){
        for(StoreBasket b : basketsPurchased){
            if(b.getStoreName() == storeName)
                return true;
        }
        return false;
    }
}
