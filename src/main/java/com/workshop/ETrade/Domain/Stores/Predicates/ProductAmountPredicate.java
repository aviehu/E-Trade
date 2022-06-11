package com.workshop.ETrade.Domain.Stores.Predicates;

import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Persistance.Stores.PredicateDTO;

import java.util.Map;

public class ProductAmountPredicate implements Predicate{
    int minAmount;
    int maxAmount;
    String productName;

    public ProductAmountPredicate(String productName, int minAmount, int maxAmount) {
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        this.productName = productName;
    }

    private Product getProduct(Map<Product, Integer> products){
        for(Product product : products.keySet()) {
            if(product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> products) {
        Product product = getProduct(products);
        if(product != null) {
            int amount = products.get(product);
            return amount >= minAmount && amount <= maxAmount;
        }
        return minAmount == 0;
    }

    @Override
    public PredicateDTO init() {
        return new PredicateDTO(this);
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public String getProductName() {
        return productName;
    }
}
