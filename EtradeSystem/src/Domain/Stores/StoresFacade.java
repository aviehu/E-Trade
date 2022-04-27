package Domain.Stores;

import Domain.purchaseOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoresFacade {
    private List<Store> stores;

    public StoresFacade(){
        stores = Collections.synchronizedList(new ArrayList<Store>());
    }

    public boolean addStore(String storeName, String founderName,int card) {
        Store store = getStoreByName(storeName);
        if(store != null) {
            return false;
        }
        stores.add(new Store(storeName, founderName, card));
        return true;
    }

    public String displayAllStores() {
        String result = "";
        for(Store store : stores) {
            result = result + store.toString();
        }
        return result;
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
            return "";
        }
    }

    public boolean addProductToStore(String ownerName, String storeName, String productName, int amount, double price, String category) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        return store.addProduct(ownerName,productName,amount,price,category);
    }

    public boolean removeProductFromStore(String userName, String storeName, String productName) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        return store.removeProduct(userName ,productName);
    }

    public boolean editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        return store.changeProductName(userName ,oldProductName, newProductName);
    }

    public boolean editProductPrice(String userName, String storeName, String productName, double newPrice) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        return store.changeProductPrice(userName ,productName, newPrice);
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
        return store.setPurchaseOption(userName, productName ,newOption);
    }

    public boolean appointStoreOwner(String userName, String storeName, String newOwner) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        return store.addOwner(userName, newOwner);
    }

    public boolean appointStoreManager(String userName, String storeName, String newManager) {
        Store store = getStoreByName(storeName);
        if(store == null) {
            return false;
        }
        return store.addManager(userName, newManager);
    }
}
