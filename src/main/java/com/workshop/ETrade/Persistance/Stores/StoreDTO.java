package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.*;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StoreDTO {
    @Id
    public String name;

//    public List<ProductDTO> products;

    public String founderName;
    public int card;
    public boolean closed;
    public int bidId;
    //public Map<String, managersPermission> managersPermissions;
    public List<MapDBobjDTO> ownersAppointments;
    public List<MapDBobjDTO> managersAppointments;
    //public List<BidDTO> bids;
    //public List<PurchaseDTO>  purchaseHistory;

    public StoreDTO(String name, String founderName, int card, boolean closed, int bidId,  List<MapDBobjDTO> ownersAppointments, List<MapDBobjDTO> managersAppointments) {
        this.name = name;
//        this.products = products;
        this.founderName = founderName;
        this.card = card;
        this.closed = closed;
        this.bidId = bidId;
//        this.managersPermissions = managersPermissions;
        this.ownersAppointments = ownersAppointments;
        this.managersAppointments = managersAppointments;
//        this.bids = bids;
//        this.purchaseHistory = purchaseHistory;
    }

    public StoreDTO() {

    }

    public StoreDTO(Store store) {
        name = store.getName();
//        List<Product> ps= store.getProducts();
//        products = new ArrayList<>();
//        for (Product p : ps) {
//            products.add(new ProductDTO(p));
//        }
        founderName = store.getFounderName();
        card = store.getCard();
        closed = store.isClosed();
        bidId = store.getBidId();
        Map<String, managersPermission> pers = store.getManagersPermissions();
//        managersPermissions = new HashMap<>();
//        for(String s : pers.keySet()) {
//            managersPermissions.put(s, pers.get(s));
//        }
        Map<String, List<String>> owns = store.getOwnersAppointments();
        ownersAppointments = new ArrayList<>();
        for(String name : owns.keySet()) {
            ownersAppointments.add(new MapDBobjDTO(name, owns.get(name)));
        }
        managersAppointments = new LinkedList<>();
        Map<String, List<String>> mangs =store.getManagersAppointments();
        for(String name : owns.keySet()) {
            managersAppointments.add(new MapDBobjDTO(name, mangs.get(name)));
        }
        //        bids = new ArrayList<>();
//        List<Bid> bs = store.getBids();
//        for (Bid b : bs) {
//            bids.add(new BidDTO(b));
//        }
//        List<Purchase> purchases = store.getPurchases();
//        purchaseHistory = new ArrayList<>();
//        for(Purchase p : purchases) {
//            purchaseHistory.add(new PurchaseDTO(p));
//        }
    }


}