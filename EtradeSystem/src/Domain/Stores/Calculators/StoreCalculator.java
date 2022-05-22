package Domain.Stores.Calculators;

import Domain.Stores.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StoreCalculator extends Calculator{
    private double discountPercent;

    public StoreCalculator(double discountPercent){
        this.discountPercent = discountPercent;
    }

    @Override
    public Map<Product, Double> calcPrices(Map<Product, Double> prices) {
        Map<Product, Double> newPrices = copyPrices(prices);
        for (Product product : prices.keySet()) {
            newPrices.computeIfPresent(product, (K, V) -> V = V * discountPercent);
        }
        return newPrices;
    }

    private int getTotalAmount(Map<Product, Integer> amounts) {
        int result = 0;
        for(Product product : amounts.keySet()) {
            result += amounts.get(product);
        }
        return result;
    }
}
