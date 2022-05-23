package com.workshop.ETrade.Domain.Users.ExternalService.Supply;

import java.util.HashMap;

public class PackageToShip {
    HashMap<String,Integer> prods;
    Double price;

    public PackageToShip(HashMap<String, Integer> prods, Double price) {
        this.prods = prods;
        this.price = price;
    }

    public HashMap<String, Integer> getProds() {
        return prods;
    }

    public Double getPrice() {
        return price;
    }

    public void setProds(HashMap<String, Integer> prods) {
        this.prods = prods;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
