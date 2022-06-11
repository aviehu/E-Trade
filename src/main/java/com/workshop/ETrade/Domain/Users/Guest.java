package com.workshop.ETrade.Domain.Users;

import com.workshop.ETrade.Domain.Pair;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Service.ResultPackge.Result;

import java.util.HashMap;
import java.util.List;

public class Guest extends User {
    int id;
    String userName;
    @Override
    public ShoppingCart getMyShopCart() {
        return super.getMyShopCart();
    }

    public Guest(String userName) {
        super();
        this.myShopCart = new ShoppingCart(0,userName);
        this.userName = userName;
    }


    // exitSystem:
    // guest loosing his shopCart and not consider as guest(users.2)


    @Override
    public boolean exitSystem() {
        this.myShopCart = null;
        return super.exitSystem();
    }

    public String addProdToCart(Store s, int quantity, String prodName) {
        return myShopCart.addProd(s, quantity, prodName);
    }

    @Override
    public String removeProd(Store s, int quantity, String prodName) {
        return myShopCart.removeProd(s, quantity, prodName);
    }

    @Override
    public HashMap<String, Pair<Integer,String>> displayCart() {
        return myShopCart.displayCart();
    }

    @Override
    public Result<List<String>> purchase(CreditCard card, SupplyAddress address) {
        Result<List<String>> ret = myShopCart.purchaseCart(card,address,userName);
        if(ret.isSuccess())
            myShopCart.finishPurchase();
        return ret;
    }

    @Override
    public String getStoreInfo(Store s) {
        return super.getStoreInfo(s);
    }

    @Override
    public Member signUp(String userName, String password, String name, String lastName) {
        return super.signUp(userName, password, name, lastName);
    }

    @Override
    public boolean logIn(String userName, String password) {
        return super.logIn(userName, password);
    }

    public String getUserName() {
        return userName;
    }

}
