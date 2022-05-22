package com.workshop.ETrade.Domain.Stores.Calculators;

import com.workshop.ETrade.Domain.Stores.Product;

import java.util.Map;

public class ProductCalculator extends Calculator{
    private String productName;
    private double discountPercent;

    public ProductCalculator(String productName, double discountPercent){
        this.productName = productName;
        this.discountPercent = discountPercent;
    }

    private Product getProduct(Map<Product, Double> prices) {
        for(Product product : prices.keySet()) {
            if(product.getName().equals(productName)){
                return product;
            }
        }
        return null;
    }

    @Override
    public Map<Product, Double> calcPrices(Map<Product, Double> prices) {
        Map<Product, Double> newPrices = copyPrices(prices);
        Product product = getProduct(newPrices);
        if(product != null) {
            newPrices.computeIfPresent(product, (K, V) -> V = V * discountPercent);
        }
        return newPrices;
    }
}
