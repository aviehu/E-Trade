package com.workshop.ETrade.Domain.Users.ExternalService;

import com.workshop.ETrade.Domain.Users.ExternalService.Payment.MyPaymentSys;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdapter;
import com.workshop.ETrade.Domain.Users.ExternalService.Security.mySecuritySys;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdapter;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.mySupplySys;
import com.workshop.ETrade.Domain.Users.SupplyAddress;

import java.time.LocalTime;

public class ExtSysController {
    private static ExtSysController myInstance = null;
    private mySupplySys supply;
    private MyPaymentSys payment;
    private mySecuritySys security;
    private MyPaymentSys realPay;
    private mySupplySys realSup;

    private ExtSysController() {
        HttpClient httpClient = new HttpClient();
        realPay = new MyPaymentSys(new PaymentAdapter(new PaymentAdaptee(httpClient)));
        realSup = new mySupplySys(new SupplyAdapter(new SupplyAdaptee(httpClient)));
        payment = realPay;
        supply = realSup;
        security = new mySecuritySys();

    }

    public static ExtSysController getInstance(){
        if(myInstance == null){
            myInstance = new ExtSysController();
        }
        return myInstance;
    }

    public int pay(String card, int month,int year,String holder, int cvv, int id){
        return payment.pay(card, month,year,holder, cvv, id);
    }
    public boolean canPay(int card, LocalTime expDate, int cvv, double price){
        return payment.canPay(card, expDate, cvv, price);
    }
    public int cancelPayment(int transId){
        return payment.cancelPayment(transId);
    }
    public int cancelSup(int transId){
        return supply.cancelSup(transId);
    }
    public int supply(String name, SupplyAddress address){
        return supply.supply(name,address.getStreet(),address.getCity(),address.getCountry(),address.getZip());
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
    public void setPayment(boolean isRealPayment){
        if(isRealPayment)
            this.payment =realPay;
        else
            this.payment = new MyPaymentSys(new PaymentAdapter(new PaymentAdaptee(null)));
    }
    public void setSupply(boolean isRealSup){
        if(isRealSup)
            this.supply =realSup;
        else
            this.supply = new mySupplySys(new SupplyAdapter(new SupplyAdaptee(null)));
    }

}
