package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.*;
import com.workshop.ETrade.Domain.Stores.Discounts.Discount;
import com.workshop.ETrade.Domain.Stores.Policies.Policy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.*;

public class StoreDTO {
    @Id
    public String name;
    @DBRef
    public List<ProductDTO> products;
    public int discountId;
    public int policyId;
    public String founderName;
    public int card;
    public boolean closed;
    public int bidId;
    public Map<String, managersPermission> managersPermissions;
    public List<MapDBobjDTO> ownersAppointments;
    public List<MapDBobjDTO> managersAppointments;
    @DBRef
    public List<BidDTO> bids;
    @DBRef
    public List<DiscountDTO> discounts;
    @DBRef
    public List<PolicyDTO> policies;
    //public List<PurchaseDTO>  purchaseHistory;

    public StoreDTO(String name, String founderName, int card, boolean closed, int bidId,  List<MapDBobjDTO> ownersAppointments, List<MapDBobjDTO> managersAppointments, List<ProductDTO> products, Map<String, String> managersPermissions, List<BidDTO> bids, int discountId, int policyId, List<DiscountDTO> discounts, List<PolicyDTO> policies) {
        this.name = name;
        this.products = products;
        this.founderName = founderName;
        this.card = card;
        this.closed = closed;
        this.bidId = bidId;
        this.managersPermissions = new HashMap<>();
        for(String key : managersPermissions.keySet()) {
            managersPermission mp;
            switch (managersPermissions.get(key)) {
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
                }
            }
            this.managersPermissions.put(key, mp);
        }
        this.ownersAppointments = ownersAppointments;
        this.managersAppointments = managersAppointments;
        this.bids = bids;
        this.discountId = discountId;
        this.policyId = policyId;
        this.discounts = discounts;
        this.policies = policies;
//        this.purchaseHistory = purchaseHistory;
    }

    public StoreDTO() {

    }

    public StoreDTO(Store store) {
        name = store.getName();
        List<Product> ps= store.getProducts();
        products = new ArrayList<>();
        for (Product p : ps) {
            products.add(new ProductDTO(p));
        }
        founderName = store.getFounderName();
        card = store.getCard();
        closed = store.isClosed();
        bidId = store.getBidId();
        managersPermissions =  store.getManagersPermissions();
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
        bids = new ArrayList<>();
        List<Bid> bs = store.getBids();
        for (Bid b : bs) {
            bids.add(new BidDTO(b));
        }
        policyId = store.getPolicyId();
        discountId = store.getDiscountId();
        List<Discount> discs = store.getDiscounts();
        discounts = new LinkedList<>();
        for(Discount d : discs) {
            discounts.add(d.init());
        }
        List<Policy> polys = store.getPolicies();
        policies = new LinkedList<>();
        for(Policy p : polys) {
            policies.add(new PolicyDTO(p));
        }

//        List<Purchase> purchases = store.getPurchases();
//        purchaseHistory = new ArrayList<>();
//        for(Purchase p : purchases) {
//            purchaseHistory.add(new PurchaseDTO(p));
//        }
    }


}