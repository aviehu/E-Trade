package com.workshop.ETrade.Domain;

import com.workshop.ETrade.Service.ResultPackge.Result;

public class MainDomain {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        Facade facade = new Facade();
        Result<Boolean> r = facade.login(facade.enterSystem().getVal(),"domain","domain");
        if(r.getVal()) {
            System.out.println("good");
            facade.openStore("domain","hila",123);
            facade.addProductToStore("domain","hila","apple",100,10,"fruits");
            String cart = facade.addProductToShoppingCart("domain","apple","hila",2).getVal();
            System.out.println(cart);
        }
        else
            System.out.println(r.getErr());






    }
}
