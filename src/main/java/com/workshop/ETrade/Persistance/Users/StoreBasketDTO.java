package com.workshop.ETrade.Persistance.Users;

import com.workshop.ETrade.Domain.Users.StoreBasket;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.HashMap;

public class StoreBasketDTO {

    private HashMap<String,Integer> prods; // <ProdName,quantity>
    private String store;
    private String user;

    public StoreBasketDTO(StoreBasket storeBasket) {
        this.prods = storeBasket.getProds();
        this.store = storeBasket.getStoreName();
        this.user = storeBasket.getUserName();
    }

    public StoreBasketDTO(HashMap<String, Integer> prods, String store, String userName) {
        this.store = store;
        this.prods = prods;
        this.user = userName;
    }
    public StoreBasketDTO() {}

    public String getUserName() {
        return user;
    }

    public void setUserName(String userName) {
        this.user = userName;
    }

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


}
