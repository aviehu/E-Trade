package com.workshop.ETrade.Domain.Stores.Discounts;

import com.workshop.ETrade.Domain.Stores.Product;

import java.util.Map;

public class XorDiscount extends Discount {
    private Discount discount1;
    private Discount discount2;

    public XorDiscount(int id,Discount discount1, Discount discount2) {
        super(id);
        this.discount1 = discount1;
        this.discount2 = discount2;
    }

    @Override
    public Map<Product, Double> calculatePrices(Map<Product, Double> prices , Map<Product, Integer> amounts) {
        if(discount1.shouldApply(amounts)) {
            discount1.calculatePrices(prices, amounts);
        } else if(discount2.shouldApply(amounts)) {
            discount2.calculatePrices(prices, amounts);
        }
        return prices;
    }

    @Override
    public boolean shouldApply(Map<Product, Integer> amounts) {
        return (discount1.shouldApply(amounts) || discount2.shouldApply(amounts));
    }
}
