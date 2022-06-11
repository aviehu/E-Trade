package com.workshop.ETrade.Domain.Stores.Calculators;

import com.workshop.ETrade.Domain.Stores.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CategoryCalculator extends Calculator {
    private String category;
    private double discountPercent;

    public CategoryCalculator(String category, double discountPercent) {
        this.category = category;
        this.discountPercent = discountPercent;
    }

    private List<Product> getProductsFromPrices(Map<Product, Double> prices) {
        List<Product> products = new LinkedList<>();
        for(Product product : prices.keySet()) {
            if(product.getCategory().equals(category)) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Map<Product, Double> calcPrices(Map<Product, Double> prices) {
        Map<Product, Double> newPrices = copyPrices(prices);
        List<Product> products = getProductsFromPrices(newPrices);
        for(Product product : products) {
            newPrices.computeIfPresent(product, (K, V) -> V = V * discountPercent);
        }
        return newPrices;
    }

    @Override
    public String getDiscountOn() {
        return category;
    }

    @Override
    public double getDiscountPercentage() {
        return discountPercent;
    }

}
