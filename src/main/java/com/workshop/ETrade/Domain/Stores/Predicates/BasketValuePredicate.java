package com.workshop.ETrade.Domain.Stores.Predicates;

import com.workshop.ETrade.Domain.Stores.Product;

import java.util.Map;

public class BasketValuePredicate implements Predicate{
    double minValue;
    double maxValue;

    public BasketValuePredicate(double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> products) {
        double total = 0;
        for(Product product : products.keySet()) {
            total += product.getPrice();
        }
        return total >= minValue && total <= maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }
}
