package Domain.Stores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bid {

    private Product product;
    private String bidderName;
    private int price;
    private Map<String, Boolean> awaitingApprove;

    public Bid(Product product, String bidderName, int price, List<String> ownersNames) {
        this.product = product;
        this.bidderName = bidderName;
        this.price = price;
        this.awaitingApprove = new HashMap<>();
        initMap(ownersNames);
    }

    private void initMap( List<String> ownersNames){
        for(String owner : ownersNames) {
            awaitingApprove.put(owner, false);
        }
    }

    private boolean approve(String ownersName) {
        if(!awaitingApprove.containsKey(ownersName)) {
            return false;
        }
        awaitingApprove.computeIfPresent(ownersName, (K,V) -> V = true);
        if(isApproved()) {
            return true;
        }
        return false;
    }

    private boolean isApproved() {
        return true;
    }

}
