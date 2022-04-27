package Domain.Stores;

import java.util.*;

public class PurchasePolicy {

    private List<String> productsNames;
    private int minAmount;

    public PurchasePolicy(List<String> productsNames, int minAmount) {
        this.productsNames = productsNames;
        this.minAmount = minAmount;
    }

    public PurchasePolicy() {
        this.productsNames = Collections.synchronizedList(new ArrayList<String>());
        this.minAmount = 0;
    }

    public boolean addProduct(String productName) {
        if(productsNames.contains(productName)) {
            return false;
        }
        productsNames.add(productName);
        return true;
    }

    public boolean setMinAmount(int newMin) {
        if(newMin >= 0) {
            minAmount = newMin;
            return true;
        }
        return false;
    }

    public boolean removeProduct(String productName) {
        if(productsNames.contains(productName)) {
            productsNames.remove(productName);
            return true;
        }
        return false;
    }

    public boolean canPurchase(Map<String, Integer> products){
        if(products.size() == 0 || minAmount == 0) {
            return true;
        }
        for(String productName : products.keySet()) {
            if (productsNames.contains(productName) && products.get(productName) < minAmount) {
                return false;
            }
        }
        return true;
    }

}
