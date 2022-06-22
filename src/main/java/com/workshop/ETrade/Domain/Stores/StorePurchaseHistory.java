package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.Persistance.Stores.PurchaseDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.*;

public class StorePurchaseHistory {

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
        Map<String, Integer> prods = new HashMap<>();
        for(String key : products.keySet()) {
            prods.put(key, products.get(key));
        }
        Purchase purchase = new Purchase(totalPrice, prods, buyer, purchaseId);
        purchases.add(purchase);
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
