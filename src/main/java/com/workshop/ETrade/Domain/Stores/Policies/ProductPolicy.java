package com.workshop.ETrade.Domain.Stores.Policies;

import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Map;

public class ProductPolicy implements Policy{

    private String productName;
    @DBRef(lazy = true)
    private OperatorComponent operatorComponent;
    private int id;
    private String description;

    public ProductPolicy(int id, String description, String productName, OperatorComponent operatorComponent) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.operatorComponent = operatorComponent;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean canPurchase(Map<Product, Integer> amounts) {
        Product product = null;
        for(Product p : amounts.keySet()){
            if(p.getName().equals(productName)) {
                product = p;
                break;
            }
        }
        if(product == null) {
            return true;
        }
        return operatorComponent.shouldApply(amounts);
    }
}
