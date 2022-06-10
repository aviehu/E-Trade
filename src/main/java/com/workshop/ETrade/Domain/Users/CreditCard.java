package com.workshop.ETrade.Domain.Users;

import java.time.LocalTime;

public class CreditCard {
    private String holderName;
    private String cardNumber;
    private int month;
    private int year;
    private int cvv;
    private int id;

    public CreditCard(String cardNumber, int month,int year, int cvv,int id,String holderName) {
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.id = id;
        this.holderName = holderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }


    public int getCvv() {
        return cvv;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getMonth() {
        return month;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
