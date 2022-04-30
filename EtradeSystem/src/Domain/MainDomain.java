package Domain;

import Service.ResultPackge.ResultBool;

public class MainDomain {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        Facade facade = new Facade();
        facade.enterSystem();
        System.out.println(facade.getOnline());
        ResultBool r = facade.login(facade.getOnline(),"domain","domain");
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
