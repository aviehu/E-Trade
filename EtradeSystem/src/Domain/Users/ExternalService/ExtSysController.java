package Domain.Users.ExternalService;

import Domain.Users.ExternalService.Payment.MyPaymentSys;
import Domain.Users.ExternalService.Supply.mySupplySys;
import Domain.Users.Users.ShoppingCart;
import Domain.Users.Users.SupplyAddress;

import java.time.LocalTime;

public class ExtSysController {
    private static ExtSysController myInstance = null;
    private mySupplySys supply;
    private MyPaymentSys payment;

    private ExtSysController() {
    }

    public static ExtSysController getInstance(){
        if(myInstance == null){
            myInstance = new ExtSysController();
        }
        return myInstance;
    }

    public boolean pay(int card, LocalTime expDate, int cvv, int price,int cardTo){
        return payment.pay(card, expDate, cvv, price,cardTo);
    }
    public boolean canPay(int card, LocalTime expDate, int cvv, int price){
        return payment.canPay(card, expDate, cvv, price);
    }
    public boolean supply(ShoppingCart cart, SupplyAddress address){
        return supply.supply(cart, address);
    }

}
