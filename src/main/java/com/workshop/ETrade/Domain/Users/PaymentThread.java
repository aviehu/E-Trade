package com.workshop.ETrade.Domain.Users;

import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;

public class PaymentThread extends Thread{
    String cardForm;
    int month;
    int year;
    String holderName;
    int cvv;
    int id;
    Answer ans;

    public PaymentThread(String cardForm, int month, int year, String holderName, int cvv, int id, Answer ans) {
        this.cardForm = cardForm;
        this.month = month;
        this.year = year;
        this.holderName = holderName;
        this.cvv = cvv;
        this.id = id;
        this.ans = ans;
    }

    public void run() {
        ans.ans = ExtSysController.getInstance().pay(cardForm, month,year,holderName, cvv,id);
    }
}
