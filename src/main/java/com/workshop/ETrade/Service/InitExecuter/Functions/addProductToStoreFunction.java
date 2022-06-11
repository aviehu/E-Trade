package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class addProductToStoreFunction extends Function<Boolean>{

    private String userName;
    private String storeName;
    private String productName;
    private int amount;
    private double price;
    private String category;

    public addProductToStoreFunction() {
    }

    public addProductToStoreFunction(String userName, String storeName, String productName, int amount, double price, String category) {
        this.userName = userName;
        this.storeName = storeName;
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.category = category;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public Result<Boolean> execute() {
        return this.service.addProductToStore(userName, storeName, productName, amount, price, category);
    }
}
