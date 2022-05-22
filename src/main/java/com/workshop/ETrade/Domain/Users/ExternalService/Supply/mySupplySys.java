package com.workshop.ETrade.Domain.Users.ExternalService.Supply;

import com.workshop.ETrade.Domain.Users.Users.ShoppingCart;
import com.workshop.ETrade.Domain.Users.Users.StoreBasket;
import com.workshop.ETrade.Domain.Users.Users.SupplyAddress;

import java.util.HashMap;

public class mySupplySys {
    ISupply supplyAdapter;

    public mySupplySys() {
        this.supplyAdapter = new SupplyAdapter(null);
    }

    public boolean supply(ShoppingCart cart, SupplyAddress address) {
        HashMap<String,Integer> prods = new HashMap<>();
        for(StoreBasket b : cart.getBaskets()){
            prods.putAll(b.getProds());
        }
        PackageToShip pack = new PackageToShip(prods,cart.getTotalPrice());
        return supplyAdapter.supply(pack,address);
    }
    public boolean isExist(){
        return supplyAdapter.isExist();
    }
    public boolean changeSupply(SupplyAdaptee supplyAdaptee){
        this.supplyAdapter = new SupplyAdapter(supplyAdaptee);
        return true;
    }

}
