package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class appointStoreOwnerFunction extends Function<String>{

    private String userName;
    private String storeName;
    private String newOwner;

    public appointStoreOwnerFunction() {
    }

    public appointStoreOwnerFunction(String userName, String storeName, String newOwner) {
        this.userName = userName;
        this.storeName = storeName;
        this.newOwner = newOwner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner;
    }

    @Override
    public Result<String> execute() {
        return this.service.appointStoreOwner(userName, storeName, newOwner);
    }
}
