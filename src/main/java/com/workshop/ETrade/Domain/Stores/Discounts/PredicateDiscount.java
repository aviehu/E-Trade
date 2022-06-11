package com.workshop.ETrade.Domain.Stores.Discounts;

import com.workshop.ETrade.Domain.Stores.Calculators.Calculator;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Persistance.Stores.DiscountDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.Map;

public class PredicateDiscount extends Discount {

    OperatorComponent operatorComponent;

    public PredicateDiscount(int id, String discountOn , int discountPercentage, String description, DiscountType type, OperatorComponent operatorComponent) {
        super(id, discountOn, discountPercentage, description, type);
        this.operatorComponent = operatorComponent;
    }

    public Map<Product, Double> calculatePrices(Map<Product, Double> prices, Map<Product, Integer> amounts) {
        Calculator calculator = getCalculator();
        if(operatorComponent.shouldApply(amounts)) {
            prices = calculator.calcPrices(prices);
        }
        return prices;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> amounts) {
        return operatorComponent.shouldApply(amounts);
    }

    public String OperatorType() {
        return operatorComponent.getType();
    }

    public List<Predicate> getPredicates() {
        return operatorComponent.getPredicates();
    }

    @Override
    public DiscountDTO init() {
        return new DiscountDTO(this);
    }
}
