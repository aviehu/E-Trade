package com.workshop.ETrade.Domain.Stores.Calculators;

import com.workshop.ETrade.Domain.Stores.Product;

import java.util.Map;

public class StoreCalculator extends Calculator{
    private int discountPercent;

    public StoreCalculator(int discountPercent){
        this.discountPercent = discountPercent;
    }

    @Override
    public Map<Product, Double> calcPrices(Map<Product, Double> prices) {
        Map<Product, Double> newPrices = copyPrices(prices);
        for (Product product : prices.keySet()) {
            newPrices.computeIfPresent(product, (K, V) -> V = V * discountPercent);
        }
        return newPrices;
    }

    @Override
    public String getDiscountOn() {
        return "";
    }

    @Override
    public int getDiscountPercentage() {
        return discountPercent;
    }

    private int getTotalAmount(Map<Product, Integer> amounts) {
        int result = 0;
        for(Product product : amounts.keySet()) {
            result += amounts.get(product);
        }
        return result;
    }
}
