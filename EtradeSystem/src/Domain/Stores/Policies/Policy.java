package Domain.Stores.Policies;

import Domain.Stores.Product;

import java.util.Map;

public interface Policy {

    public int getId();

    public boolean canPurchase(Map<Product, Integer> amounts);
}
