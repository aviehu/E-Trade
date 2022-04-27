package Domain.Stores;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Store {

    private int card;
    private String name;
    private Inventory inventory;
    private String originalOwner;
    private Map<String, List<String>> ownersAppointments;
    private Map<String, List<String>> managersAppointments;

    private PolicyManager policyManager;

    public Store(String storeName, String ownersName,int card) {
        name = storeName;
        inventory = new Inventory();
        originalOwner = ownersName;
        ownersAppointments = new HashMap<>();
        ownersAppointments.put(ownersName, new LinkedList<>());
        managersAppointments = new HashMap<>();
        policyManager = new PolicyManager();
        this.card = card;
    }

    public void addManager(String ownerName, String nameToAdd){
        if(!isManager(nameToAdd) && !isOwner(nameToAdd) && isOwner(ownerName)){
            managersAppointments.get(ownerName).add(nameToAdd);
        }
    }

    public int getCard() {
        return card;
    }

    public void addOwner(String ownersName, String nameToAdd){
        if(isOwner(ownersName) && !isOwner(nameToAdd)){
           ownersAppointments.get(ownersName).add(nameToAdd);
           ownersAppointments.put(nameToAdd, new LinkedList<>());
           managersAppointments.put(nameToAdd, new LinkedList<>());
        }
    }

    public void addProduct(String ownerName, String name, int amount, int price, String category) {
        if(isOwner(ownerName)) {
            inventory.addProduct(name, amount, price, category);
        }
    }

    public boolean canPurchase(String prodName,int quantity){
        return inventory.canPurchase(prodName, quantity);
    }

    public void removeProduct(String ownerName, String productName) {
        if(isOwner(ownerName)) {
            inventory.removeProduct(productName);
        }
    }

    public void changeProductName(String ownerName,String oldName, String newName){
        if(isOwner(ownerName)) {
            inventory.changeProductName(oldName, newName);
        }
    }

    public void changeProductPrice(String ownerName,String productName, int newPrice) {
        if(isOwner(ownerName)) {
            inventory.changeProductPrice(productName, newPrice);
        }
    }

    public String getAllProdsByCategory(String categoryName) {
        String result = "";
        for(Product product: inventory.getProductsByCategory(categoryName)) {
            result = result + product.toString();
        }
        return result;
    }

    public void addKeywordToProduct(String ownerName, String productName, String keyword) {
        if(isOwner(ownerName)) {
            inventory.addKeyWordToProduct(productName, keyword);
        }
    }

    public String getAllProdsByKeyword(String keyword){
        String result = "";
        for(Product product: inventory.getProductsByKeyword(keyword)) {
            result = result + product.toString();
        }
        return result;
    }

    private boolean isOwner(String nameToSearch) {
        for(String owner : ownersAppointments.keySet()) {
            if(owner.equals(nameToSearch)){
                return true;
            }
        }
        return false;
    }

    private boolean isManager(String nameToSearch) {
        for(String ownersName : managersAppointments.keySet()) {
            for(String manger : managersAppointments.get(ownersName)) {
                if(manger.equals(nameToSearch)) {
                    return true;
                }
            }
        }
        return false;
    }
}
