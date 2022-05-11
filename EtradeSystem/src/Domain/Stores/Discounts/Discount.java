package Domain.Stores.Discounts;

import Domain.Stores.Calculators.Calculator;
import Domain.Stores.Calculators.CategoryCalculator;
import Domain.Stores.Calculators.ProductCalculator;
import Domain.Stores.Calculators.StoreCalculator;
import Domain.Stores.Product;

import java.util.Map;

public class Discount {

    private Calculator calculator;
    private String description;
    private int id;

    public Discount(int id) {
        this.calculator = null;
        description = null;
        this.id = id;
    }

    public Discount(int id, String discountOn , int discountPercentage, String description, DiscountType type) {
        this.description = description;
        this.id = id;
        switch (type) {
            case CATEGORY -> calculator = new CategoryCalculator(discountOn, discountPercentage);
            case PRODUCT -> calculator = new ProductCalculator(discountOn, discountPercentage);
            case STORE -> calculator = new StoreCalculator(discountPercentage);
        }
    }

    public int getId() {
        return id;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public Map<Product, double> calculatePrices(Map<Product, double> prices , Map<Product, int> amounts) {
        return calculator.calcPrices(prices);
    }

    public boolean shouldApply(Map<Product, int> amounts) {
        return true;
    }

}
