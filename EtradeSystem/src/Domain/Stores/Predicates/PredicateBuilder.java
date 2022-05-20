package Domain.Stores.Predicates;

import java.time.LocalDate;

public class PredicateBuilder {

    public static Predicate getProductAmountPredicate(String productName, int minAmount, int maxAmount) {
        return new ProductAmountPredicate(productName, minAmount, maxAmount);
    }

    public static Predicate getBasketValuePredicate(double minValue, double maxValue) {
        return new BasketValuePredicate(minValue, maxValue);
    }

    public static Predicate getTimePredicate(LocalDate start, LocalDate end, boolean include) {
        return new TimePredicate(start, end, include);
    }

}
