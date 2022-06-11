package com.workshop.ETrade.Domain.Stores.Policies;

import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import com.workshop.ETrade.Domain.Stores.Product;

import java.util.List;
import java.util.Map;

public interface Policy {

    public int getId();

    public boolean canPurchase(Map<Product, Integer> amounts);

    List<Predicate> getPredicates();

    String getPolicyOn();

    String getDescription();

    String getOpType();


    PolicyType getType();
}
