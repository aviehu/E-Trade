package Domain.Stores;

import java.util.LinkedList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new LinkedList<>();
    }

    public void addProduct(String name, int amount, int price, String category){
        if(getProductByName(name) == null) {
            products.add(new Product(name, amount, price, category));
        }
    }

    public boolean canPurchase(String prodName,int quantity){
        Product product = getProductByName(prodName);
        if(product != null) {
            return product.getAmount() >= quantity;
        }
        return false;
    }

    public Product getProductByName(String ProductName) {
        for (Product product : products) {
            if(product.getName().equals(ProductName)) {
                return product;
            }
        }
        return null;
    }

    public void changeProductName(String oldName, String newName) {
        Product product = getProductByName(oldName);
        if(product != null && getProductByName(newName) != null){
            product.setName(newName);
        }
    }

    public void removeProduct(String productName) {
        Product product = getProductByName(productName);
        if(product != null) {
            products.remove(product);
        }
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> result = new LinkedList<>();
        for(Product product : products) {
            if(product.getCategory().equals(category)) {
                result.add(product);
            }
        }
        return result;
    }

    public void changeProductPrice(String productName, int newPrice) {
        getProductByName(productName).setPrice(newPrice);
    }

    public void addKeyWordToProduct(String productName, String keyword) {
        getProductByName(productName).addKeyword(keyword);
    }

    public List<Product> getProductsByKeyword(String keyword) {
        List<Product> result = new LinkedList<>();
        for (Product product : products) {
            if(product.getKeywords().contains(keyword)) {
                result.add(product);
            }
        }
        return result;
    }
}
