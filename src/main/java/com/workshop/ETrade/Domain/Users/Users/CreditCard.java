package com.workshop.ETrade.Domain.Users.Users;

import java.time.LocalTime;

public class CreditCard {
    private int cardNumber;
    private LocalTime expDate;
    private int cvv;

    public CreditCard(int cardNumber, LocalTime expDate, int cvv) {
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.cvv = cvv;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public LocalTime getExpDate() {
        return expDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpDate(LocalTime expDate) {
        this.expDate = expDate;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
