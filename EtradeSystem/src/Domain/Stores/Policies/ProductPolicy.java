package Domain.Stores.Policies;

import Domain.Stores.Predicates.OperatorComponent;
import Domain.Stores.Product;

import java.util.Map;

public class ProductPolicy implements Policy{

    private String productName;
    private OperatorComponent operatorComponent;
    private int id;
    private String description;

    public ProductPolicy(int id, String description, String productName, OperatorComponent operatorComponent) {
        this.id = id;
        this.description = description;
        this.productName = productName;
        this.operatorComponent = operatorComponent;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean canPurchase(Map<Product, int> amounts) {
        Product product = null;
        for(Product p : amounts.keySet()){
            if(p.getName().equals(productName)) {
                product = p;
                break;
            }
        }
        if(product == null) {
            return true;
        }
        return operatorComponent.shouldApply(amounts);
    }
}
