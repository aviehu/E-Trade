package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.Domain.Notifications.NotificationManager;
import com.workshop.ETrade.Domain.Observable;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Users.Users.Member;
import com.workshop.ETrade.Domain.purchaseOption;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Store implements Observable {

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
    private List<Member> subscribers;
    private NotificationManager notificationManager;

    public Store(String storeName, String founderName,int card) {
        name = storeName;
        inventory = new Inventory();
        this.founderName = founderName;
        ownersAppointments = new HashMap<>();
        ownersAppointments.put(founderName, new LinkedList<>());
        managersAppointments = new HashMap<>();
        managersAppointments.put(founderName, new LinkedList<>());
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
        managersPermissions = new ConcurrentHashMap<>();
        closedByAdmin = false;
        notificationManager = new NotificationManager();
        subscribers = new ArrayList<>();
    }

    public int addDiscount(String userName,String discountOn, int discountPercentage, String description, DiscountType discountType) {
        if(isOwner(userName)) {
            return policyManager.addDiscount(discountOn, discountPercentage, description, discountType);
        }
        return -1;
    }

    public int addPredicateDiscount(String discountOn, int discountPercentage, String description, DiscountType discountType, OperatorComponent operatorComponent) {
        return policyManager.addPredicateDiscount(discountOn, discountPercentage, description, discountType, operatorComponent);
    }

    public int addPolicy(String userName,String policyOn, String description, PolicyType policyType, OperatorComponent operatorComponent) {
        if(isOwner(userName)) {
            return policyManager.addPolicy(policyOn, description, policyType, operatorComponent);
        }
        return -1;
    }

    public boolean removeDiscount(int discountId) {
        return policyManager.removeDiscount(discountId);
    }

    public boolean removePolicy(int policyId) {
        return policyManager.removePolicy(policyId);
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
            notifySubscribers(name+" closed the store "+ getName()+"\n",name);
            return true;
        }
        return false;
    }

    public boolean adminCloseStore() {
        if(!closed) {
            closed = true;
            closedByAdmin = true;
            notifySubscribers("A system manager closed the store "+ getName()+"\n",name);
            return true;
        }
        return false;
    }

    public synchronized boolean purchase(Map<String,Integer> prods, String buyer){
        Map<Product,Integer> amounts = inventory.getAmountsFromNames(prods);
        if(policyManager.canPurchase(amounts) && inventory.canPurchase(prods)) {
            inventory.purchase(prods);
            Map<Product, Integer> products = new HashMap<Product, Integer>();
            for(String productName : prods.keySet()) {
                Product product = inventory.getProductByName(productName);
                products.put(product, prods.get(productName));
            }
            storeHistory.addPurchase(policyManager.getTotalPrice(products), products, buyer);
            purchased(prods.keySet().stream().toList(),buyer);
            return true;
        }
        return false;
    }

    public String getHistory(String userName) {
        if(hasLowPermission(userName)) {
            return storeHistory.getHistory();
        }
        return null;
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
        if(isOwner(ownersName)  && !isOwner(nameToAdd)){
           ownersAppointments.get(ownersName).add(nameToAdd);
           ownersAppointments.put(nameToAdd, new LinkedList<>());
           managersAppointments.put(nameToAdd, new LinkedList<>());
           return true;
        }
        return false;
    }

    public double getPrice(Map<String, Integer> items) {
        List<Product> products = inventory.getProductsByListOfNames(items.keySet());
        Map<Product, Integer> productsAmounts = new HashMap<Product, Integer>();
        for(Product product : products) {
            productsAmounts.put(product, items.get(product.getName()));
        }
        return policyManager.getTotalPrice(productsAmounts);
    }

    public boolean addProduct(String userName, String productName, int amount, double price, String category) {
        if(hasHighPermission(userName)) {
            return inventory.addProduct(productName, amount, price, category);
        }
        return false;
    }

    public double getProductPrice(String productName) {
        return  inventory.getProductByName(productName).getPrice();
    }

    public boolean removeOwner(String ownersName, String ownerToRemove) {
        if(isOwner(ownersName) && ownersAppointments.get(ownersName).contains(ownerToRemove)) {
            ownersAppointments.get(ownersName).remove(ownerToRemove);
            ownersAppointments.remove(ownerToRemove);
            //managersAppointments.get(ownersName).remove(ownerToRemove);
            notifyOne("You are no longer Owner at " + getName(),ownersName,ownerToRemove);
            return true;
        }
        return false;
    }

    public boolean removeManager(String ownersName ,String managerName) {
        if(isOwner(ownersName) && managersAppointments.get(ownersName).contains(managerName)) {
            managersAppointments.get(ownersName).remove(managerName);
            notifyOne("You are no longer Manager at " + getName()+"\n",ownersName,managerName);
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
        Set<String> management = new HashSet();
        for(String owner : ownersAppointments.keySet()) {
            management.add(owner);
            for(String manager : managersAppointments.get(owner)) {
                management.add(manager);
            }
        }
//        Set<String> management = managers;
//        management.addAll(owners);
        return management;
    }

    public boolean canPurchase(String prodName,int quantity){
        return inventory.canPurchase(prodName, quantity);
    }

    public boolean removeProduct(String userName, String productName) {
        if(hasHighPermission(userName)) {
            return inventory.removeProduct(productName);
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
        if(hasMidPermission(ownerName)) {
            return inventory.addKeyWordToProduct(productName, keyword);
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

    public List<String> searchByCategory(String category) {
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
        return this.founderName.equals(nameToSearch);
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

    public String getHistory() {
        return storeHistory.getHistory();
    }

    public void purchased(List<String> products,String userNamePurchased){
        String mess = "";
        for(String p : products){
            mess+=p+"\n";
        }
        notifySubscribers(mess,userNamePurchased);

    }

    @Override
    public void attach(Member user) {
        if(!subscribers.contains(user))
            subscribers.add(user);

    }

    @Override
    public void detach(Member user) {
        if(subscribers.contains(user))
            subscribers.remove(user);
    }

    @Override
    public void notifySubscribers(String message,String sendFrom) {

        for(Member user: subscribers) {
            if(!user.getUserName().equals(sendFrom))
                user.update(message, sendFrom);
        }

    }
    public void notifyOne(String message,String sendFrom,String sendTo) {
        //
        for(Member user: subscribers) {
            if(user.getUserName().equals(sendTo))
                user.update(message, sendFrom);
        }
    }
    public List<String> getSubscribers(){
        List<String> subs = new ArrayList<>();
        for (Member m: this.subscribers){
            subs.add(m.getUserName());
        }
        return subs;
    }
    public List<Product> getProducts() {
        return inventory.getProducts();
    }
}
