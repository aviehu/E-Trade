package com.workshop.ETrade.Domain.Users.ExternalService.Payment;


import java.time.LocalTime;

public class PaymentAdapter implements IPay {
    private PaymentAdaptee paymentAdaptee;

    public PaymentAdapter(PaymentAdaptee extPaySys) {
        this.paymentAdaptee = extPaySys;
    }

    @Override
    public int pay(String cardNumber, int month,int year,String holder, int cvv, int id) {
        if(paymentAdaptee == null)
            return 1;
        else {

            return paymentAdaptee.payment(cardNumber, month,year,holder, cvv,id);
        }
    }

    @Override
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv, double price) {
        if(paymentAdaptee == null){
            if (expDate.isBefore(LocalTime.now()))
                return false;
            return true;
        }
        return paymentAdaptee.canPay(cardFrom,expDate,cvv,price);
    }

    @Override
    public int getBalance(int card, LocalTime exp, int cvv) {
        if(paymentAdaptee == null)
            return 0;
        return paymentAdaptee.getBalance(card, exp, cvv);
    }

    @Override
    public boolean isExist() {
        if(paymentAdaptee == null)
            return false;
        return paymentAdaptee.handShake();
    }

    @Override
    public int cancelPayment(int transId) {
        if(paymentAdaptee == null)
            return -1;
        return paymentAdaptee.cancelPayment(transId);
    }
}
