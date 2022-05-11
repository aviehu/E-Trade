package Domain.Stores.Calculators;

import Domain.Stores.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Calculator {

    abstract public Map<Product, double> calcPrices(Map<Product, double> prices);

    public Map<Product, double> copyPrices(Map<Product, double> prices) {
        Map<Product, double> newPrices = new HashMap<Product, double>();
        for(Product product : prices.keySet()) {
            newPrices.put(product, prices.get(product));
        }
        return newPrices;
    }

}
