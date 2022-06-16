package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.AllRepos;
import com.workshop.ETrade.Domain.Stores.Discounts.*;
import com.workshop.ETrade.Domain.Stores.Policies.*;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorLeaf;
import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import com.workshop.ETrade.Domain.Stores.Predicates.PredicateBuilder;
import com.workshop.ETrade.Persistance.Stores.DiscountDTO;
import com.workshop.ETrade.Persistance.Stores.PolicyDTO;
import com.workshop.ETrade.Persistance.Stores.PredicateDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;

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

    public PolicyManager(List<PolicyDTO> policies, int policyId, List<DiscountDTO> discounts, int discountId) {
        this.discounts = new LinkedList<>();
        this.policies = new LinkedList<>();
        for(PolicyDTO pdto : policies) {
            List<Predicate> pres = new ArrayList<>();
            for(PredicateDTO p : pdto.predicates) {
                switch (p.type) {
                    case ("TIME"): {
                        pres.add(PredicateBuilder.getTimePredicate(p.startTime, p.endTime, true));
                        break;
                    }
                    case ("AMOUNT"): {
                        pres.add(PredicateBuilder.getProductAmountPredicate(p.productName, p.minAmount, p.maxAmount));
                        break;
                    }
                    default: {
                        pres.add(PredicateBuilder.getBasketValuePredicate(p.minAmount, p.maxAmount));
                        break;
                    }
                }
            }
            OperatorLeaf ol = new OperatorLeaf(pdto.operatorType, pres);
            addPolicy(Integer.parseInt(pdto.policyId), pdto.policyOn, pdto.description,pdto.type,ol);
        }
        this.policyId = policyId;

        for(DiscountDTO ddto : discounts) {
            List<Predicate> pres = new ArrayList<>();
            if(ddto.isPre) {
                for(PredicateDTO p : ddto.predicates) {
                    switch (p.type) {
                        case ("TIME"): {
                            pres.add(PredicateBuilder.getTimePredicate(p.startTime, p.endTime, true));
                            break;
                        }
                        case ("AMOUNT"): {
                            pres.add(PredicateBuilder.getProductAmountPredicate(p.productName, p.minAmount, p.maxAmount));
                            break;
                        }
                        default: {
                            pres.add(PredicateBuilder.getBasketValuePredicate(p.minAmount, p.maxAmount));
                            break;
                        }
                    }
                }
                OperatorLeaf ol = new OperatorLeaf(ddto.operatorType, pres);
                addPredicateDiscount(Integer.parseInt(ddto.discountId), ddto.discountOn, ddto.discountPercentage, ddto.description, ddto.type,ol);
            }
        }
        this.policyId = policyId;
    }


    private Discount getDiscountById(int id) {
        for(Discount discount : discounts) {
            if(discount.getId() == id) {
                return discount;
            }
        }
        return null;
    }

    public int addPolicy(int policyId, String policyOn, String description, PolicyType type, OperatorComponent operatorComponent) {
        Policy policy;
        switch (type) {
            case BASKET -> policy = new BasketPolicy(policyId, description, operatorComponent);
            case PRODUCT -> policy = new ProductPolicy(policyId, description, policyOn, operatorComponent);
            default ->  policy = new CategoryPolicy(policyId, description, policyOn, operatorComponent);
        }
        policies.add(policy);
        AllRepos.getPolicyRepo().save(new PolicyDTO(policy));
        this.policyId ++;
        return this.policyId - 1;
    }

    public int addDiscount(String discountOn, int discountPercentage, String description, DiscountType type) {
        Discount discount = new Discount(discountId, discountOn, discountPercentage, description, type);
        discounts.add(discount);
        AllRepos.getDiscountRepo().save(new DiscountDTO(discount));
        discountId++;
        return discountId - 1;
    }

    public int addPredicateDiscount(int discountId, String discountOn, int discountPercentage, String description, DiscountType type, OperatorComponent operatorComponent) {
        PredicateDiscount discount = new PredicateDiscount(discountId, discountOn, discountPercentage, description, type, operatorComponent);
        discounts.add(discount);
        AllRepos.getDiscountRepo().save(new DiscountDTO(discount));
        this.discountId++;
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

    public int getPolicyId() {
        return policyId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

}
