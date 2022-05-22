package com.workshop.ETrade.Domain.Stores.Predicates;

import com.workshop.ETrade.Domain.Stores.Product;

import java.time.LocalDate;
import java.util.Map;

public class TimePredicate implements Predicate{

    private LocalDate startTime;
    private LocalDate endTime;
    private boolean include;

    public TimePredicate(LocalDate startTime, LocalDate endTime, boolean include) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.include = include;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> products) {
        LocalDate now = LocalDate.now();
        if(include) {
            return now.isAfter(startTime) && now.isBefore(endTime);
        } else {
            return now.isBefore(startTime) || now.isAfter(endTime);
        }
    }
}
