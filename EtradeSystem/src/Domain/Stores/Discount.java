package Domain.Stores;

import java.util.List;

public class Discount {
    private List<Product> products;
    private int discountPercent;
    private String desc;

    public Discount(List<Product> products, int discountPercent, String desc){
        this.discountPercent = discountPercent;
        this.products = products;
        this.desc = desc;
    }

    public int apply(Product product, int currPercent) {
        if(products.contains(product)) {
            return currPercent - (currPercent * discountPercent / 100);
        }
        return currPercent;
    }
}
