package Domain.Stores;

import Domain.purchaseOption;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private List<Product> getProductsByKeyword(String keyword) {
        List<Product> result = new LinkedList<>();
        for (Product product : products) {
            if(product.getKeywords().contains(keyword)) {
                result.add(product);
            }
        }
        return result;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Product product : products) {
            result.append(product.toString());
        }
        return result.toString();
    }

    public String searchByKeyword(String keyword) {
        StringBuilder result = new StringBuilder();
        List<Product> foundProducts = getProductsByKeyword(keyword);
        for(Product product : foundProducts) {
            result.append(product.toString());
        }
        return result.toString();
    }

    public String searchByName(String name) {
        return getProductByName(name).toString();
    }

    public String searchByCategory(String category) {
        StringBuilder result = new StringBuilder();
        List<Product> foundProducts = getProductsByCategory(category);
        for(Product product : foundProducts) {
            result.append(product.toString());
        }
        return result.toString();
    }

    public purchaseOption getPurchaseOption(String productName) {
        Product product = getProductByName(productName);
        if(product == null) {
            return null;
        }
        return product.getSelectedOption();
    }

    public boolean setPurchaseOption(String productName, purchaseOption option) {
        Product product = getProductByName(productName);
        if(product == null) {
            return false;
        }
        product.setSelectedOption(option);
        return true;
    }

    public List<Product> getProductsByListOfNames(Set<String> keySet) {
        List<Product> products = new LinkedList<>();
        for(String key : keySet) {
            products.add(getProductByName(key));
        }
        return products;
    }

    public boolean canPurchase(Map<String, Integer> prods) {
        boolean result = true;
        for(String productName : prods.keySet()) {
            result = result && canPurchase(productName, prods.get(productName));
        }
        return result;
    }

    public boolean purchase(Map<String, Integer> prods) {
        for(String productName : prods.keySet()) {
            Product product = getProductByName(productName);
            if(product == null || !product.setAmount(product.getAmount() - prods.get(productName))) {
                return false;
            }
        }
        return true;
    }
}
