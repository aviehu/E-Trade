package Domain.Stores;

import java.util.LinkedList;
import java.util.List;

public class Product {

    private String name;
    private int amount;
    private int price;
    private String category;
    private List<String> keywords;

    public Product(String name, int amount, int price, String category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.keywords = new LinkedList<>();
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

    public void setPrice(int newPrice){
        if(newPrice > 0) {
            this.price = newPrice;
        }
    }

    public int getPrice() {
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
        return name + "\t" + price + "$\t" + category + "\t" + amount;
    }
}
