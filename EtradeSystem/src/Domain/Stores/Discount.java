package Domain.Stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Discount {
    private int discountId;
    private List<Product> products;
    private int discountPercent;
    private String desc;

    public Discount(List<Product> products, int discountPercent, String desc, int discountId){
        this.discountId = discountId;
        this.discountPercent = discountPercent;
        this.products = Collections.synchronizedList(new ArrayList<Product>());
        this.desc = desc;
    }

    public Discount() {
        this.discountId = 0;
        this.discountPercent = 0;
        this.products = new LinkedList<>();
        this.desc = "Default discount";
    }

    public double apply(Product product, int amount, double currPercent) {
        if(products.contains(product)) {
            return currPercent - (currPercent * discountPercent / 100);
        }
        return currPercent;
    }

    public boolean setDesc(String desc) {
        this.desc = desc;
        return true;
    }

    public boolean setDiscountPercent(int newPercent) {
        if(newPercent >= 0 && newPercent <= 100){
            discountPercent = newPercent;
            return true;
        }
        return false;
    }

    public boolean addProductToDiscount(Product product) {
        if(products.contains(product)) {
            return false;
        }
        products.add(product);
        return true;
    }

    public boolean removeProductFromDiscount(Product product) {
        if(products.contains(product)) {
            products.remove(product);
            return true;
        }
        return false;
    }

    public String toString() {
        String result = "Discount: " + discountId + "\n";
        result +=       "Description: " + desc + "\n";
        result +=       "Discount Percentage: " + discountPercent + "\n";
        result +=       "Products On Discount: " + products.toString() + "\n\n";
        return result;
    }
}
