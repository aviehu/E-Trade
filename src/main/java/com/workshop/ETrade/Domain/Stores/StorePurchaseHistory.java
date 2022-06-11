package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.Persistance.Stores.PurchaseDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StorePurchaseHistory {
    @DBRef(lazy = true)
    private List<Purchase> purchases;
    private int purchaseId;

    public StorePurchaseHistory() {
        purchases = Collections.synchronizedList(new ArrayList<>());
        purchaseId = 1;
    }

    public StorePurchaseHistory(List<PurchaseDTO> purs) {
        int maxP = 1;
        purchases = Collections.synchronizedList(new ArrayList<>());
        for(PurchaseDTO p : purs) {
            purchases.add(new Purchase(p));
            if(p.purchaseId > maxP) {
                maxP = p.purchaseId;
            }
        }
        purchaseId = maxP + 1;
    }

    public boolean addPurchase(double totalPrice, Map<String, Integer> products, String buyer) {
        Purchase purchase = new Purchase(totalPrice, products, buyer, purchaseId);
        purchaseId++;
        return true;
    }

    public String getHistory() {
        String result = "";
        for(Purchase purchase : purchases) {
            result = result + purchase.toString();
        }
        return result;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }
}
