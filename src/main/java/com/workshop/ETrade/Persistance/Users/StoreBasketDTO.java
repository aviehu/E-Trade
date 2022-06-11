package com.workshop.ETrade.Persistance.Users;

import java.util.HashMap;

public class StoreBasketDTO {
    private HashMap<String,Integer> prods; // <ProdName,quantity>

    public HashMap<String, Integer> getProds() {
        return prods;
    }

    public void setProds(HashMap<String, Integer> prods) {
        this.prods = prods;
    }

    public StoreBasketDTO(HashMap<String, Integer> prods) {
        this.prods = prods;
    }
}
