package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class addSysManagerFunction extends Function<Boolean>{
    String userName;
    String managerToAdd;

    public addSysManagerFunction() {
    }



    public addSysManagerFunction(String userName, String managerToAdd) {
        this.userName = userName;
        this.managerToAdd = managerToAdd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getManagerToAdd() {
        return managerToAdd;
    }

    public void setManagerToAdd(String managerToAdd) {
        this.managerToAdd = managerToAdd;
    }
    @Override
    public Result<Boolean> execute() {
        return this.service.addSystemManager(userName,managerToAdd);
    }
}
