package com.workshop.ETrade.Domain.Users.ExternalService.Supply;

import com.workshop.ETrade.Domain.Users.Users.SupplyAddress;

public interface ISupply {
    public int supply(String name,String street,String city,String country,int zip);
    public boolean isExist();

    int cancelSupply(int transId);
}
