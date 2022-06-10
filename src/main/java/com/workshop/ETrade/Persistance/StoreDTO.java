package com.workshop.ETrade.Persistance;

import com.workshop.ETrade.Domain.Stores.Bid;
import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Stores.managersPermission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreDTO {
    public List<ProductDTO> products;
    public String founderName;
    public int card;
    public boolean closed;
    public int bidId;
    public Map<String, String> managersPermissions;
    public Map<String, List<String>> ownersAppointments;
    public Map<String, List<String>> managersAppointments;
    public List<BidDTO> bids;

    public StoreDTO(Store store) {
        List<Product> ps= store.getProducts();
        products = new ArrayList<>();
        for (Product p : ps) {
            products.add(new ProductDTO(p));
        }
        founderName = store.getFounderName();
        card = store.getCard();
        closed = store.isClosed();
        bidId = store.getBidId();
        Map<String, managersPermission> pers = store.getManagersPermissions();
        managersPermissions = new HashMap<>();
        for(String s : pers.keySet()) {
            managersPermissions.put(s, pers.get(s).toString());
        }
        ownersAppointments = store.getOwnersAppointments();
        managersAppointments = store.getManagersAppointments();
        bids = new ArrayList<>();
        List<Bid> bs = store.getBids();
        for (Bid b : bs) {
            bids.add(new BidDTO(b));
        }
    }


}