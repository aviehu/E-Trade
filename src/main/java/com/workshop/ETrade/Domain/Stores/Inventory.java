package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.AllRepos;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Persistance.Stores.ProductDTO;
import com.workshop.ETrade.Persistance.Stores.StoreDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.*;

public class Inventory {

    private List<Product> products;

    public Inventory() {
        products = Collections.synchronizedList(new ArrayList<Product>());
    }

    public Inventory(List<ProductDTO> prods) {
        products = Collections.synchronizedList(new ArrayList<>());
        for(ProductDTO p : prods) {
            products.add(new Product(p));
        }
    }

    public Map<Product, Integer> getAmountsFromNames(Map<String, Integer> amounts) {
        Map<Product, Integer> productAmounts = new HashMap<Product, Integer>();
        for(String productName : amounts.keySet()) {
            productAmounts.put(getProductByName(productName), amounts.get(productName));
        }
        return productAmounts;
    }

    public boolean addProduct(String name, int amount, double price, String category){
        if(getProductByName(name) == null) {
            Product product = new Product(name, amount, price, category);
            products.add(product);
            AllRepos.getProductRepo().save(new ProductDTO(product));
            return true;
        }
        return false;
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

    public boolean changeProductName(String oldName, String newName) {
        Product product = getProductByName(oldName);
        if(product != null && getProductByName(newName) != null){
            product.setName(newName);
            AllRepos.getProductRepo().save(new ProductDTO(product));
            return true;
        }
        return false;
    }

    public boolean removeProduct(String productName) {
        Product product = getProductByName(productName);
        if(product != null) {
            products.remove(product);
            AllRepos.getProductRepo().delete(new ProductDTO(product));
            return true;
        }
        return false;
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

    public boolean changeProductPrice(String productName, double newPrice) {
        Product product = getProductByName(productName);
        if(product != null){
            product.setPrice(newPrice);
            AllRepos.getProductRepo().save(new ProductDTO(product));
            return true;
        }
        return false;
    }

    public boolean addKeyWordToProduct(String productName, String keyword) {
        Product product = getProductByName(productName);
        if(product != null) {
            product.addKeyword(keyword);
            AllRepos.getProductRepo().save(new ProductDTO(product));
            return true;
        }
        return false;
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
        Product product = getProductByName(name);
        if(product == null) {
            return null;
        }
        return product.getName();
    }

    public List<String> searchByCategory(String category) {
        List<String> result = new LinkedList<>();
        List<Product> foundProducts = getProductsByCategory(category);
        for(Product product : foundProducts) {
            result.add(product.getName());
        }
        return result;
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
        AllRepos.getProductRepo().save(new ProductDTO(product));
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
            AllRepos.getProductRepo().save(new ProductDTO(product));
        }
        return true;
    }

    public boolean changeProductQuantity(String productName, int newQuantity) {
        Product product = getProductByName(productName);
        if(product == null) {
            return false;
        }
        AllRepos.getProductRepo().save(new ProductDTO(product));
        return product.setAmount(newQuantity);
    }

    public List<Product> getProducts() {
        return products;
    }
}
