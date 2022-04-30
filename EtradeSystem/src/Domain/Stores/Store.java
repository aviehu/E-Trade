package Domain.Stores;

import Domain.purchaseOption;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Store {

    private boolean closedByAdmin;
    private String name;
    private String founderName;
    private int card;
    private boolean closed;
    private int auctionId;
    private int bidId;
    private int raffleId;
    private Inventory inventory;
    private Map<String, managersPermission> managersPermissions;
    private PolicyManager policyManager;
    private StorePurchaseHistory storeHistory;
    private Map<String, List<String>> ownersAppointments;
    private Map<String, List<String>> managersAppointments;
    private List<Raffle> raffles;
    private List<Bid> bids;
    private List<Auction> auctions;
    private Lock invLock;

    public Store(String storeName, String founderName,int card) {
        name = storeName;
        inventory = new Inventory();
        founderName = founderName;
        ownersAppointments = new HashMap<>();
        ownersAppointments.put(founderName, new LinkedList<>());
        managersAppointments = new HashMap<>();
        policyManager = new PolicyManager();
        this.card = card;
        bids = new LinkedList<>();
        auctions = new LinkedList<>();
        closed = false;
        storeHistory = new StorePurchaseHistory();
        raffles = new LinkedList<>();
        auctionId = 1;
        bidId = 1;
        raffleId = 1;
        ReadWriteLock lock = new ReentrantReadWriteLock();
        invLock = lock.writeLock();
        managersPermissions = new ConcurrentHashMap<>();
        closedByAdmin = false;
    }

    public boolean hasLowPermission(String userName) {
        if(isManager(userName) || isOwner(userName)) {
            return true;
        }
        return false;
    }

    public boolean hasMidPermission(String userName) {
        if(isOwner(userName) || (isManager(userName) && managersPermissions.get(userName) != managersPermission.LOW)) {
            return true;
        }
        return false;
    }

    public boolean hasHighPermission(String userName) {
        if(isOwner(userName) || (isManager(userName) && managersPermissions.get(userName) == managersPermission.HIGH)) {
            return true;
        }
        return false;
    }

    public boolean changeStoreManagersPermission(String userName, String managerName, managersPermission newPermission){
        if(!isOwner(userName) || !isManager(managerName) || !managersAppointments.get(userName).equals(managerName)) {
            return false;
        }
        managersPermissions.computeIfPresent(managerName, (K, V) -> V = newPermission);
        return true;
    }

    public String getName() {
        return name;
    }

    private boolean isFounder(String name){
        return founderName.equals(name);
    }

    public int getCard() {
        return card;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean closeStore(String name) {
        if(isFounder(name) && !closed) {
            closed = true;
            return true;
        }
        return false;
    }

    public boolean adminCloseStore() {
        if(!closed) {
            closed = true;
            closedByAdmin = true;
            return true;
        }
        return false;
    }

    public boolean purchase(Map<String,Integer> prods, String buyer){
        if(policyManager.canPurchase(prods) && inventory.canPurchase(prods)) {
            invLock.lock();
            inventory.purchase(prods);
            invLock.unlock();
            Map<Product, Integer> products = new HashMap<>();
            for(String productName : prods.keySet()) {
                Product product = inventory.getProductByName(productName);
                products.put(product, prods.get(productName));
            }
            storeHistory.addPurchase(policyManager.getTotalPrice(products), products, buyer);
            return true;
        }
        return false;
    }

    public String getHistory() {
        return storeHistory.getHistory();
    }

    public boolean startAuction(double startingPrice, LocalDate auctionEnd, String productName) {
        Product product = inventory.getProductByName(productName);
        if(product == null || product.getSelectedOption() != purchaseOption.AUCTION) {
            return false;
        }
        auctions.add(new Auction(startingPrice, auctionEnd, product, "NONE", auctionId));
        auctionId++;
        return true;
    }

    public String printAuctions() {
        StringBuilder result = new StringBuilder();
        for(Auction auction : auctions) {
            result.append(auction.toString());
        }
        return result.toString();
    }

    public String printAuction(int auctionId){
        Auction auction = getAuctionById(auctionId);
        if(auction == null) {
            return "There is no auction with id: " + auctionId;
        }
        return auction.toString();
    }

    private Auction getAuctionById(int auctionId) {
        for(Auction auction : auctions) {
            if(auction.getAuctionId() == auctionId) {
                return auction;
            }
        }
        return null;
    }

    public boolean addBid(String productName, double amount, String biddersName) {
        Product product = inventory.getProductByName(productName);
        if(product != null && product.getSelectedOption() == purchaseOption.BID) {
            bids.add(new Bid(product,biddersName,amount,ownersAppointments.keySet(), bidId));
            bidId++;
            return true;
        }
        return false;
    }

    public boolean startRaffle(String productName, LocalDate raffleEnd, double price) {
        Product product = inventory.getProductByName(productName);
        if(product != null && product.getSelectedOption() == purchaseOption.RAFFLE) {
            raffles.add(new Raffle(product,price, raffleEnd, raffleId));
            return true;
        }
        return false;
    }

    public purchaseOption getPurchaseOption(String productName){
        return inventory.getPurchaseOption(productName);
    }

    public boolean setPurchaseOption(String userName, String productName, purchaseOption option) {
        if(hasMidPermission(userName)) {
            return inventory.setPurchaseOption(productName, option);
        }
        return false;
    }

    public boolean addManager(String ownerName, String nameToAdd){
        if(!isManager(nameToAdd) && !isOwner(nameToAdd) && isOwner(ownerName)){
            managersAppointments.get(ownerName).add(nameToAdd);
            managersPermissions.put(nameToAdd, managersPermission.LOW);
            return true;
        }
        return false;
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

    public double getPrice(Map<String, Integer> items) {
        List<Product> products = inventory.getProductsByListOfNames(items.keySet());
        Map<Product, Integer> productsAmounts = new HashMap<>();
        for(Product product : products) {
            productsAmounts.put(product, items.get(product.getName()));
        }
        return policyManager.getTotalPrice(productsAmounts);
    }

    public boolean addProduct(String userName, String productName, int amount, double price, String category) {
        if(hasHighPermission(userName)) {
            inventory.addProduct(productName, amount, price, category);
            return true;
        }
        return false;
    }

    public String getProductPrice(String productName) {
        return  inventory.getProductByName(productName).getName();
    }

    public boolean removeOwner(String ownersName, String ownerToRemove) {
        if(isOwner(ownersName) && ownersAppointments.get(ownersName).contains(ownerToRemove)) {
            ownersAppointments.get(ownersName).remove(ownerToRemove);
            return true;
        }
        return false;
    }

    public boolean removeManager(String ownersName ,String managerName) {
        if(isOwner(ownersName) && managersAppointments.get(ownersName).contains(managerName)) {
            managersAppointments.get(ownersName).remove(managerName);
            return true;
        }
        return false;
    }

    public Set<String> getOwners(String name) {
        if(isOwner(name)) {
            return ownersAppointments.keySet();
        }
        return null;
    }

    public Set<String> getManagers(String name) {
        if(isOwner(name)) {
            return managersAppointments.keySet();
        }
        return null;
    }

    public Set<String> getAllManagement(String name) {
        Set<String> managers = getManagers(name);
        Set<String> owners = getOwners(name);
        if(managers == null || owners == null) {
            return null;
        }
        Set<String> management = managers;
        management.addAll(owners);
        return management;
    }

    public boolean canPurchase(String prodName,int quantity){
        return inventory.canPurchase(prodName, quantity);
    }

    public boolean removeProduct(String userName, String productName) {
        if(hasHighPermission(userName)) {
            inventory.removeProduct(productName);
            return true;
        }
        return false;
    }

    public boolean changeProductName(String userName,String oldName, String newName){
        if(hasMidPermission(userName)) {
            inventory.changeProductName(oldName, newName);
            return true;
        }
        return false;
    }

    public boolean changeProductPrice(String userName,String productName, double newPrice) {
        if(hasMidPermission(userName)) {
            inventory.changeProductPrice(productName, newPrice);
            return true;
        }
        return false;
    }

    public String getAllProdsByCategory(String categoryName) {
        String result = "";
        for(Product product: inventory.getProductsByCategory(categoryName)) {
            result = result + product.toString();
        }
        return result;
    }

    public boolean addKeywordToProduct(String ownerName, String productName, String keyword) {
        if(isOwner(ownerName)) {
            inventory.addKeyWordToProduct(productName, keyword);
            return true;
        }
        return false;
    }

    public boolean canAddProduct(String productName, int quantity) {
        return inventory.canPurchase(productName, quantity);
    }

    public String searchByKeyword(String keyword) {
        return inventory.searchByKeyword(keyword);
    }

    public String searchByName(String name) {
        return inventory.searchByName(name);
    }

    public String searchByCategory(String category) {
        return inventory.searchByCategory(category);
    }

    public String toString() {
        if(isClosed()){
            return null;
        }
        return inventory.toString();
    }

    private boolean isOwner(String nameToSearch) {
        for(String owner : ownersAppointments.keySet()) {
            if(owner.equals(nameToSearch)){
                return true;
            }
        }
        return false;
    }

    public boolean writeReviewOnProduct(String productName, String review, String userName) {
        return true;
    }

    public boolean rateProduct(String productName, int rating, String userName) {
        return true;
    }

    public boolean rateStore(int rate, String userName) {
        return true;
    }

    public boolean ask(String userName, String question) {
        return true;
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

    public boolean changeProductQuantity(String userName, String productName, int newQuantity) {
        if(hasMidPermission(userName)) {
            return inventory.changeProductQuantity(productName, newQuantity);
        }
        return false;
    }
    public int getProductAmount(String prodName){
        return inventory.getProductByName(prodName).getAmount();
    }
}
