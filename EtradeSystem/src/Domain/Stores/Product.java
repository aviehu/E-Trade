package Domain.Stores;

import Domain.purchaseOption;

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
        this.keywords = new LinkedList<>();
        selectedOption = purchaseOption.IMMEDIATE;
    }

    public purchaseOption getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(purchaseOption selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getKeywords(){
        return keywords;
    }

    public void addKeyword(String keyword) {
        if(!keyword.contains(keyword)) {
            keywords.add(keyword);
        }
    }

    public void removeKeyword(String keyword) {
        if(keyword.contains(keyword)) {
            keywords.remove(keyword);
        }
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

    public void setPrice(double newPrice){
        if(newPrice > 0) {
            this.price = newPrice;
        }
    }

    public double getPrice() {
        return price;
    }

    public void addAmount(int amount) {
        if(this.amount - amount >= 0) {
            this.amount = amount;
        }
    }

    public void setName(String name) {
        this.name = name;
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
