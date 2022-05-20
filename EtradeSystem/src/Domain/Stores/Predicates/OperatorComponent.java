package Domain.Stores.Predicates;

import Domain.Stores.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class OperatorComponent {

    private String type;

    public OperatorComponent(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    abstract public boolean shouldApply(Map<Product, Integer> amounts);

}
