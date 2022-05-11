package Domain.Stores.Calculators;

import Domain.Stores.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CategoryCalculator extends Calculator {
    private String category;
    private double discountPercent;

    public CategoryCalculator(String category, double discountPercent) {
        this.category = category;
        this.discountPercent = discountPercent;
    }

    private List<Product> getProductsFromPrices(Map<Product, double> prices) {
        List<Product> products = new LinkedList<>();
        for(Product product : prices.keySet()) {
            if(product.getCategory().equals(category)) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Map<Product, double> calcPrices(Map<Product, double> prices) {
        Map<Product, double> newPrices = copyPrices(prices);
        List<Product> products = getProductsFromPrices(newPrices);
        for(Product product : products) {
            newPrices.computeIfPresent(product, (K, V) -> V = V * discountPercent);
        }
        return newPrices;
    }

}
