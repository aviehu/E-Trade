package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class SignUpFunction extends Function<Boolean>{
    private String guestName;
    private String userName;
    private String password;
    private String name;
    private String lastName;

    public SignUpFunction() {
    }

    public SignUpFunction(String userName, String password, String name, String lastName) {
        this.guestName = service.enterSystem().getVal();
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Result<Boolean> execute() {
        this.guestName = service.enterSystem().getVal();
        return this.service.signUp(guestName, userName, password, name, lastName);
    }


}

