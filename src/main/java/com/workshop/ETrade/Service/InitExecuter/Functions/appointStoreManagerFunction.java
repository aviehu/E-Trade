package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class appointStoreManagerFunction extends Function<Boolean>{
    private String userName;
    private String storeName;
    private String newManager;

    public appointStoreManagerFunction() {
    }

    public appointStoreManagerFunction(String userName, String storeName, String newManager) {
        this.userName = userName;
        this.storeName = storeName;
        this.newManager = newManager;
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

    public String getNewManager() {
        return newManager;
    }

    public void setNewOwner(String newManager) {
        this.newManager = newManager;
    }

    @Override
    public Result<Boolean> execute() {
        return this.service.appointStoreManager(userName, storeName, newManager);
    }

}
