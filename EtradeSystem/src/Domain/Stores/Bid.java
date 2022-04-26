package Domain.Stores;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bid {

    private Product product;
    private String bidderName;
    private double price;
    private Map<String, Boolean> awaitingApprove;
    private int bidId;

    public Bid(Product product, String bidderName, double price, Set<String> ownersNames, int bidId) {
        this.product = product;
        this.bidderName = bidderName;
        this.price = price;
        this.bidId = bidId;
        this.awaitingApprove = new HashMap<>();
        initMap(ownersNames);
    }

    public boolean approve(String ownersName) {
        if(!awaitingApprove.containsKey(ownersName)) {
            return false;
        }
        awaitingApprove.computeIfPresent(ownersName, (K,V) -> V = true);
        if(isApproved()) {
            return true;
        }
        return false;
    }


    private void initMap( Set<String> ownersNames){
        for(String owner : ownersNames) {
            awaitingApprove.put(owner, false);
        }
    }

    private boolean isApproved() {
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

}
