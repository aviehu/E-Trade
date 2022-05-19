package Domain.Stores.Calculators;

import Domain.Stores.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Calculator {

    abstract public Map<Product, Double> calcPrices(Map<Product, Double> prices);

    public Map<Product, Double> copyPrices(Map<Product, Double> prices) {
        Map<Product, Double> newPrices = new HashMap<Product, Double>();
        for(Product product : prices.keySet()) {
            newPrices.put(product, prices.get(product));
        }
        return newPrices;
    }

}
