package Domain.Users.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Member extends User{
    protected String userName;
    protected String password;
    protected int age;
    protected String mail;
    private MemberPurchaseHistory pHistory;
    protected int securityLvl;
    protected HashMap<String,String> securityQuests;

    protected SupplyAddress address;
    protected CreditCard card;
    protected int discount;


    @Override
    public void exitSystem() {
        //save details to database
    }

    public Member(String userName, String password, int age, String mail,String city,String street,int streetNum,int apartementNum) {
        super();
        this.discount = 10;
        this.myShopCart.setDiscount(discount);
        this.securityLvl = 0;
        this.securityQuests = new HashMap<>();
        this.pHistory = new MemberPurchaseHistory();
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.mail = mail;
        this.address = new SupplyAddress(city,street,streetNum,apartementNum);
        this.card = null;
    }

    @Override
    public ShoppingCart getMyShopCart() {
        return super.getMyShopCart();
    }

    @Override
    public void addProd(Store s, int quantity, String prodName) {
        super.addProd(s, quantity, prodName);
    }

    @Override
    public void removeProd(Store s, int quantity, String prodName) {
        super.removeProd(s, quantity, prodName);
    }

    @Override
    public String displayCart() {
        return super.displayCart();
    }

    @Override
    public boolean purchase(CreditCard card,SupplyAddress address) {
        if(myShopCart.purchaseCart(card,address)){
            for (StoreBasket b : myShopCart.getBaskets()){
                StoreBasket copy = b;
                pHistory.addToHistory(copy);
            }
            myShopCart.finishPurchase();
            return true;
        }
        else
            return false;
    }

    @Override
    public String getStoreInfo(Store s) {
        return super.getStoreInfo(s);
    }

//    @Override
//    public boolean logIn(String userName, String password) {
//        return super.logIn(userName, password);
//    }
    public Guest logOut() {
       return new Guest();
    }

//    //not sure
//    public StoreFounder openStore(String storeName){
//        Store s = new Store(this,storeName);
//        return new StoreFounder(s);
//
//    }

    public boolean writeReviewOnProduct(Store s,String prodName, String review){
        if(pHistory.isProdPurchased(prodName)) {
            s.writeReviewOnProd(prodName, review, this.userName);
            return true;
        }
        return false;
    }
    public boolean rateProduct(Store s,String prodName, int rate){
        if(pHistory.isProdPurchased(prodName)) {
            s.rateProd(prodName,rate,userName);
            return true;
        }
        return false;
    }

    public boolean RateStore(Store s,int rate){

        if(pHistory.isPurchasedFromStore(s.getName())){
            s.rateStore(rate,this.userName);
            return true;
        }
        return false;
    }
    public void sendMessageToStore(Store s,String message){
        s.askAs(message,userName);
    }



    @Override
    public void searchProducts(String prodName) {
        super.searchProducts(prodName);
    }

    @Override
    public Member signIn(String userName, String password, int age, String mail,String city,String street,int streetNum,int apartementNum) {
        return super.signIn(userName, password, age, mail,city,street,streetNum,apartementNum);
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
}
