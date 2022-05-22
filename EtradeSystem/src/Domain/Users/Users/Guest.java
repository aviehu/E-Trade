package Domain.Users.Users;

import Domain.Stores.Store;

public class Guest extends User {
    int id;
    String userName;
    @Override
    public ShoppingCart getMyShopCart() {
        return super.getMyShopCart();
    }

    public Guest(String userName) {
        super();
        this.userName = userName;
    }


    // exitSystem:
    // guest loosing his shopCart and not consider as guest(users.2)


    @Override
    public boolean exitSystem() {
        this.myShopCart = null;
        return super.exitSystem();
    }

    @Override
    public String addProdToCart(Store s, int quantity, String prodName) {
        return super.addProdToCart(s, quantity, prodName);
    }

    @Override
    public String removeProd(Store s, int quantity, String prodName) {
        return super.removeProd(s, quantity, prodName);
    }

    @Override
    public String displayCart() {
        return super.displayCart();
    }

    @Override
    public String purchase(CreditCard card,SupplyAddress address) {
        String ret = myShopCart.purchaseCart(card,address,userName);
        if(ret == null) {
            myShopCart.finishPurchase();
            return null;
        }
        else
            return ret;
    }

    @Override
    public String getStoreInfo(Store s) {
        return super.getStoreInfo(s);
    }

    @Override
    public Member signIn(String userName, String password, int age, String mail,String city,String street,int streetNum,int apartementNum) {
        return super.signIn(userName, password, age, mail,city,street,streetNum,apartementNum);
    }

    @Override
    public boolean logIn(String userName, String password) {
        return super.logIn(userName, password);
    }

    public String getUserName() {
        return userName;
    }

}
