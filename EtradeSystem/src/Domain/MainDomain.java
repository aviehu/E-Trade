package Domain;

import Service.ResultPackge.ResultBool;

public class MainDomain {
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        Facade facade = new Facade();
        facade.enterSystem();
        System.out.println(facade.getOnline());
        ResultBool r = facade.login(facade.getOnline(),"member","member");
        if(r.getVal())
            System.out.println("good");
        else
            System.out.println(r.getErr());






    }
}
