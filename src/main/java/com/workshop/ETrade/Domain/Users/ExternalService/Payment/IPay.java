package com.workshop.ETrade.Domain.Users.ExternalService.Payment;

import java.time.LocalTime;

public interface IPay {
    public int pay(int cardFrom, int month,int year,String holder, int cvv,int id);
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv,double price);
    public int getBalance(int card,LocalTime exp,int cvv);
    public boolean isExist();

    public int cancelPayment(int transId);
}
