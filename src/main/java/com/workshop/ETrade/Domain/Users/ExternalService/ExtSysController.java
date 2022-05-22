package com.workshop.ETrade.Domain.Users.ExternalService;

import com.workshop.ETrade.Domain.Users.ExternalService.Payment.MyPaymentSys;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Security.mySecuritySys;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.mySupplySys;
import com.workshop.ETrade.Domain.Users.Users.ShoppingCart;
import com.workshop.ETrade.Domain.Users.Users.SupplyAddress;

import java.time.LocalTime;

public class ExtSysController {
    private static ExtSysController myInstance = null;
    private mySupplySys supply;
    private MyPaymentSys payment;
    private mySecuritySys security;

    private ExtSysController() {
        payment = new MyPaymentSys();
        supply = new mySupplySys();
        security = new mySecuritySys();
    }

    public static ExtSysController getInstance(){
        if(myInstance == null){
            myInstance = new ExtSysController();
        }
        return myInstance;
    }

    public boolean pay(int card, LocalTime expDate, int cvv, double price,int cardTo){
        return payment.pay(card, expDate, cvv, price,cardTo);
    }
    public boolean canPay(int card, LocalTime expDate, int cvv, double price){
        return payment.canPay(card, expDate, cvv, price);
    }
    public boolean supply(ShoppingCart cart, SupplyAddress address){
        return supply.supply(cart, address);
    }
    public boolean isExistSupply(){
        return getInstance().supply.isExist();
    }
    public boolean isExistPayment(){
        return getInstance().payment.isExist();
    }
    public boolean changePayment(PaymentAdaptee paymentAdaptee){
        return this.payment.changePayment(paymentAdaptee);
    }
    public boolean changeSupply(SupplyAdaptee supplyAdaptee){
        return this.supply.changeSupply(supplyAdaptee);
    }

    public String encode(String pass){
        return security.encode(pass);
    }
    public String decode(String pass){
        return security.decode(pass);
    }
    public boolean isExistSecurity(){
        return security.isExist();
    }
    public int getBalance(int card, LocalTime exp, int cvv) {
        return payment.getBalance(card, exp, cvv);
    }


}
