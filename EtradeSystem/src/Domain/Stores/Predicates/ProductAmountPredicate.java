package Domain.Stores.Predicates;

import Domain.Stores.Product;

import java.util.Map;

public class ProductAmountPredicate implements Predicate{
    int minAmount;
    int maxAmount;
    String productName;

    public ProductAmountPredicate(String productName, int minAmount, int maxAmount) {
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        this.productName = productName;
    }

    private Product getProduct(Map<Product, int> products){
        for(Product product : products.keySet()) {
            if(product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean shouldApply(Map<Product, int> products) {
        Product product = getProduct(products);
        if(product != null) {
            int amount = products.get(product);
            return amount >= minAmount && amount <= maxAmount;
        }
        return minAmount == 0;
    }
}
