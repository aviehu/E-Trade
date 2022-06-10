package com.workshop.ETrade.Domain.Stores;

import com.workshop.ETrade.Domain.Users.CreditCard;
import com.workshop.ETrade.Domain.Users.SupplyAddress;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Bid {

    private Product product;
    private String bidderName;
    private double price;
    private Map<String, Boolean> awaitingApprove;
    private int bidId;

    private String storeName;

    private boolean approvedByBidder;

    CreditCard creditCard;

    SupplyAddress supplyAddress;

    private boolean rejected;

    public Bid(Product product, String bidderName, double price, Set<String> ownersNames, int bidId,  CreditCard creditCard, SupplyAddress supplyAddress, String storeName) {
        this.product = product;
        this.bidderName = bidderName;
        this.price = price;
        this.bidId = bidId;
        this.awaitingApprove = new ConcurrentHashMap<>();
        this.rejected = false;
        this.creditCard = creditCard;
        this.supplyAddress = supplyAddress;
        this.approvedByBidder = true;
        this.storeName = storeName;
        initMap(ownersNames);
    }

    public String getProductName() {
        return product.getName();
    }

    public double getPrice() {
        return price;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void reject(){
        rejected = true;
    }

    public Bid approve(String ownersName) {
        if(!awaitingApprove.containsKey(ownersName)) {
            return null;
        }
        awaitingApprove.computeIfPresent(ownersName, (K,V) -> V = true);
        if(isApproved()) {
            return this;
        }
        return null;
    }


    private void initMap( Set<String> ownersNames){
        for(String owner : ownersNames) {
            awaitingApprove.put(owner, false);
        }
    }

    private boolean isApproved() {
        if(rejected || !approvedByBidder) {
            return false;
        }
        boolean result = true;
        Set<String> ownersToApprove = awaitingApprove.keySet();
        for(String owner: ownersToApprove) {
            result = result && awaitingApprove.get(owner);
        }
        return result;
    }

    public String toString() {
        String result = "Bid " + bidId + ":\n";
        result +=       "Product: " + product.getName() + "\n";
        result +=       "Bidder: " + bidderName + "\n";
        result +=       "Price: " + price + "\n";
        result +=       "Status:\n" + statusString() + "\n";
        return result;
    }

    private String statusString() {
        String approved = "";
        String notApproved = "";
        for(String ownerName : awaitingApprove.keySet()) {
            if(awaitingApprove.get(ownerName)){
                approved += ownerName + ", ";
            } else {
                notApproved += ownerName + ", ";
            }
        }
        if(approved.length() >= 2) {
            approved = approved.substring(0, approved.length() - 2);
        }
        if(notApproved.length() >= 2) {
            notApproved = notApproved.substring(0, notApproved.length() - 2);
        }
        String result = "Approved: " + approved + "\n";
        result +=       "Not Approved Yet: " + notApproved + "\n";
        return result;
    }

    public int getId() {
        return bidId;
    }

    public Map<String, Boolean> getAwaitingApprove() {
        return awaitingApprove;
    }

    public boolean getRejected() {
        return rejected;
    }

    public CreditCard getCard() {
        return creditCard;
    }

    public SupplyAddress getSupplyAddress() {
        return supplyAddress;
    }

    public boolean counterOffer(double newOffer) {
        if(price >= newOffer) {
            return false;
        }
        for(String owner : awaitingApprove.keySet()) {
            awaitingApprove.computeIfPresent(owner, (K, V) -> V = false);
        }
        price = newOffer;
        rejected = false;
        approvedByBidder = false;
        return true;
    }

    public String getStoreName() {
        return storeName;
    }

    public Bid counterOfferReview(boolean approve) {
        if(approve) {
            approvedByBidder = true;
        } else {
            reject();
        }
        if(isApproved()){
          return this;
        }
        return null;
    }

    public boolean getApprovedByBidder() {
        return approvedByBidder;
    }
}
