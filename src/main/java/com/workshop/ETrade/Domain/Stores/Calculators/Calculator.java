package com.workshop.ETrade.Domain.Stores.Calculators;

import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;
import java.util.Map;

public abstract class Calculator {

    abstract public Map<Product, Double> calcPrices(Map<Product, Double> prices);

    public Map<Product, Double> copyPrices(Map<Product, Double> prices) {
        Map<Product, Double> newPrices = new HashMap<Product, Double>();
        for(Product product : prices.keySet()) {
            newPrices.put(product, prices.get(product));
        }
        return newPrices;
    }

    abstract public String getDiscountOn();

    abstract public double getDiscountPercentage();
}
