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
    public int supply(String name,String street,String city,String country,int zip) {
        if(supplyAdaptee == null) {
            if (checkCity(city))
                return 1;
            return -1;
        }
        else
            return supplyAdaptee.supply(name, street, city, country, zip);
    }

    @Override
    public boolean isExist() {
        if(supplyAdaptee != null)
            return supplyAdaptee.handShake();
        return false;
    }

    @Override
    public int cancelSupply(int transId) {
        if(supplyAdaptee == null)
            return -1;
        return supplyAdaptee.cancelSupply(transId);
    }

}
