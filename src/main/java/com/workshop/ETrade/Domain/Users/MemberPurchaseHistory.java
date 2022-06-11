package com.workshop.ETrade.Domain.Users;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class MemberPurchaseHistory {
    private List<StoreBasket> basketsPurchased;

    public MemberPurchaseHistory() {
        this.basketsPurchased = new ArrayList<>();
    }

    public String displayHistory(){
        StringBuilder display = new StringBuilder();
        for(StoreBasket b : basketsPurchased){
            display.append(b.displayBasket());
        }
        return display.toString();
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
            if(b.getStoreName().equals(storeName))
                return true;
        }
        return false;
    }
}
