package com.workshop.ETrade.Domain.Stores.Discounts;

import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Map;

public class MaxDiscount extends Discount{

    @DBRef(lazy = true)
    private Discount discount1;
    @DBRef(lazy = true)
    private Discount discount2;


    public MaxDiscount(int id, Discount discount1, Discount discount2) {
        super(id);
        this.discount1 = discount1;
        this.discount2 = discount2;
    }

    private double totalPrice(Map<Product, Double> prices){
        double total = 0;
        for(Product product : prices.keySet()) {
            total += prices.get(product);
        }
        return total;
    }

    @Override
    public Map<Product, Double> calculatePrices(Map<Product, Double> prices , Map<Product, Integer> amounts) {
        Map<Product, Double> dis1Prices = discount1.calculatePrices(prices, amounts);
        Map<Product, Double> dis2Prices = discount2.calculatePrices(prices, amounts);
        if(totalPrice(dis1Prices) >= totalPrice(dis2Prices)) {
            return dis1Prices;
        }
        return dis2Prices;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> amounts) {
        return discount1.shouldApply(amounts) || discount2.shouldApply(amounts);
    }
}
