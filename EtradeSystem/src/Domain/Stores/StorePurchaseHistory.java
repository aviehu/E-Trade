package Domain.Stores;

import java.util.*;

public class StorePurchaseHistory {
    private List<Purchase> purchases;
    private int purchaseId;

    public StorePurchaseHistory() {
        purchases = Collections.synchronizedList(new ArrayList<Purchase>());;
        purchaseId = 1;
    }

    public boolean addPurchase(double totalPrice, Map<Product, int> products, String buyer) {
        Purchase purchase = new Purchase(totalPrice, products, buyer, purchaseId);
        purchaseId++;
        return true;
    }

    public String getHistory() {
        String result = "";
        for(Purchase purchase : purchases) {
            result = result + purchase.toString();
        }
        return result;
    }
}
