package Domain.Users.Users;

import java.util.ArrayList;
import java.util.List;

public class Owner extends Member {

    boolean isFounder;
    List<Owner> myOwners;
    List<Manager> myManegers;

    public Owner(String userName, String password, int age, String mail, String city, String street, int streetNum, int apartementNum,boolean isFounder) {
        super(userName, password, age, mail, city, street, streetNum, apartementNum);
        this.isFounder = isFounder;
        this.myManegers = new ArrayList<>();
        this.myOwners = new ArrayList<>();
    }



    @Override
    public void exitSystem() {
        super.exitSystem();
    }

    @Override
    public Guest logOut() {
        return super.logOut();
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
    public int getDiscount() {
        return super.getDiscount();
    }
}
