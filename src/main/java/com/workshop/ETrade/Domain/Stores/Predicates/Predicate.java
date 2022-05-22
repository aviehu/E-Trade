package com.workshop.ETrade.Domain.Stores.Predicates;

import com.workshop.ETrade.Domain.Stores.Product;

import java.util.Map;

public interface Predicate {
    boolean shouldApply(Map<Product, Integer> products);
}
