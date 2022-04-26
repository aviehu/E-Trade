package Domain.Stores;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PolicyManager {
    private int discountId;
    private int purchasePolicyId;
    private Discount founderDiscount;
    private PurchasePolicy founderPurchasePolicy;
    private List<Discount> discounts;
    private List<PurchasePolicy> purchasePolicies;

    public PolicyManager() {
        discountId = 1;
        purchasePolicyId = 1;
        founderDiscount = new Discount();
        founderPurchasePolicy = new PurchasePolicy();
        discounts = new LinkedList<>();
        purchasePolicies = new LinkedList<>();
    }

    public boolean addDiscount(List<Product> products, int discountPercent, String desc) {
        discounts.add(new Discount(products, discountPercent, desc, discountId));
        discountId++;
        return true;
    }

    public double getTotalPrice(Map<Product, Integer> items) {
        double totalAmount = 0;
        Set<Product> products = items.keySet();
        for(Product product : products) {
            double discountPer = 100;
            discountPer = founderDiscount.apply(product, items.get(product), discountPer);
            for(Discount discount : discounts) {
                discountPer = discount.apply(product, items.get(product), discountPer);
            }
            totalAmount += product.getPrice() * discountPer / 100;
        }
        return totalAmount;
    }


    public boolean canPurchase(Map<String, Integer> prods) {
        boolean result = founderPurchasePolicy.canPurchase(prods);
        for(PurchasePolicy purchasePolicy : purchasePolicies) {
            result = result && purchasePolicy.canPurchase(prods);
        }
        return result;
    }

    public double getProductPrice(Product product, Map<String, Integer> prods) {
        return 0;
    }
}
