package com.workshop.ETrade.Domain.Users.ExternalService.Supply;

import com.workshop.ETrade.Domain.Users.Users.SupplyAddress;

import java.util.ArrayList;
import java.util.List;

public class SupplyAdapter implements ISupply{
    SupplyAdaptee supplyAdaptee;
    List<String> citiesCanSup;

    public SupplyAdapter(SupplyAdaptee supplyAdaptee) {
        this.supplyAdaptee = supplyAdaptee;
        this.citiesCanSup = new ArrayList<>();
        init();
    }
    public void init(){
        citiesCanSup.add("TelAviv");
        citiesCanSup.add("BeerSheva");
    }
    public boolean checkCity(String city){
        if(citiesCanSup.contains(city))
            return true;
        return false;
    }

    @Override
    public boolean supply(PackageToShip packageToShip, SupplyAddress address) {
        if(supplyAdaptee == null) {
            if (checkCity(address.getCity()) && packageToShip != null)
                return true;
            return false;
        }
        else
            return supplyAdaptee.supply(packageToShip, address);
    }

    @Override
    public boolean isExist() {
        if(supplyAdaptee != null)
            return true;
        return false;
    }

}
