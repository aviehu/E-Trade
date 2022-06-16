package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class logOutFunction extends Function<String>{
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public logOutFunction() {
    }

    public logOutFunction(String userName) {
        this.userName = userName;
    }

    @Override
    public Result<String> execute() {
        return this.service.logOut(userName);
    }
}
