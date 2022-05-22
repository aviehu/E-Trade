package com.workshop.ETrade.Domain.Users.ExternalService.Supply;

import com.workshop.ETrade.Domain.Users.Users.SupplyAddress;

public interface ISupply {
    public boolean supply(PackageToShip packageToShip , SupplyAddress address);
    public boolean isExist();

}
