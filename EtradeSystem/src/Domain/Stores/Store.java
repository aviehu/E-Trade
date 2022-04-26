package Domain.Stores;

import Domain.purchaseOption;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Store {

    private int card;
    private String name;
    private Inventory inventory;
    private String founderName;
    private Map<String, List<String>> ownersAppointments;
    private Map<String, List<String>> managersAppointments;
    private List<Bid> bids;
    private List<Auction> auctions;
    private boolean isClosed;
    private List<Purchase> purchaseHistory;

    private PolicyManager policyManager;

    public Store(String storeName, String founderName,int card) {
        name = storeName;
        inventory = new Inventory();
        founderName = founderName;
        ownersAppointments = new HashMap<>();
        ownersAppointments.put(founderName, new LinkedList<>());
        managersAppointments = new HashMap<>();
        policyManager = new PolicyManager();
        this.card = card;
        this.bids = new LinkedList<>();
        this.auctions = new LinkedList<>();
        isClosed = false;
        this.purchaseHistory = new LinkedList<>();
    }

    public String getHistory(String name) {
        return "";
    }

    public boolean closeStore(String name) {
        return false;
    }

    public String getName() {
        return name;
    }

    public purchaseOption getPurchaseOption(String productName){
        return purchaseOption.BID;
    }

    public boolean setPurchaseOption(String productName, purchaseOption option) {
        return true;
    }

    public void addManager(String ownerName, String nameToAdd){
        if(!isManager(nameToAdd) && !isOwner(nameToAdd) && isOwner(ownerName)){
            managersAppointments.get(ownerName).add(nameToAdd);
        }
    }

    public int getCard() {
        return card;
    }

    public boolean addOwner(String ownersName, String nameToAdd){
        if(isOwner(ownersName) && !isOwner(nameToAdd)){
           ownersAppointments.get(ownersName).add(nameToAdd);
           ownersAppointments.put(nameToAdd, new LinkedList<>());
           managersAppointments.put(nameToAdd, new LinkedList<>());
           return true;
        }
        return false;
    }

    public int getPrice(Map<String, Integer> items) {
        return 0;
    }

    public void addProduct(String ownerName, String name, int amount, int price, String category) {
        if(isOwner(ownerName)) {
            inventory.addProduct(name, amount, price, category);
        }
    }

    public List<String> getOwners(String name) {
        if(isOwner(name)) {

        }
        return new LinkedList<>();
    }

    public boolean removeOwner(String ownersName) {
        return false;
    }

    public boolean removeManager(String managerName) {
        return false;
    }

    public List<String> getManagers(String name) {
        return new LinkedList<>();
    }

    public List<String> getAllManagement(String name) {
        return new LinkedList<>();
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

    public boolean canAddProduct(String productName, int quantity) {
        return true;
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

    public String toString() {
        return inventory.toString();
    }
}
