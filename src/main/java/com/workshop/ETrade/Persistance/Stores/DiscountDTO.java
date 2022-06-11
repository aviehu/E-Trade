package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;

import java.util.List;

public class DiscountDTO {
    public boolean isPre;
    public String discountOn;
    public int discountPercentage;
    public String description;
    public DiscountType type;

    public String operatorType;
    public List<PredicateDTO> predicates;

    public DiscountDTO() {

    }

    public DiscountDTO(boolean isPre, String discountOn, int discountPercentage, String description, String type, String operatorType, List<PredicateDTO> predicates) {
        this.isPre = isPre;
        this.discountOn = discountOn;
        this.discountPercentage = discountPercentage;
        this.description = description;
        DiscountType dt;
        switch (type) {
            case "STORE": {
                dt = DiscountType.STORE;
                break;
            }
            case "PRODUCT": {
                dt = DiscountType.PRODUCT;
                break;
            }
            default: {
                dt = DiscountType.CATEGORY;
                break;
            }
        }
        this.type = dt;
        this.operatorType = operatorType;
        this.predicates = predicates;
    }
}
