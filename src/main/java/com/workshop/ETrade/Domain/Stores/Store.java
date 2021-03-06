package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.Controller.Forms.OwnerWaitingForApproveForm;
import com.workshop.ETrade.Domain.Pair;
import com.workshop.ETrade.Domain.Stores.Discounts.Discount;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.Policy;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Users.CreditCard;
import com.workshop.ETrade.Domain.Users.Member;
import com.workshop.ETrade.Domain.Users.SupplyAddress;
import com.workshop.ETrade.Domain.Users.User;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Persistance.Stores.*;
import com.workshop.ETrade.AllRepos;
import com.workshop.ETrade.RepoThread;

import java.lang.management.ManagementPermission;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private String name;
    private String founderName;
    private int card;
    private boolean closed;

    private boolean closedByAdmin;
    private int auctionId;
    private int bidId;
    private int raffleId;

    private Inventory inventory;
    private Map<String, managersPermission> managersPermissions;
    private PolicyManager policyManager;
    private StorePurchaseHistory storeHistory;
    private Map<String, AppointmentAgreement> ownersAppointmentAgreement;
    private Map<String, List<String>> ownersAppointments;
    private Map<String, List<String>> managersAppointments;
    private List<Raffle> raffles;
    private List<Bid> bids;
    private List<Auction> auctions;
    private List<Member> subscribers;

    public Store(StoreDTO storeDTO) {
        name = storeDTO.name;
        founderName = storeDTO.founderName;
        card = storeDTO.card;
        closed = storeDTO.closed;
        bidId = storeDTO.bidId;
        inventory = new Inventory(storeDTO.products);
        managersPermissions = storeDTO.managersPermissions;
        policyManager = new PolicyManager(storeDTO.policies, storeDTO.policyId, storeDTO.discounts, storeDTO.discountId);
        storeHistory = new StorePurchaseHistory(storeDTO.purchaseHistory);
        List<MapDBobjDTO> dts = storeDTO.ownersAppointments;
        ownersAppointments = new HashMap<>();
        managersAppointments = new HashMap<>();
        for (MapDBobjDTO d : dts) {
            ownersAppointments.put(d.key, d.val);
        }
        dts = storeDTO.managersAppointments;
        for (MapDBobjDTO d : dts) {
            managersAppointments.put(d.key, d.val);
        }
        subscribers = new ArrayList<>();
        bids = new LinkedList<>();
        for (BidDTO b : storeDTO.bids) {
            bids.add(new Bid(b, inventory.getProductByName(b.productName)));
        }

        ownersAppointmentAgreement = new HashMap<>();
        for (AwaitingAppointmentDTO adto : storeDTO.awaitingAppointment) {
            ownersAppointmentAgreement.put(adto.awaitingUser, new AppointmentAgreement(adto.mainOwner, adto.approvedBy, adto.isRejected));
        }
    }

    public Store(String storeName, String founderName, int card) {
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
        subscribers = new ArrayList<>();
        ownersAppointmentAgreement = new HashMap<>();
    }

    public int addDiscount(String userName, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        if (isOwner(userName)) {
            return policyManager.addDiscount(discountOn, discountPercentage, description, discountType);
        }
        return -1;
    }

    public int addPredicateDiscount(String discountOn, int discountPercentage, String description, DiscountType discountType, OperatorComponent operatorComponent) {
        return policyManager.addPredicateDiscount(policyManager.getDiscountId(), discountOn, discountPercentage, description, discountType, operatorComponent);
    }

    public int addPolicy(String userName, String policyOn, String description, PolicyType policyType, OperatorComponent operatorComponent) {
        if (isOwner(userName)) {
            return policyManager.addPolicy(policyManager.getPolicyId(), policyOn, description, policyType, operatorComponent);
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
        if (isManager(userName) || isOwner(userName)) {
            return true;
        }
        return false;
    }

    public boolean hasMidPermission(String userName) {
        if (isOwner(userName) || (isManager(userName) && managersPermissions.get(userName) != managersPermission.LOW)) {
            return true;
        }
        return false;
    }

    public boolean hasHighPermission(String userName) {
        if (isOwner(userName) || (isManager(userName) && managersPermissions.get(userName) == managersPermission.HIGH)) {
            return true;
        }
        return false;
    }

    public boolean changeStoreManagersPermission(String userName, String managerName, managersPermission newPermission) {
        if (!isOwner(userName) || !isManager(managerName) || !managersAppointments.get(userName).contains(managerName)) {
            return false;
        }
        managersPermissions.computeIfPresent(managerName, (K, V) -> V = newPermission);
        return true;
    }

    public String getName() {
        return name;
    }

    private boolean isFounder(String name) {
        return founderName.equals(name);
    }

    public int getCard() {
        return card;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean closeStore(String name) {
        if (isFounder(name) && !closed) {
            closed = true;
            // notifySubscribers(name+" closed the store "+ getName()+"\n",name);
            return true;
        }
        return false;
    }

    public boolean adminCloseStore() {
        if (!closed) {
            closed = true;
            //notifySubscribers("A system manager closed the store "+ getName()+"\n",name);
            return true;
        }
        return false;
    }

    public synchronized boolean purchase(Map<String, Integer> prods, String buyer) {
        Map<Product, Integer> amounts = inventory.getAmountsFromNames(prods);
        if (policyManager.canPurchase(amounts) && inventory.canPurchase(prods)) {
            inventory.purchase(prods);
            Map<Product, Integer> products = new HashMap<Product, Integer>();
            for (String productName : prods.keySet()) {
                Product product = inventory.getProductByName(productName);
                products.put(product, prods.get(productName));
            }
            storeHistory.addPurchase(policyManager.getTotalPrice(products), prods, buyer);
            new RepoThread<>(AllRepos.getStoreRepo(), new StoreDTO(this)).start();
            purchased(prods.keySet().stream().toList(), buyer);
            return true;
        }
        return false;
    }

    public boolean counterBid(int bidId, double newOffer) {
        Bid bid = getBidById(bidId);
        if (bid == null) {
            return false;
        }
        boolean ans = bid.counterOffer(newOffer);
        AllRepos.getBidRepo().save(new BidDTO(bid));
        return ans;
    }

    public synchronized boolean purchaseBid(Map<String, Integer> prods, User buyer, double price) {
        Map<Product, Integer> amounts = inventory.getAmountsFromNames(prods);
        if (policyManager.canPurchase(amounts) && inventory.canPurchase(prods)) {
            inventory.purchase(prods);
            Map<Product, Integer> products = new HashMap<Product, Integer>();
            for (String productName : prods.keySet()) {
                Product product = inventory.getProductByName(productName);
                products.put(product, prods.get(productName));
            }
            storeHistory.addPurchase(price, prods, buyer.getUserName());
            new RepoThread<>(AllRepos.getStoreRepo(), new StoreDTO(this)).start();
            purchased(prods.keySet().stream().toList(), buyer.getUserName());
            //notifyUser("Your bid has been approved", name, buyer);
            //notifySubscribers("A bid for - " + prods.keySet().toArray()[0] + "  in " + name + " has been approved", buyer.getUserName());

            return true;
        }
        return false;
    }

    public String getHistory(String userName) {
        if (hasLowPermission(userName)) {
            return storeHistory.getHistory();
        }
        return null;
    }

    public boolean startAuction(double startingPrice, LocalDate auctionEnd, String productName) {
        Product product = inventory.getProductByName(productName);
        if (product == null || product.getSelectedOption() != purchaseOption.AUCTION) {
            return false;
        }
        auctions.add(new Auction(startingPrice, auctionEnd, product, "NONE", auctionId));
        auctionId++;
        return true;
    }

    public String printAuctions() {
        StringBuilder result = new StringBuilder();
        for (Auction auction : auctions) {
            result.append(auction.toString());
        }
        return result.toString();
    }

    public String printAuction(int auctionId) {
        Auction auction = getAuctionById(auctionId);
        if (auction == null) {
            return "There is no auction with id: " + auctionId;
        }
        return auction.toString();
    }

    private Auction getAuctionById(int auctionId) {
        for (Auction auction : auctions) {
            if (auction.getAuctionId() == auctionId) {
                return auction;
            }
        }
        return null;
    }

    public boolean addBid(String productName, double amount, String biddersName, CreditCard creditCard, SupplyAddress supplyAddress, String storeName) {
        Product product = inventory.getProductByName(productName);
        if (product != null && product.getSelectedOption() == purchaseOption.BID) {
            Bid bid = new Bid(product, biddersName, amount, ownersAppointments.keySet(), bidId, creditCard, supplyAddress, storeName);
            bids.add(bid);
            AllRepos.getBidRepo().save(new BidDTO(bid));
            bidId++;
            return true;
        }
        return false;
    }

    public boolean startRaffle(String productName, LocalDate raffleEnd, double price) {
        Product product = inventory.getProductByName(productName);
        if (product != null && product.getSelectedOption() == purchaseOption.RAFFLE) {
            raffles.add(new Raffle(price, raffleEnd, raffleId));
            return true;
        }
        return false;
    }

    public purchaseOption getPurchaseOption(String productName) {
        return inventory.getPurchaseOption(productName);
    }

    public boolean setPurchaseOption(String userName, String productName, purchaseOption option) {
        if (hasMidPermission(userName)) {
            return inventory.setPurchaseOption(productName, option);
        }
        return false;
    }

    public boolean addManager(String ownerName, String nameToAdd) {
        if (!isManager(nameToAdd) && !isOwner(nameToAdd) && isOwner(ownerName)) {
            managersAppointments.get(ownerName).add(nameToAdd);
            managersPermissions.put(nameToAdd, managersPermission.LOW);
            return true;
        }
        return false;
    }

    public boolean addManager(String ownerName, String nameToAdd, String permission) {
        managersPermission mp;
        switch (permission) {
            case "LOW": {
                mp = managersPermission.LOW;
                break;
            }
            case "MID": {
                mp = managersPermission.MID;
                break;
            }
            default: {
                mp = managersPermission.HIGH;
                break;
            }
        }

        if (!isManager(nameToAdd) && !isOwner(nameToAdd) && isOwner(ownerName)) {
            managersAppointments.get(ownerName).add(nameToAdd);
            managersPermissions.put(nameToAdd, mp);
            return true;
        }
        return false;
    }


    private Map<String, Boolean> getAwaiting(String owner) {
        Map<String, Boolean> awaiting = new HashMap<>();
        for (String name : getOwners(owner)) {
            if (owner.equals(name)) {
                awaiting.put(name, true);
            } else {
                awaiting.put(name, false);
            }
        }
        return awaiting;
    }

    public String addOwner(String ownersName, String nameToAdd) {
        if (!isOwner(ownersName)) {
            return ownersName + " is not an owner in this store";
        }
        if (isOwner(nameToAdd)) {
            return nameToAdd + " is already an owner in this store";
        }
        if (!ownersAppointmentAgreement.containsKey(nameToAdd)) {
            ownersAppointmentAgreement.put(nameToAdd, new AppointmentAgreement(ownersName, getAwaiting(ownersName), false));
            AppointmentAgreement aa = ownersAppointmentAgreement.get(nameToAdd);
            aa.approve(ownersName, true);
            if (aa.isApproved()) {
                ownersAppointments.get(aa.getMainOwner()).add(nameToAdd);
                ownersAppointments.put(nameToAdd, new LinkedList<>());
                managersAppointments.put(nameToAdd, new LinkedList<>());
                ownersAppointmentAgreement.remove(nameToAdd);
                AllRepos.getStoreRepo().save(new StoreDTO(this));
                return nameToAdd + " has been added as store owner";
            }
            AllRepos.getStoreRepo().save(new StoreDTO(this));
            return nameToAdd + " has been added to review as store owner";
        }
        return "User is already waiting for approval";
    }

    public String approveOwner(String ownersName, String nameToApprove, boolean approve) {
        String ans = ownersAppointmentAgreement.get(nameToApprove).approve(ownersName, approve);
        AppointmentAgreement aa = ownersAppointmentAgreement.get(nameToApprove);
        if (aa.isApproved()) {
            ownersAppointments.get(aa.getMainOwner()).add(nameToApprove);
            ownersAppointments.put(nameToApprove, new LinkedList<>());
            managersAppointments.put(nameToApprove, new LinkedList<>());
            ownersAppointmentAgreement.remove(nameToApprove);
            AllRepos.getStoreRepo().save(new StoreDTO(this));
        }
        return ans;
    }

    public double getPrice(Map<String, Integer> items) {
        List<Product> products = inventory.getProductsByListOfNames(items.keySet());
        Map<Product, Integer> productsAmounts = new HashMap<Product, Integer>();
        for (Product product : products) {
            productsAmounts.put(product, items.get(product.getName()));
        }
        return policyManager.getTotalPrice(productsAmounts);
    }

    public boolean addProduct(String userName, String productName, int amount, double price, String category) {
        if (hasHighPermission(userName)) {
            if (inventory.addProduct(productName, amount, price, category)) {
                return true;
            }
        }
        return false;
    }

    public double getProductPrice(String productName) {
        return inventory.getProductByName(productName).getPrice();
    }

    public List<String> removeOwner(String ownersName, String ownerToRemove) {
        List<String> ret = new ArrayList<>();
        if (isOwner(ownersName) && ownersAppointments.get(ownersName).contains(ownerToRemove)) {
            ret.add(ownerToRemove);

            List<String> otherOwners = new ArrayList<>();
            otherOwners.addAll(ownersAppointments.get(ownerToRemove));
            List<String> otherManagers = new ArrayList<>();
            otherManagers.addAll(managersAppointments.get(ownerToRemove));
            for (String o : otherOwners) {
                ret.addAll(removeOwner(ownerToRemove, o));
            }
            for (String o : otherManagers) {
                ret.addAll(removeManager(ownerToRemove, o));
            }
            List<String> waitingAgreement = new ArrayList<>();
            waitingAgreement.addAll(ownersAppointmentAgreement.keySet());

            for(String a : waitingAgreement){
                AppointmentAgreement aa = ownersAppointmentAgreement.get(a);
                aa.removeOwnerFromAprroval(ownerToRemove);
                if(aa.isApproved()) {
                    ownersAppointments.get(aa.getMainOwner()).add(a);
                    ownersAppointments.put(a, new LinkedList<>());
                    managersAppointments.put(a, new LinkedList<>());
                    ownersAppointmentAgreement.remove(a);

                }

            }
            ownersAppointments.get(ownersName).remove(ownerToRemove);
            ownersAppointments.remove(ownerToRemove);
            if(this.managersPermissions.containsKey(ownerToRemove))
                managersPermissions.remove(ownerToRemove);
            if(this.managersAppointments.containsKey(ownerToRemove))
                this.managersAppointments.remove(ownerToRemove);
            subscribers.remove(ownerToRemove);

//            for(String a : getAppointmentAgreements().keySet()){
//                AppointmentAgreement agreement = ownersAppointmentAgreement.get(a);
//                agreement.removeOwnerFromAprroval(ownerToRemove);
//                if(agreement.isApproved()) {
//                    ownersAppointments.get(agreement.getMainOwner()).add(a);
//                    ownersAppointments.put(a, new LinkedList<>());
//                    managersAppointments.put(a, new LinkedList<>());
//                }
            //}
            //notifyOne("You are no longer Owner at " + getName(),ownersName,ownerToRemove);
            AllRepos.getStoreRepo().save(new StoreDTO(this));
            return ret;//true
        }
        return ret; // false
    }

    public List<String> removeManager(String ownersName, String managerName) {
        List<String> ret = new ArrayList<>();
        if (isOwner(ownersName) && managersAppointments.get(ownersName).contains(managerName)) {
            ret.add(managerName);
            managersAppointments.get(ownersName).remove(managerName);
            if(managersPermissions.containsKey(managerName))
                managersPermissions.remove(managerName);
            if(managersAppointments.containsKey(managerName))
                managersAppointments.remove(managerName);
            if(subscribers.contains(managerName))
                subscribers.remove(managerName);
//            List<String> otherManagers = managersAppointments.get(managerName);
//            for (String o : otherManagers) {
//                ret.addAll(removeManager(managerName, o));
//            }
            //notifyOne("You are no longer Manager at " + getName()+"\n",ownersName,managerName);
            return ret;
        }
        return ret;
    }

    public Set<String> getOwners(String name) {
        if (isOwner(name)) {
            return ownersAppointments.keySet();
        }
        return null;
    }

    public Set<String> getManagers(String name) {
        if (isOwner(name)) {
            return managersAppointments.keySet();
        }
        return null;
    }

    public Set<String> getAllManagement(String name) {
        Set<String> management = new HashSet();
        for (String owner : ownersAppointments.keySet()) {
            management.add(owner);
            for (String manager : managersAppointments.get(owner)) {
                management.add(manager);
            }
        }
//        Set<String> management = managers;
//        management.addAll(owners);
        return management;
    }

    public boolean canPurchase(String prodName, int quantity) {
        return inventory.canPurchase(prodName, quantity);
    }

    public boolean removeProduct(String userName, String productName) {
        if (hasHighPermission(userName)) {
            return inventory.removeProduct(productName);
        }
        return false;
    }

    public boolean changeProductName(String userName, String oldName, String newName) {
        if (hasMidPermission(userName)) {
            inventory.changeProductName(oldName, newName);
            return true;
        }
        return false;
    }

    public boolean changeProductPrice(String userName, String productName, double newPrice) {
        if (hasMidPermission(userName)) {
            inventory.changeProductPrice(productName, newPrice);
            return true;
        }
        return false;
    }

    public boolean addKeywordToProduct(String ownerName, String productName, String keyword) {
        if (hasMidPermission(ownerName)) {
            return inventory.addKeyWordToProduct(productName, keyword);
        }
        return false;
    }

    public boolean canAddProduct(String productName, int quantity) {
        return inventory.canPurchase(productName, quantity);
    }

    public List<Product> searchByKeyword(String keyword) {
        return inventory.searchByKeyword(keyword);
    }

    public List<Product> searchByName(String name) {
        return inventory.searchByName(name);
    }

    public List<Product> searchByCategory(String category) {
        return inventory.searchByCategory(category);
    }

    public String toString() {
        if (isClosed()) {
            return null;
        }
        return inventory.toString();
    }

    public boolean isOwner(String nameToSearch) {
        for (String owner : ownersAppointments.keySet()) {
            if (owner.equals(nameToSearch)) {
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
        for (String ownersName : managersAppointments.keySet()) {
            for (String manger : managersAppointments.get(ownersName)) {
                if (manger.equals(nameToSearch)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean changeProductQuantity(String userName, String productName, int newQuantity) {
        if (hasMidPermission(userName)) {
            return inventory.changeProductQuantity(productName, newQuantity);
        }
        return false;
    }

    public int getProductAmount(String prodName) {
        Product p = inventory.getProductByName(prodName);
        if (p != null) {
            return p.getAmount();
        }
        return -1;
    }

    public String getHistory() {
        return storeHistory.getHistory();
    }

    public void purchased(List<String> products, String userNamePurchased) {
        String mess = "";
        for (String p : products) {
            mess += p + "\n";
        }
        //notifySubscribers(mess,userNamePurchased);

    }


    public void attach(Member user) {
        if (!subscribers.contains(user))
            subscribers.add(user);

    }


    public void detach(Member user) {
        if (subscribers.contains(user))
            subscribers.remove(user);
    }

//    @Override
//    public void notifySubscribers(String message,String sendFrom) {
//        List<Thread> threads = new ArrayList<>();
//        for(Member user: subscribers) {
//            threads.add(new NotificationThread(user, message, sendFrom));
//        }
//        for(Thread t : threads) {
//            t.run();
//        }
//        for(Thread t : threads) {
//            try {
//                t.join();
//            } catch (Exception e) {
//            }
//        }
//
//    }
//    public void notifyOne(String message,String sendFrom,String sendTo) {
//        //
//        for(Member user: subscribers) {
//            if(user.getUserName().equals(sendTo))
//                user.update(message, sendFrom);
//        }
//    }

    //    public void notifyUser(String message,String sendFrom,User sendTo) {
//        //
//        sendTo.update(message,sendFrom);
//    }
    public List<String> getSubscribers() {
        List<String> subs = new ArrayList<>();
        for (Member m : this.subscribers) {
            subs.add(m.getUserName());
        }
        return subs;
    }

    public List<Product> getProducts() {
        return inventory.getProducts();
    }


    public List<Bid> getBids() {
        return bids;
    }

    private Bid getBidById(int bidId) {
        for (Bid bid : bids) {
            if (bid.getId() == bidId) {
                return bid;
            }
        }
        return null;
    }

    public Bid reviewBid(String userName, int bidId, boolean approve) {
        if (isOwner(userName)) {
            Bid bid = getBidById(bidId);
            if (approve) {
                Bid ans = bid.approve(userName);
                if (ans != null) {
                    HashMap<String, Integer> mapHelper = new HashMap<>();
                    mapHelper.put(ans.getProductName(), 1);
                }
                return ans;
            }
            bid.reject();
            AllRepos.getBidRepo().save(new BidDTO(bid));
        }
        return null;
    }

    public List<Bid> userBids(String userName) {
        List<Bid> ans = new LinkedList<>();
        for (Bid b : bids) {
            if (b.getBidderName().equals(userName)) {
                ans.add(b);
            }
        }
        return ans;
    }

    public Bid counterBidReview(int bidId, boolean approve) {
        Bid bid = getBidById(bidId);
        if (bid == null) {
            return null;
        }
        Bid b = bid.counterOfferReview(approve);
        AllRepos.getBidRepo().save(new BidDTO(bid));
        if (b != null) {
            HashMap<String, Integer> mapHelper = new HashMap<>();
            mapHelper.put(b.getProductName(), 1);
            storeHistory.addPurchase(b.getPrice(), mapHelper, b.getBidderName());
        }
        return b;
    }

    public String getFounderName() {
        return founderName;
    }

    public int getBidId() {
        return bidId;
    }

    public Map<String, managersPermission> getManagersPermissions() {
        return managersPermissions;
    }

    public Map<String, List<String>> getOwnersAppointments() {
        return ownersAppointments;
    }

    public Map<String, List<String>> getManagersAppointments() {
        return managersAppointments;
    }

    public List<Purchase> getPurchases() {
        return storeHistory.getPurchases();
    }

    public int getPolicyId() {
        return policyManager.getPolicyId();
    }

    public int getDiscountId() {
        return policyManager.getDiscountId();
    }

    public List<Discount> getDiscounts() {
        return policyManager.getDiscounts();
    }

    public List<Policy> getPolicies() {
        return policyManager.getPolicies();
    }

    public int addComplexDiscount(String discountOn, int discountPercentage, String description, DiscountType discountType, OperatorComponent component) {
        return policyManager.addPredicateDiscount(policyManager.getDiscountId(), discountOn, discountPercentage, description, discountType, component);
    }

    public Map<String, OwnerWaitingForApproveForm> getOwnersWaitingForApprove() {
        Map<String, OwnerWaitingForApproveForm> ans = new HashMap<>();
        for (String name : ownersAppointmentAgreement.keySet()) {
            AppointmentAgreement aa = ownersAppointmentAgreement.get(name);
            ans.put(name, new OwnerWaitingForApproveForm(aa.getWaiting(), aa.isRejected(), aa.isApproved()));
        }
        return ans;
    }

    public Map<String, AppointmentAgreement> getAppointmentAgreements() {
        return ownersAppointmentAgreement;
    }

    public boolean editProduct(String productName, int amount, int price) {
        return inventory.editProduct(productName, amount, price);
    }

    public Pair<List<String>, Map<String, String>> getStoreManagement(String userName, boolean userSysManager) {
        List<String> owners = ownersAppointments.keySet().stream().toList();
        HashMap<String, String> managersPermissions = new HashMap<>();
        for (String man : this.managersPermissions.keySet()) {
            managersPermissions.put(man, this.managersPermissions.get(man).toString());
        }
        return new Pair<>(owners, managersPermissions);
    }

    public List<String> removeMemberFromStore(String member) { // return list of removed owners or managers
        List<String> ret = new ArrayList<>();
        if (isManager(member)) {

            ret = removeManager(founderName, member);
            return ret;
        }
        if (isOwner(member)) {
            ret = removeOwner(founderName, member);
        }
        return ret;

    }

    public boolean isOpen() {
        return !closed;
    }

    public boolean reopenStore() {
        closed = false;
        return true;
    }

    public String getStoreFounder(String storeName) {
        return founderName;
    }
}
