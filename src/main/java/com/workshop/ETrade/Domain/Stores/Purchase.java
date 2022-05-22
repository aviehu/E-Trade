package com.workshop.ETrade.Domain.Stores;

import java.time.LocalDate;
import java.util.Map;

public class Purchase {
    private double price;
    private Map<Product, Integer> prods;
    private String buyer;
    private LocalDate purchaseTime;
    private int purchaseId;

    public Purchase(double price, Map<Product, Integer> prods, String buyer, int purchaseId) {
        this.price = price;
        this.buyer = buyer;
        this.purchaseTime = LocalDate.now();
        this.purchaseId = purchaseId;
        this.prods = prods;
    }

    private String printProducts() {
        String result = "";
        for(Product product : prods.keySet()) {
            result +=  "Product: " + product.getName() + "\tAmount :" + prods.get(product) + "\n";
        }
        return result;
    }

    public String toString() {
        String result = "Purchase " + purchaseId + ":\n";
        result +=       "Buyer: " + buyer + "\n";
        result +=       "Products: \n" + printProducts();
        result +=       "Price: " + price + "\n";
        result +=       "Purchase Time: " + purchaseTime.toString() + "\n\n";
        return result;
    }
}
