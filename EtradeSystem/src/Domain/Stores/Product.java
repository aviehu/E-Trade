package Domain.Stores;

import Domain.purchaseOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Product {

    private String name;
    private int amount;
    private double price;
    private String category;
    private List<String> keywords;
    private purchaseOption selectedOption;

    public Product(String name, int amount, double price, String category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.keywords = Collections.synchronizedList(new ArrayList<String>());
        selectedOption = purchaseOption.IMMEDIATE;
    }

    public purchaseOption getSelectedOption() {
        return selectedOption;
    }

    public boolean setSelectedOption(purchaseOption selectedOption) {
        this.selectedOption = selectedOption;
        return true;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getKeywords(){
        return keywords;
    }

    public boolean addKeyword(String keyword) {
        if(!keyword.contains(keyword)) {
            keywords.add(keyword);
            return true;
        }
        return false;
    }

    public boolean removeKeyword(String keyword) {
        if(keyword.contains(keyword)) {
            keywords.remove(keyword);
            return true;
        }
        return false;
    }

    public int getAmount() {
        return amount;
    }

    public boolean setAmount(int newAmount) {
        if(amount < 0) {
            return false;
        }
        amount = newAmount;
        return true;
    }

    public boolean setPrice(double newPrice){
        if(newPrice > 0) {
            this.price = newPrice;
            return true;
        }
        return false;
    }

    public double getPrice() {
        return price;
    }

    public boolean addAmount(int amount) {
        if(this.amount - amount >= 0) {
            this.amount = amount;
            return true;
        }
        return false;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        String result = "Product: " + name + "\n";
        result +=       "Amount In Stock: " + amount + "\n";
        result +=       "Base Price: " + price + "\n";
        result +=       "Category: " + category + "\n";
        result +=       "Keywords: " + keywords.toString() + "\n";
        result +=       "Purchase Option: " + selectedOption.toString();
        return result;
    }
}
