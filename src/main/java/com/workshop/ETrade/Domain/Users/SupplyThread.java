package com.workshop.ETrade.Domain.Users;

import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;

public class SupplyThread extends Thread{
    String userName;
    SupplyAddress address;
    Answer ans;

    public SupplyThread(String userName, SupplyAddress address, Answer ans) {
        this.userName = userName;
        this.address = address;
        this.ans = ans;
    }

    public void run() {
        ans.ans = ExtSysController.getInstance().supply(userName, address);
    }
}
