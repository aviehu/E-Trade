package Domain.Stores;

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

}
