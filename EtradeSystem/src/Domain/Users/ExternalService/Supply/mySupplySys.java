package Domain.Users.ExternalService.Supply;

import Domain.Users.ExternalService.Payment.IPay;
import Domain.Users.ExternalService.Payment.PaymentAdapter;
import Domain.Users.Users.ShoppingCart;
import Domain.Users.Users.StoreBasket;
import Domain.Users.Users.SupplyAddress;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

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

}
