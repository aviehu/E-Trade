package Domain.Stores.Predicates;

import Domain.Stores.Product;

import java.util.Map;

public interface Predicate {
    boolean shouldApply(Map<Product, Integer> products);
}
