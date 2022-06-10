package com.workshop.ETrade.Domain.Users.ExternalService.Payment;

import java.time.LocalTime;

public class MyPaymentSys {
    IPay realPaymentSys;

    public MyPaymentSys(PaymentAdapter paymentAdapter) {
        this.realPaymentSys = paymentAdapter;
    }

    public int pay(String cardNumber, int month,int year,String holder, int cvv, int id) {
        return realPaymentSys.pay(cardNumber, month, year,holder, cvv,id);
    }
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv,double price){
        return realPaymentSys.canPay(cardFrom, expDate, cvv, price);
    }
    public boolean isExist(){
        return realPaymentSys.isExist();
    }
    public boolean changePayment(PaymentAdaptee paymentAdaptee){
        this.realPaymentSys = new PaymentAdapter(paymentAdaptee);
        return true;
    }
    public int getBalance(int card, LocalTime exp, int cvv) {
        return this.realPaymentSys.getBalance(card, exp, cvv);
    }

    public int cancelPayment(int transId) {
        return this.realPaymentSys.cancelPayment(transId);
    }

}
