package com.workshop.ETrade.Domain.Stores.Predicates;

import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.Map;

public class OperatorLeaf extends OperatorComponent{

    @DBRef(lazy = true)
    private List<Predicate> predicates;

    public OperatorLeaf(String type , List<Predicate> predicates) {
        super(type);
        this.predicates = predicates;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> amounts) {
        boolean ans = true;
        if(getType().equals("and")) {
            for(Predicate predicate : predicates) {
                ans = ans && predicate.shouldApply(amounts);
            }
        }
        else {
            ans = false;
            for(Predicate predicate : predicates) {
                ans = ans || predicate.shouldApply(amounts);
            }
        }
        return ans;
    }
}
