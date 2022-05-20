package Domain.Stores;

import Domain.Stores.Discounts.*;
import Domain.Stores.Policies.*;
import Domain.Stores.Predicates.OperatorComponent;

import java.util.*;

public class PolicyManager {

    private int discountId;
    private int policyId;
    private List<Discount> discounts;
    private List<Policy> policies;

    public PolicyManager() {
        discountId = 1;
        policyId = 1;
        discounts = new LinkedList<>();
        policies = new LinkedList<>();
    }

    private Discount getDiscountById(int id) {
        for(Discount discount : discounts) {
            if(discount.getId() == id) {
                return discount;
            }
        }
        return null;
    }

    public int addPolicy(String policyOn, String description, PolicyType type, OperatorComponent operatorComponent) {
        switch (type) {
            case BASKET -> policies.add(new BasketPolicy(policyId, description, operatorComponent));
            case PRODUCT -> policies.add(new ProductPolicy(policyId, description, policyOn, operatorComponent));
            case CATEGORY -> policies.add(new CategoryPolicy(policyId, description, policyOn, operatorComponent));
        }
        policyId ++;
        return policyId - 1;
    }

    public int addDiscount(String discountOn, int discountPercentage, String description, DiscountType type) {
        discounts.add(new Discount(discountId, discountOn, discountPercentage, description, type));
        discountId++;
        return discountId - 1;
    }

    public int addPredicateDiscount(String discountOn, int discountPercentage, String description, DiscountType type, OperatorComponent operatorComponent) {
        discounts.add(new PredicateDiscount(discountId, discountOn, discountPercentage, description, type, operatorComponent));
        discountId++;
        return discountId - 1;
    }

    public boolean makeXorDiscount(int discount1Id, int discount2Id) {
        Discount dis1 = getDiscountById(discount1Id);
        Discount dis2 = getDiscountById(discount2Id);
        if(dis1 == null || dis2 == null) {
            return false;
        }
        discounts.add(new XorDiscount(discountId, dis1, dis2));
        discountId ++;
        discounts.remove(dis1);
        discounts.remove(dis2);
        return true;
    }

    public boolean makeMaxDiscount(int discount1Id, int discount2Id){
        Discount dis1 = getDiscountById(discount1Id);
        Discount dis2 = getDiscountById(discount2Id);
        if(dis1 == null || dis2 == null) {
            return false;
        }
        discounts.add(new MaxDiscount(discountId, dis1, dis2));
        discountId ++;
        discounts.remove(dis1);
        discounts.remove(dis2);
        return true;
    }

    private Map<Product, Double> getOriginalPrices(Map<Product, Integer> amounts) {
        Map<Product, Double> prices = new HashMap<Product, Double>();
        Set<Product> products = amounts.keySet();
        for(Product product : products) {
            prices.put(product, product.getPrice() * amounts.get(product));
        }
        return prices;
    }

    private Map<Product, Double> applyDiscounts(Map<Product, Double> prices, Map<Product, Integer> amounts) {
        for(Discount discount : discounts) {
            prices = discount.calculatePrices(prices, amounts);
        }
        return prices;
    }

    public double getTotalPrice(Map<Product, Integer> amounts) {
        double totalAmount = 0;
        Map<Product, Double> prices = getOriginalPrices(amounts);
        prices = applyDiscounts(prices, amounts);
        for(Product product : prices.keySet()) {
            totalAmount += prices.get(product);
        }
        return totalAmount;
    }

    public boolean canPurchase(Map<Product, Integer> amounts) {
        boolean result = true;
        for(Policy policy : policies) {
            result = result && policy.canPurchase(amounts);
        }
        return result;
    }

    public boolean removeDiscount(int discountId) {
        for(Discount discount : discounts) {
            if(discount.getId() == discountId) {
                discounts.remove(discount);
                return true;
            }
        }
        return false;
    }

    public boolean removePolicy(int policyId) {
        for(Policy policy : policies) {
            if(policy.getId() == policyId) {
                policies.remove(policy);
                return true;
            }
        }
        return false;
    }
}
