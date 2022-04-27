package Domain.Users.Users;

import Domain.Stores.Store;

public class SystemManager extends Member{
    public SystemManager(String userName, String password) {
        super(userName, password);
    }

    @Override
    public boolean exitSystem() {
        return super.exitSystem();
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

    @Override
    public String displayCart() {
        return super.displayCart();
    }

    @Override
    public boolean purchase(CreditCard card, SupplyAddress address) {
        return super.purchase(card, address);
    }

    @Override
    public String getStoreInfo(Store s) {
        return super.getStoreInfo(s);
    }

    @Override
    public Member signIn(String userName, String password, int age, String mail, String city, String street, int streetNum, int apartementNum) {
        return super.signIn(userName, password, age, mail, city, street, streetNum, apartementNum);
    }

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
    public void setDiscount(int discount) {
        super.setDiscount(discount);
    }

    @Override
    public CreditCard getCard() {
        return super.getCard();
    }

    @Override
    public int getDiscount() {
        return super.getDiscount();
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
    public boolean logIn(String userName, String password) {
        return super.logIn(userName, password);
    }

    @Override
    public void setAddress(SupplyAddress address) {
        super.setAddress(address);
    }
}
