package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.Predicates.BasketValuePredicate;
import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import com.workshop.ETrade.Domain.Stores.Predicates.ProductAmountPredicate;
import com.workshop.ETrade.Domain.Stores.Predicates.TimePredicate;

import java.time.LocalDate;

public class PredicateDTO {
    public double minAmount;
    public double maxAmount;
    public String productName;
    public LocalDate startTime;
    public LocalDate endTime;
    public String type;

    public PredicateDTO() {

    }

    public PredicateDTO(Predicate predicate) {
        predicate.init();
    }

    public PredicateDTO(double minAmount, double maxAmount, String productName, LocalDate startTime, LocalDate endTime,String type) {
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.productName = productName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
    }

    public PredicateDTO(BasketValuePredicate predicate) {
        minAmount = predicate.getMinValue();
        maxAmount = predicate.getMaxValue();
        productName = null;
        startTime = null;
        endTime = null;
        type = "BASKET";
    }

    public PredicateDTO(ProductAmountPredicate predicate) {
        minAmount = predicate.getMinAmount();
        maxAmount = predicate.getMaxAmount();
        productName = predicate.getProductName();
        startTime = null;
        endTime = null;
        type = "AMOUNT";
    }

    public PredicateDTO(TimePredicate predicate) {
        minAmount = 0;
        maxAmount = 0;
        productName = null;
        startTime = predicate.getStartTime();
        endTime = predicate.getEndTime();
        type = "TIME";
    }


}
