package com.workshop.ETrade.Domain.Users.ExternalService.Supply;

import java.util.HashMap;

public class PackageToShip {
    HashMap<String,Integer> prods;
    int price;

    public PackageToShip(HashMap<String, Integer> prods, int price) {
        this.prods = prods;
        this.price = price;
    }

    public HashMap<String, Integer> getProds() {
        return prods;
    }

    public int getPrice() {
        return price;
    }

    public void setProds(HashMap<String, Integer> prods) {
        this.prods = prods;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
