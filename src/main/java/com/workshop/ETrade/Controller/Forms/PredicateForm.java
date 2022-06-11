package com.workshop.ETrade.Controller.Forms;

import java.time.LocalDate;

public class PredicateForm {
    public String predicateType;
    public String preProduct;
    public double minAmount;
    public double maxAmount;
    public LocalDate startTime;
    public LocalDate endTime;

    public PredicateForm(String predicateType, String preProduct, double minAmount, double maxAmount, LocalDate startTime, LocalDate endTime) {
        this.predicateType = predicateType;
        this.preProduct = preProduct;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
