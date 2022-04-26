package Domain.Stores;

import java.util.LinkedList;
import java.util.List;

public class StoresFacade {
    private List<Store> stores;

    public StoresFacade(){
        stores = new LinkedList<>();
    }

    public boolean addStore() {
        return false;
    }

    public String displayAllStores() {
        String result = "";
        for(Store store : stores) {
            result = result + store.toString();
        }
        return result;
    }

    public boolean canAddProduct(String productName, String StoreName, int quantity) {
        return true;
    }

    public boolean deleteStore(String storeName) {
        return true;
    }

    public String searchByKeyword(String keyword) {
        return "";
    }

    public String searchByName(String name) {
        return "";
    }

    public String searchByCategory(String category) {
        return "";
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
