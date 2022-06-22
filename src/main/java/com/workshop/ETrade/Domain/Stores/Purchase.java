package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.Persistance.Stores.PurchaseDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.Map;

public class Purchase {
    private double price;
    private Map<String, Integer> prods;
    private String buyer;
    private LocalDate purchaseTime;
    private int purchaseId;

    public Purchase(double price, Map<String, Integer> prods, String buyer, int purchaseId) {
        this.price = price;
        this.buyer = buyer;
        this.purchaseTime = LocalDate.now();
        this.purchaseId = purchaseId;
        this.prods = prods;
    }

    public Purchase(double price, Map<String, Integer> prods, String buyer, int purchaseId, LocalDate purchaseTime) {
        this.price = price;
        this.buyer = buyer;
        this.purchaseTime = purchaseTime;
        this.purchaseId = purchaseId;
        this.prods = prods;
    }

    public Purchase(PurchaseDTO pur) {
        price = pur.price;
        buyer = pur.buyer;
        purchaseTime = pur.purchaseTime;
        purchaseId = pur.purchaseId;
        prods = pur.prods;
    }

    public double getPrice() {
        return price;
    }

    public Map<String, Integer> getProds() {
        return prods;
    }

    public String getBuyer() {
        return buyer;
    }

    public LocalDate getPurchaseTime() {
        return purchaseTime;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public String toString(){
        String ans = "purchase ID: " + this.purchaseId +"\npurchased by: " + this.buyer +"\npurchased: ";
        for (String prod : prods.keySet()){
            ans += prods.get(prod).toString() + " " + prod + ", ";
        }
        return ans;
    }
}
