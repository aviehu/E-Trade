package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.Purchase;

import java.time.LocalDate;
import java.util.Map;

public class PurchaseDTO {

    public double price;
    public Map<String, Integer> prods;
    public String buyer;
    public LocalDate purchaseTime;
    public int purchaseId;

    public PurchaseDTO(double price, Map<String, Integer> prods, String buyer, LocalDate purchaseTime, int purchaseId) {
        this.price = price;
        this.prods = prods;
        this.buyer = buyer;
        this.purchaseTime = purchaseTime;
        this.purchaseId = purchaseId;
    }

    public PurchaseDTO(Purchase purchase) {
        price = purchase.getPrice();
        prods = purchase.getProds();
        buyer = purchase.getBuyer();
        purchaseTime = purchase.getPurchaseTime();
        purchaseId = purchase.getPurchaseId();

    }

}
