package com.workshop.ETrade.Controller.Forms;

import com.workshop.ETrade.Domain.Stores.Purchase;

import java.time.LocalDate;
import java.util.Map;

public class PurchaseHistoryForm {
    public double price;
    public Map<String, Integer> prods;
    public String buyer;
    public LocalDate purchaseTime;
    public int purchaseId;

    public PurchaseHistoryForm(Purchase p) {
        price = p.getPrice();
        prods = p.getProds();
        buyer = p.getBuyer();
        purchaseTime = p.getPurchaseTime();
        purchaseId = p.getPurchaseId();
    }
}
