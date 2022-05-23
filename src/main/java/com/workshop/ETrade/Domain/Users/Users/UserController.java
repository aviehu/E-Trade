package com.workshop.ETrade.Domain.Users.Users;

import com.workshop.ETrade.Domain.Stores.Store;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class UserController {
    public static int guestId;
    List<Member> members;
    public static int memberDiscount;

    private List<Guest> guests;
    private List<Member> systemManagers;
    private List<User> users;
    private Guest online;
    private Logger logger = Logger.getLogger("users");

    public UserController() {
        guestId = 0;
        memberDiscount = 0;
        this.members = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.systemManagers = new ArrayList<>();
        users = new ArrayList<>();
        try {
            Handler fileHandler = new FileHandler(System.getProperty("user.dir") + "/users.log", 2000, 5);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("error while creating logger for users");
        }
        init();
    }
    public void init(){
        //load from database
        Member systemManager = new Member("domain","domain", "domain","domain");
//        Member member = new Member("member","member");
//        members.add(member);
        members.add(systemManager);
        systemManagers.add(systemManager);
        users.addAll(members);
        users.addAll(guests);
        users.addAll(systemManagers);
    }

   public int getCartPrice(String userName){
       User u = getUser(userName);
       if (u == null)
           return -1;
      return u.getMyShopCart().getTotalPrice();
   }

    public String addSystemManager(String userName,String managerToAdd){
        Member u = getMember(userName);
        if(systemManagers.contains(u)){
            Member m = getMember(managerToAdd);
            if(!systemManagers.contains(m)){
                if(m != null){
                    Member sm = new Member(m.getUserName(), m.getPassword(),m.getName(),m.getLastName());
                    members.remove(m);
                    systemManagers.add(sm);
                    users.add(sm);
                    logger.info("new system manager - " + managerToAdd);
                    return null;
                }
                else
                    return managerToAdd+ " is not a registered member\n";
            }else
                return managerToAdd+" is already a system manager\n";
        }
        else
            return "You don't have permission to add system manager\n";
    }
    public boolean signUp(String userName,String password,String name,String lastName) {
        for (Member m : members) {
            if (userName.equals(m.getUserName()))
                return false;
        }
        Member m = new Member(userName, password,name,lastName);
        members.add(m);
        users.add(m);
        logger.info("new user - " + userName);
        return true;
    }
    public String enterSystem(){
        online = new Guest("guest"+guestId);
        online.setConnected(true);
        this.guests.add(online);
        this.users.add(online);
        logger.info("new guest - guest" + guestId);
        guestId++;
        return online.getUserName();
    }
    public String logIn(String userName,String password){
        for(Member m : members){
            if(m.getUserName().equals(userName)) {
                if (m.getPassword().equals(password)) {
                    m.setConnected(true);
                    logger.info(userName + " has logged in");
                    return null;

                }
                else
                    return "Wrong password\n";
            }
        }
        return "Wrong user-name\n";
    }
    public boolean exitSystem(String userName){
        User u = getUser(userName);
        if(u != null) {
            Member m = getMember(userName);
            if (m != null) {
                //save to data
            }
            return u.exitSystem();
        }
        return false;
    }
    public String logOut(String userName){
        Member m = getMember(userName);
        if(m != null){
            //save to date base
            m.setConnected(false);
            return enterSystem();
        }
        return null;

    }
    public boolean isConnected(String userName){
        User u;
        if((u = getMember(userName)) != null)
            return u.isConnected();
        else if((u = getGuest(userName)) != null)
            return u.isConnected();
        else
            return false;
    }
    public Member getMember(String userName){
        for(Member m : members){
            if(m.getUserName().equals(userName))
                return m;
        }
        return null;
    }
    public Guest getGuest(String userName){
        for(Guest g : guests){
            if(g.getUserName().equals(userName))
                return g;
        }
        return null;
    }
    public User getUser(String userName){
        for(User u : users){
            if(u.getUserName().equals(userName))
                return u;
        }
        return null;
    }
    public String addProductToShoppingCart(String userName, String productName, Store storeName, int quantity){
       User user = getUser(userName);
       if(user != null) {
           logger.info("product - " + productName + " has been added to cart");
           return user.addProdToCart(storeName,quantity,productName);
       }
       return "User "+userName+" does not exist\n";
    }
    public String removeProductFromShoppingCart(String userName,Store s,int quantity,String prodName){
        User user = getUser(userName);
        if(user != null)
            return user.removeProd(s,quantity,prodName);
        return "User: "+userName+" does not exist\n";
    }
    public String displayShoppingCart(String userName){
        User user = getUser(userName);
        if(user != null)
            return user.displayCart();
        return "User: "+userName+" does not exist\n";
    }
    public synchronized String purchase(String userName, int creditCard, LocalTime expDate,int cvv,String city,String street,int stNum,int apartmentNum){
        User user = getUser(userName);
        SupplyAddress sa;
        if(user != null) {
            logger.info("new purchase by - " + userName);
            if(user.getCard() == null) {
                CreditCard card = new CreditCard(creditCard, expDate, cvv);
                if (user.getAddress() == null) {
                    sa = new SupplyAddress(city, street, stNum, apartmentNum);
                    return user.purchase(card, sa);
                } else {
                    return user.purchase(card, user.getAddress());
                }
            }
            else if (user.getAddress() == null) {
                sa = new SupplyAddress(city, street, stNum, apartmentNum);
                return user.purchase(user.getCard(), sa);
            } else
                return user.purchase(user.getCard(), user.getAddress());
        }
        return "User: "+userName+" does not exist\n";
    }
    public boolean isValidPassword(String password){
        return password != null && !password.equals("");
        //return password.length() >= 8 && containsUpperCase(password);
    }
    public boolean isUserNameExist(String userName){
        User u = getUser(userName);
        return u != null;
    }

    public boolean containsUpperCase(String pass) {
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isUpperCase(pass.charAt(i))) {
                return true;
            }
        }

        return false;
    }
    public Member getSysManager(String userName){
        for(Member sm : systemManagers){
            if(sm.getUserName().equals(userName))
                return sm;
        }
        return null;
    }
    public boolean removeSystemManager(String userName,String managerToRemove){
        if(systemManagers.size() < 1)
            return false;
        Member sm1 = getSysManager(userName);
        Member sm2 = getSysManager(managerToRemove);
        if(sm1 != null && sm2 != null){
            Member m = new Member(sm2.getUserName(),sm2.getPassword(),sm2.getName(),sm2.getLastName());
            members.add(m);
            systemManagers.remove(sm2);
            logger.info("removed system manager - " + managerToRemove);
            return true;
        }
        return false;


    }
    public String removeMember(String userName,String memberToRemove){
        Member sm = getSysManager(userName);
        Member m = getMember(memberToRemove);
        if(sm != null){
            if(m != null){
                members.remove(m);
                users.remove(m);
                //return "Successfully removed "+ memberToRemove+" from the system\n";
                logger.info("removed member - " + memberToRemove);
                return null;
            }
            else
                return "Failed, "+memberToRemove + " is already not a member\n";
        }
        else
            return "You don't have permission to remove a member\n";
    }
    public boolean isUserSysManager(String userName){
        Member sm = getSysManager(userName);
        return sm != null;
    }
    public int getSystemManagerCount(){
        return systemManagers.size();
    }

    public static int getGuestId() {
        return guestId;
    }

    public String  getOnline() {
        return online.getUserName();
    }
    public boolean hasAdmin(){
        if(this.systemManagers.size() > 0)
            return true;
        return false;
    }
    public String  getOnlineMembers(String userName) {
        if(!isUserSysManager(userName)){
            return null;
        }
        String ret = "";
        for(Member m : members){
            if(isConnected(m.getUserName())){
                String s = m.getUserName() +"\t"+ m.getName()+ "\t"+ m.getLastName()+"\n";
                ret+=s;
            }
        }
        return ret;
    }
    public String  getOfflineMembers(String userName) {
        if(!isUserSysManager(userName)){
            return null;
        }
        String ret = "";
        for(Member m : members){
            if(!isConnected(m.getUserName())){
                String s = m.getUserName() +"\t"+ m.getName()+ "\t"+ m.getLastName()+"\n";
                ret+=s;
            }
        }
        return ret;
    }
}
