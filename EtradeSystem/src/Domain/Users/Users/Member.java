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


    @Override
    public void exitSystem() {
        //save details to database
    }

    public Member(String userName, String password, int age, String mail) {
        super();
        this.securityLvl = 0;
        this.securityQuests = new HashMap<>();
        this.pHistory = new MemberPurchaseHistory();
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.mail = mail;
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
    public void purchase() {
        List<StoreBasket> bas;
        if((bas = myShopCart.purchase()) != null){
            for (StoreBasket b : bas){
                pHistory.addToHistory(b);
            }
        }
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


    @Override
    public void searchProducts(String prodName) {
        super.searchProducts(prodName);
    }

    @Override
    public Member signIn(String userName, String password, int age, String mail) {
        return super.signIn(userName, password, age, mail);
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
}
