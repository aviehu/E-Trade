package Domain.Stores.Calculators;

import Domain.Stores.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProductCalculator extends Calculator{
    private String productName;
    private double discountPercent;

    public ProductCalculator(String productName, double discountPercent){
        this.productName = productName;
        this.discountPercent = discountPercent;
    }

    private Product getProduct(Map<Product, double> prices) {
        for(Product product : prices.keySet()) {
            if(product.getName().equals(productName)){
                return product;
            }
        }
        return null;
    }

    @Override
    public Map<Product, double> calcPrices(Map<Product, double> prices) {
        Map<Product, double> newPrices = copyPrices(prices);
        Product product = getProduct(newPrices);
        if(product != null) {
            newPrices.computeIfPresent(product, (K, V) -> V = V * discountPercent);
        }
        return newPrices;
    }
}
