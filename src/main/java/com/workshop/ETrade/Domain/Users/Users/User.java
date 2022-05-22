package com.workshop.ETrade.Domain.Users.Users;

import com.workshop.ETrade.Domain.Stores.Store;

public abstract class User {
    protected ShoppingCart myShopCart;
    protected SupplyAddress address;
    protected boolean isConnected;
    protected String userName;
    protected CreditCard card;

    public ShoppingCart getMyShopCart() {
        return myShopCart;
    }

    public User() {
        this.myShopCart = new ShoppingCart(0);
        this.isConnected = false;
        this.address = null;
        this.card = null;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    // exitSystem:
    // guest loosing his shopCart and not consider as guest(users.2)
//    public abstract void exitSystem();

    public String addProdToCart(Store s, int quantity, String prodName){
            return myShopCart.addProd(s,quantity,prodName);
    }


    public String removeProd(Store s,int quantity,String prodName){
        return myShopCart.removeProd(s,quantity,prodName);
    }
    public String displayCart(){
        return myShopCart.displayCart();
    }

    public abstract String purchase(CreditCard card,SupplyAddress address);

    //getStoreInfo:
    //input:store -> output: store info and store's items info(2.1)
    public String getStoreInfo(Store s){
       return s.toString();
    }

    //searchProducts:
    //not in a specific store, search by product name/category/keyword
//    public void searchProducts(String keyword){
//
//    }
    //signIn:
    //create new Member object return it to controller to save it.
    public Member signUp(String userName,String password,String name,String lastName){
        return new Member(userName,password,name,lastName);
    }

    //logIn:
    //checks details with database
    public boolean logIn(String userName,String password){
        return true;
    }

    public void addAddress(String city,String street,int streetNum,int apartmentNum ){
            this.address = new SupplyAddress(city,street,streetNum,apartmentNum);
    }
    public boolean exitSystem(){
        this.isConnected = false;
        return true;
    }

    public String getUserName() {
        return userName;
    }

    public SupplyAddress getAddress() {
        return address;
    }

    public CreditCard getCard() {
        return card;
    }

    public void setAddress(SupplyAddress address) {
        this.address = address;
    }

    public void setCard(CreditCard card) {
        this.card = card;
    }

}
