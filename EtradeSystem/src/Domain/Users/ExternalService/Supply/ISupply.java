package Domain.Users.ExternalService.Supply;

import Domain.Users.Users.StoreBasket;
import Domain.Users.Users.SupplyAddress;

import java.util.List;

public interface ISupply {
    public boolean supply(PackageToShip packageToShip , SupplyAddress address);
    public boolean isExist();

}
