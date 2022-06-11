package com.workshop.ETrade.Domain.Stores.Policies;

import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Map;

public class BasketPolicy implements Policy {

    @DBRef(lazy = true)
    private OperatorComponent operatorComponent;
    private String description;
    private int id;

    public BasketPolicy(int id, String description, OperatorComponent operatorComponent) {
        this.id = id;
        this.description = description;
        this.operatorComponent = operatorComponent;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean canPurchase(Map<Product, Integer> amounts) {
        return operatorComponent.shouldApply(amounts);
    }
}
