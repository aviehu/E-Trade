package com.workshop.ETrade.Domain.Stores;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.workshop.ETrade.AllRepos;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Persistance.Stores.ProductDTO;
import com.workshop.ETrade.Persistance.Stores.StoreDTO;
import com.workshop.ETrade.RepoThread;
import org.apache.commons.codec.binary.StringUtils;
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
            new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
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
        synchronized (products) {
            for (Product product : products) {
                if (product.getName().equals(ProductName)) {
                    return product;
                }
            }
        }
        return null;
    }

    public boolean changeProductName(String oldName, String newName) {
        Product product = getProductByName(oldName);
        if(product != null && getProductByName(newName) != null){
            product.setName(newName);
            new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
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
            new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
            return true;
        }
        return false;
    }

    public boolean addKeyWordToProduct(String productName, String keyword) {
        Product product = getProductByName(productName);
        if(product != null) {
            product.addKeyword(keyword);
            new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
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

    public List<Product> searchByKeyword(String keyword) {
        List<Product> result = new LinkedList<>();
        for (Product product : products) {
            if(product.getKeywords().contains(keyword)) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> searchByName(String name) {
        List<Product> ans = new LinkedList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name.toLowerCase()))
                ans.add(p);
        }
        return ans;
    }

    public List<Product> searchByCategory(String category) {
        List<Product> result = new LinkedList<>();
        for(Product product : products) {
            if(product.getCategory().toLowerCase().contains(category.toLowerCase())) {
                result.add(product);
            }
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
        new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
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
            new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
        }
        return true;
    }

    public boolean changeProductQuantity(String productName, int newQuantity) {
        Product product = getProductByName(productName);
        if(product == null) {
            return false;
        }
        new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
        return product.setAmount(newQuantity);
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean editProduct(String productName, int amount, int price) {
        Product product = getProductByName(productName);
        if(product == null) {
            return false;
        }
        if(amount < 0 || price < 0) {
            return false;
        }
        product.setAmount(amount);
        product.setPrice(price);
        new RepoThread<>(AllRepos.getProductRepo(), new ProductDTO(product)).start();
        return true;
    }
}
