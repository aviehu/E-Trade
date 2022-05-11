package Domain.Stores.Policies;

import Domain.Stores.Predicates.OperatorComponent;
import Domain.Stores.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CategoryPolicy implements Policy {

    private OperatorComponent operatorComponent;
    private String categoryName;
    private int id;
    private String description;

    public CategoryPolicy(int id, String description, String categoryName, OperatorComponent operatorComponent) {
        this.id = id;
        this.description = description;
        this.categoryName = categoryName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean canPurchase(Map<Product, int> amounts) {
        List<Product> products = new LinkedList<>();
        for(Product product : amounts.keySet()) {
            if(product.getCategory().equals(categoryName)) {
                products.add(product);
            }
        }
        if(products.size() < 1) {
            return true;
        }
        return operatorComponent.shouldApply(amounts);
    }
}
