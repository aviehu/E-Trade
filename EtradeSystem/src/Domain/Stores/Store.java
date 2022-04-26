package Domain.Stores;

import Domain.purchaseOption;

import java.time.LocalDate;
import java.util.*;

public class Store {

    private String name;
    private String founderName;
    private int card;
    private boolean closed;
    private int auctionId;
    private int bidId;
    private int purchaseId;
    private int raffleId;
    private Inventory inventory;
    private PolicyManager policyManager;
    private Map<String, List<String>> ownersAppointments;
    private Map<String, List<String>> managersAppointments;
    private List<Raffle> raffles;
    private List<Bid> bids;
    private List<Auction> auctions;
    private List<Purchase> purchaseHistory;


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
        closed = false;
        this.purchaseHistory = new LinkedList<>();
        raffles = new LinkedList<>();
        auctionId = 1;
        bidId = 1;
        purchaseId = 1;
        raffleId = 1;
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
        if(isOwner(name) && !closed) {
            closed = true;
            return true;
        }
        return false;
    }

    public boolean purchase(Map<String,Integer> prods, String buyer){
        if(policyManager.canPurchase(prods) && inventory.canPurchase(prods)) {
            inventory.purchase(prods);
            for(String productName : prods.keySet()) {
                Product product = inventory.getProductByName(productName);
                purchaseHistory.add(new Purchase(policyManager.getProductPrice(product, prods),productName, prods.get(productName), buyer, purchaseId));
                purchaseId++;
            }
            return true;
        }
        return false;
    }

    public String getHistory(String name) {
        String result = "";
        for(Purchase purchase : purchaseHistory) {
            result = result + purchase.toString();
        }
        return result;
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

    public boolean setPurchaseOption(String productName, purchaseOption option) {
        return inventory.setPurchaseOption(productName, option);
    }

    public void addManager(String ownerName, String nameToAdd){
        if(!isManager(nameToAdd) && !isOwner(nameToAdd) && isOwner(ownerName)){
            managersAppointments.get(ownerName).add(nameToAdd);
        }
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

    public void addProduct(String ownerName, String name, int amount, int price, String category) {
        if(isOwner(ownerName)) {
            inventory.addProduct(name, amount, price, category);
        }
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
