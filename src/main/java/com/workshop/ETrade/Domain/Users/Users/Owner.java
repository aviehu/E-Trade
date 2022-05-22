package com.workshop.ETrade.Domain.Users.Users;

import com.workshop.ETrade.Domain.Stores.Store;

public class Owner extends Member {


    public Owner(String userName, String password,String name,String lastName) {
        super(userName, password,name,lastName);
        //this.isFounder = isFounder;
    }

    @Override
    public ShoppingCart getMyShopCart() {
        return super.getMyShopCart();
    }

    @Override
    public String addProdToCart(Store store, int quantity, String prodName) {
        return super.addProdToCart(store, quantity, prodName);
    }



    @Override
    public String removeProd(Store s, int quantity, String prodName) {
        return super.removeProd(s, quantity, prodName);
    }

//    public boolean addProdToStore(Store store, int quantity, String prodName,int price,String category) {
//        return store.addProduct(userName,prodName,quantity,price,category);
//    }
//    public boolean removeProdFromStore(Store store, int quantity, String prodName,int price,String category) {
//        return store.removeProduct(userName,prodName);
//    }
//    public boolean setProdPrice(Store store, int quantity, String prodName,int price) {
//        return store.changeProductPrice(userName,prodName,price);
//    }
//    public boolean setProdName(Store store, int quantity, String prodName,String newName) {
//        return store.changeProductName(userName,prodName,newName);
//    }
//    public boolean setProdPurOption(Store store, String prodName, purchaseOption pp) {
//        return store.setPurchaseOption(userName,prodName,pp);
//    }
//    public boolean appointOwner(Store s,String appointee){
//        return s.addOwner(userName,appointee);
//    }
//    public boolean appointManager(Store s,String appointee){
//        return s.addManager(userName,appointee);
//    }
//    public boolean changeManagerPermission(Store s,String manager, ManagementPermission p){
//        return s.changeStoreManagersPermission(userName,manager,p);
//    }


    @Override
    public String displayCart() {
        return super.displayCart();
    }

    @Override
    public String purchase(CreditCard card, SupplyAddress address) {
        return super.purchase(card, address);
    }

    @Override
    public String getStoreInfo(Store s) {
        return super.getStoreInfo(s);
    }

//    @Override
//    public Member signIn(String userName, String password, int age, String mail, String city, String street, int streetNum, int apartementNum) {
//        return super.signIn(userName, password, age, mail, city, street, streetNum, apartementNum);
//    }

    @Override
    public void setDiscount(int discount) {
        super.setDiscount(discount);
    }

    @Override
    public CreditCard getCard() {
        return super.getCard();
    }

    @Override
    public void setCard(CreditCard card) {
        super.setCard(card);
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
    public void addAddress(String city, String street, int streetNum, int apartmentNum) {
        super.addAddress(city, street, streetNum, apartmentNum);
    }

    @Override
    public boolean exitSystem() {
        return super.exitSystem();
    }

//    @Override
//    public Guest logOut() {
//        return super.logOut();
//    }

    @Override
    public void addSecurityQuest(String quest, String ans) {
        super.addSecurityQuest(quest, ans);
    }

    @Override
    public void removeSecurityQuest(String quest) {
        super.removeSecurityQuest(quest);
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);
    }

    @Override
    public String getMail() {
        return super.getMail();
    }

    @Override
    public void setMail(String mail) {
        super.setMail(mail);
    }

    @Override
    public int getSecurityLvl() {
        return super.getSecurityLvl();
    }

    @Override
    public SupplyAddress getAddress() {
        return super.getAddress();
    }

    @Override
    public void setCity(String city) {
        super.setCity(city);
    }

    @Override
    public void setStreet(String street) {
        super.setStreet(street);
    }

    @Override
    public void setStreetNum(int sNum) {
        super.setStreetNum(sNum);
    }

    @Override
    public void setApartmentNum(int apartmentNum) {
        super.setApartmentNum(apartmentNum);
    }

    @Override
    public int getDiscount() {
        return super.getDiscount();
    }
}
