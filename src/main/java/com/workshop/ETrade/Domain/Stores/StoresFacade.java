package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.purchaseOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class StoresFacade {
    private Logger logger = Logger.getLogger("stores");
    private List<Store> stores;

    public StoresFacade(){
        stores = Collections.synchronizedList(new ArrayList<Store>());
        try {
            Handler fileHandler = new FileHandler(System.getProperty("user.dir") + "/stores.log", 2000, 5);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("error while creating logger for stores");
        }
    }
    public int addPolicy(String store,String policyOn, String description, PolicyType policyType, OperatorComponent operatorComponent){
        Store s = getStore(store);
        return s.addPolicy(policyOn, description, policyType, operatorComponent);
    }
    public boolean addStore(String storeName, String founderName,int card) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            return false;
        }
        stores.add(new Store(storeName, founderName, card));
        logger.info("store - " + storeName + "added by - " + founderName);
        return true;
    }

    public String displayAllStores() {
        String result = "";
        for(Store store : stores) {
            result = result + store.toString();
        }
        return result;
    }

    public boolean adminCloseStore(String storeName) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            if(store.adminCloseStore()) {
                logger.info("store - " + storeName + "closed by admin");
                return true;
            }
        }
        return false;
    }

    public boolean canAddProduct(String productName, String storeName, int quantity) {
        Store store = getStoreByName(storeName);
        return store.canAddProduct(productName, quantity);
    }

    public boolean deleteStore(String storeName) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        stores.remove(store);
        return true;
    }

    public String searchByKeyword(String keyword) {
        String result = "";
        for(Store store : stores) {
            result = result + store.searchByKeyword(keyword);
        }
        return result;
    }

    public String searchByName(String name) {
        String result = "";
        for(Store store : stores) {
            result = result + store.searchByName(name);
        }
        return result;
    }

    public String searchByCategory(String category) {
        String result = "";
        for(Store store : stores) {
            result = result + store.searchByCategory(category);
        }
        return result;
    }

    private Store getStoreByName(String storeName) {
        for(Store store : stores) {
            if(store.getName().equals(storeName)) {
                return store;
            }
        }
        return null;
    }

    public String displayStore(String storeName) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            return store.toString();
        }
        else {
            return null;
        }
    }

    public boolean addProductToStore(String ownerName, String storeName, String productName, int amount, double price, String category) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        if(store.addProduct(ownerName,productName,amount,price,category)) {
            logger.info("product - " + productName + " was added to store");
            return true;
        }
        return false;
    }

    public boolean removeProductFromStore(String userName, String storeName, String productName) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        if(store.removeProduct(userName ,productName)) {
            logger.info("product - " + productName + " was removed from store");
            return true;
        }
        return false;
    }

    public boolean editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        if(store.changeProductName(userName ,oldProductName, newProductName)) {
            logger.info("product - " + oldProductName + " is now called - " + newProductName + " in store - " + storeName);
        }
        return false;
    }

    public boolean editProductPrice(String userName, String storeName, String productName, double newPrice) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        if(store.changeProductPrice(userName ,productName, newPrice)) {
            logger.info("product - " + productName + " now costs - " + newPrice + " in store - " + storeName);
            return true;
        }
        return false;
    }

    public boolean editProductQuantity(String userName, String storeName, String productName, int newQuantity) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        return store.changeProductQuantity(userName ,productName, newQuantity);
    }

    public boolean changePurchaseOption(String userName, String storeName, String productName, purchaseOption newOption) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        if(store.setPurchaseOption(userName, productName ,newOption)) {
            logger.info("new purchase option for store - " + storeName);
            return true;
        }
        return false;
    }

    public boolean appointStoreOwner(String userName, String storeName, String newOwner) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        if(store.addOwner(userName, newOwner)) {
            logger.info("new store owner for store - " + storeName);
            return true;
        }
        return false;
    }

    public boolean appointStoreManager(String userName, String storeName, String newManager) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        if(store.addManager(userName, newManager)) {
            logger.info("new store manager for store - " + storeName);
            return true;
        }
        return false;
    }
    public Store getStore(String storeName){
        for(Store s : stores){
            if(s.getName() == storeName)
                return s;
        }
        return null;
    }
    public int getProductAmount(String storeName,String prodName){
        return getStore(storeName).getProductAmount(prodName);
    }

    public String getStorePurchaseHistory(String userName, String storeName) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            return store.getHistory(userName);
        }
        return null;
    }

    public String adminGetStorePurchaseHistory(String storeName) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            return store.getHistory();
        }
        return null;
    }

    public Set<String> getStoresManagement(String userName, String storeName) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            return store.getAllManagement(userName);
        }
        return null;
    }

    public boolean closeStore(String storeName, String userName) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            if(store.closeStore(userName)) {
                logger.info("store - " + storeName + " is now closed");
                return true;
            }
        }
        return false;
    }

    public boolean changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            if(store.changeStoreManagersPermission(userName, managerName, newPermission)) {
                logger.info("changed permission for manger - " + managerName);
                return true;
            }
        }
        return false;
    }

    public boolean addKeyword(String userName, String storeName, String productName, String keyWord) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            return store.addKeywordToProduct(userName, productName, keyWord);
        }
        return false;
    }
    public int addDiscount(String store,String discountOn, int discountPercentage, String description, DiscountType discountType){
        Store s = getStoreByName(store);
        if(s == null)
            return -1;
        return s.addDiscount(discountOn, discountPercentage, description, discountType);
    }

    public List<Store> getStores() {
        return stores;
    }
}
