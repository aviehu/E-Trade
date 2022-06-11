package com.workshop.ETrade.Persistance.Users;

import com.workshop.ETrade.Domain.Users.StoreBasket;

import java.util.HashMap;

public class StoreBasketDTO {
    private HashMap<String,Integer> prods; // <ProdName,quantity>
    private String store;

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public HashMap<String, Integer> getProds() {
        return prods;
    }

    public void setProds(HashMap<String, Integer> prods) {
        this.prods = prods;
    }

    public StoreBasketDTO(HashMap<String, Integer> prods, String store) {
        this.store = store;
        this.prods = prods;
    }

    public StoreBasketDTO(StoreBasket storeBasket) {
        this.prods = storeBasket.getProds();
        this.store = storeBasket.getStoreName();
    }
}
