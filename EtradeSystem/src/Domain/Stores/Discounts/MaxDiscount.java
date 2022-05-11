package Domain.Stores.Discounts;

import Domain.Stores.Discounts.Discount;
import Domain.Stores.Product;

import java.util.Map;

public class MaxDiscount extends Discount{
    private Discount discount1;
    private Discount discount2;


    public MaxDiscount(int id, Discount discount1, Discount discount2) {
        super(id);
        this.discount1 = discount1;
        this.discount2 = discount2;
    }

    private double totalPrice(Map<Product, double> prices){
        double total = 0;
        for(Product product : prices.keySet()) {
            total += prices.get(product);
        }
        return total;
    }

    @Override
    public Map<Product, double> calculatePrices(Map<Product, double> prices , Map<Product, int> amounts) {
        Map<Product, double> dis1Prices = discount1.calculatePrices(prices, amounts);
        Map<Product, double> dis2Prices = discount2.calculatePrices(prices, amounts);
        if(totalPrice(dis1Prices) >= totalPrice(dis2Prices)) {
            return dis1Prices;
        }
        return dis2Prices;
    }

    @Override
    public boolean shouldApply(Map<Product, int> amounts) {
        return discount1.shouldApply(amounts) || discount2.shouldApply(amounts);
    }
}
