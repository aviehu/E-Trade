package com.workshop.ETrade.Domain.Stores.Predicates;

import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.Map;

public class OperatorComposite extends OperatorComponent {

    @DBRef(lazy = true)
    private List<OperatorComponent> components;

    public OperatorComposite(String type, List<OperatorComponent> components){
        super(type);
        this.components = components;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> amounts) {
        boolean ans = true;
        if(getType().equals("and")) {
            for(OperatorComponent operatorComponent : components) {
                ans = ans && operatorComponent.shouldApply(amounts);
            }
        } else {
            ans = false;
            for(OperatorComponent operatorComponent : components) {
                ans = ans || operatorComponent.shouldApply(amounts);
            }
        }
        return ans;
    }
}
