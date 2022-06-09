package com.workshop.ETrade.Domain.Users.Users;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Notifications.NotificationManager;
import com.workshop.ETrade.Domain.Observer;
import com.workshop.ETrade.Domain.Stores.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Member extends User implements Observer {
    protected NotificationManager notificationManager;
    protected List<Notification> awaitingNotification;
    protected String userName;
    protected String password;
    protected int age;
    protected String name;
    protected String lastName;
    protected String mail;
    private MemberPurchaseHistory pHistory;
    protected int securityLvl;
    protected HashMap<String,String> securityQuests;

    protected CreditCard card;
    protected int discount;

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean exitSystem() {
        return super.exitSystem();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Member(String userName, String password, String name, String lastName) {
        super();
        this.discount = 0;
        this.myShopCart.setDiscount(discount);
        this.securityLvl = 0;
        this.securityQuests = new HashMap<>();
        this.pHistory = new MemberPurchaseHistory();
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = 0;
        this.mail = "";
        this.address = null;
        this.notificationManager = new NotificationManager();
        this.awaitingNotification = new ArrayList<>();
    }

    @Override
    public ShoppingCart getMyShopCart() {
        return super.getMyShopCart();
    }

    @Override
    public String addProdToCart(Store store, int quantity, String prodName) {
         return super.addProdToCart(store,quantity,prodName);
    }

    @Override
    public String removeProd(Store s, int quantity, String prodName) {
        return super.removeProd(s, quantity, prodName);
    }

    @Override
    public HashMap<String,Integer> displayCart() {
        return super.displayCart();
    }

    @Override
    public String purchase(CreditCard card,SupplyAddress address) {
        String ret = myShopCart.purchaseCart(card,address,userName);
        if(ret == null){
            for (StoreBasket b : myShopCart.getBaskets()){
                StoreBasket copy = b;
                pHistory.addToHistory(copy);
            }
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

//    @Override
//    public boolean logIn(String userName, String password) {
//        return super.logIn(userName, password);
//    }
//    public Guest logOut() {
//       return new Guest();
//    }

//    //not sure
//    public boolean openStore(String storeName,int card){
//        Store s = new Store(storeName,this.userName,card);
//    }

//    public boolean writeReviewOnProduct(Store s,String prodName, String review){
//        if(pHistory.isProdPurchased(prodName)) {
//            s.writeReviewOnProd(prodName, review, this.userName);
//            return true;
//        }
//        return false;
//    }
//    public boolean rateProduct(Store s,String prodName, int rate){
//        if(pHistory.isProdPurchased(prodName)) {
//            s.rateProd(prodName,rate,userName);
//            return true;
//        }
//        return false;
//    }

//    public boolean RateStore(Store s, int rate){
//
//        if(pHistory.isPurchasedFromStore(s.getName())){
//            s.rateStore(rate,this.userName);
//            return true;
//        }
//        return false;
//    }
//    public void sendMessageToStore(Store s,String message){
//        s.askAs(message,userName);
//    }



//    @Override
//    public void searchProducts(String prodName) {
//        super.searchProducts(prodName);
//    }

    @Override
    public Member signUp(String userName, String password, String name,String lastName) {
        return super.signUp(userName, password,name,lastName);
    }

    public void addSecurityQuest(String quest,String ans){
        if(!securityQuests.containsKey(quest)) {
            securityQuests.put(quest, ans);
            securityLvl++;
        }
    }
    public void removeSecurityQuest(String quest){
        if(!securityQuests.containsKey(quest)) {
            securityQuests.remove(quest);
            securityLvl--;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getSecurityLvl() {
        return securityLvl;
    }

    public SupplyAddress getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.address.setCity(city);
    }
    public void setStreet(String street) {
        this.address.setStreet(street);
    }
    public void setStreetNum(int sNum) {
        this.address.setStreetNum(sNum);
    }
    public void setApartmentNum(int apartmentNum) {
        this.address.setApartmentNum(apartmentNum);
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        this.myShopCart.setDiscount(discount);
    }

    public CreditCard getCard() {
        return card;
    }

    public int getDiscount() {
        return discount;
    }


    public void setCard(CreditCard card) {
        this.card = card;
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    @Override
    public void setConnected(boolean connected) {
        super.setConnected(connected);
    }

    @Override
    public void addAddress(String country,String city, String street, int streetNum, int apartmentNum, int zip) {
        super.addAddress(country, city, street, streetNum, apartmentNum, zip);
    }

    @Override
    public void update(String message, String from) {
        notificationManager.sendNotification(this,message,from);
    }

    @Override
    public void addToAwaitingNotification(Notification notification) {
        this.awaitingNotification.add(notification);
    }

    public List<Notification> getMessages() {
        return awaitingNotification;
    }
}
