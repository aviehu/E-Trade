package com.workshop.ETrade.Domain.Stores.Predicates;

import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Persistance.Stores.DiscountDTO;
import com.workshop.ETrade.Persistance.Stores.PredicateDTO;

import java.util.Map;

public interface Predicate {
    boolean shouldApply(Map<Product, Integer> products);

    PredicateDTO init();
}
