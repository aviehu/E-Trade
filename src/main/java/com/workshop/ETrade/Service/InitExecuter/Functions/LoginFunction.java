package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class LoginFunction extends Function<Boolean> {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public LoginFunction() {
    }

    public LoginFunction(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Result<Boolean> execute() {
        String guest = service.enterSystem().getVal();
        return this.service.login(guest, userName, password);
    }
}
