package com.workshop.ETrade.Domain.Users;

import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Notifications.NotificationManager;
import com.workshop.ETrade.Domain.Pair;
import com.workshop.ETrade.Domain.Stores.Bid;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;
import com.workshop.ETrade.Service.ResultPackge.Result;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;
import java.util.List;

public abstract class User {
    protected ShoppingCart myShopCart;
    protected SupplyAddress address;
    protected boolean isConnected;
    protected String userName;
    protected CreditCard card;
    protected NotificationManager notificationManager;

    public ShoppingCart getMyShopCart() {
        return myShopCart;
    }

    public User() {
        //this.myShopCart = new ShoppingCart(0, userName);
        this.isConnected = false;
        this.address = null;
        this.card = null;
        this.notificationManager = new NotificationManager();
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

//    public String addProdToCart(Store s, int quantity, String prodName){
//            return myShopCart.addProd(s,quantity,prodName);
//    }


    public String removeProd(Store s,int quantity,String prodName){
        return myShopCart.removeProd(s,quantity,prodName);
    }
    public HashMap<String, Pair<Integer, String>> displayCart(){
        return myShopCart.displayCart();
    }

    public abstract Result<List<String>> purchase(CreditCard card, SupplyAddress address);

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

    public void addAddress(String country,String city, String street, int streetNum, int apartmentNum, int zip ){
            this.address = new SupplyAddress(country, city, street, streetNum, apartmentNum, zip);
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

    public void update(String message, String from) {
        notificationManager.sendNotification(this,message,from);
    }
    public void updateStats(){
        notificationManager.updateStats();
    }

    public void purchaseBid(Bid approved) {
        CreditCard card = approved.getCard();
        SupplyAddress address = approved.getSupplyAddress();
        String cardFrom = approved.getCard().getCardNumber();
        int month = card.getMonth();
        int year = card.getYear();
        int cvv = card.getCvv();
        int id = card.getId();
        String holderName = card.getHolderName();
        ExtSysController extSystems = ExtSysController.getInstance();
        int payTransactionId = extSystems.pay(cardFrom, month,year,holderName, cvv,id);
        //payment
        if(payTransactionId != -1) {//can charge payment
            int supTransactionId =extSystems.supply(userName, address);
            if (supTransactionId == -1) {
                extSystems.cancelPayment(payTransactionId);
                extSystems.cancelSup(supTransactionId);
            }
        } else {
            extSystems.cancelPayment(payTransactionId);
        }
    }

    public void addToAwaitingNotification(Notification notification) {
    }
}
