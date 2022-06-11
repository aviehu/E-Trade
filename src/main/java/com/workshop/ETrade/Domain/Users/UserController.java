package com.workshop.ETrade.Domain.Users;

import com.workshop.ETrade.AllRepos;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Pair;
import com.workshop.ETrade.Domain.Stores.Bid;
import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Persistance.Users.MemberDTO;
import com.workshop.ETrade.Persistance.Users.SystemManagerDTO;
import com.workshop.ETrade.Repository.ProductRepository;
import com.workshop.ETrade.Service.ResultPackge.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class UserController {
    public static int guestId;
    List<Member> members;
    public static int memberDiscount;

    private List<Guest> guests;
    private List<String> systemManagers;
    private List<User> users;
    private Logger logger = Logger.getLogger("users");

    public UserController() {
        guestId = 0;
        memberDiscount = 0;
        this.members = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.systemManagers = new ArrayList<>();
        users = new ArrayList<>();
        try {
            Handler fileHandler = new FileHandler(System.getProperty("user.dir") + "/usersLogs/users.log", 2000, 5);
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            System.out.println("error while creating logger for users");
        }
//        init();
    }
    public void init(){
        //load from database
        Member systemManager = new Member("domain","domain", "domain","domain");
        members.add(systemManager);
        systemManagers.add(systemManager.getUserName());
        loadMembers();
        loadSystemManager();
        users.addAll(members);
        users.addAll(guests);
    }

    private void loadSystemManager() {
        List<SystemManagerDTO> smDTOS = AllRepos.getSystemManagerRepo().findAll();
        for (SystemManagerDTO sm : smDTOS) {
            systemManagers.add(sm.getSystemManager());
        }
    }

    private void loadMembers () {
        List<MemberDTO> memberDTOS = AllRepos.getMemberRepo().findAll();
        for (MemberDTO m : memberDTOS) {
            members.add(new Member(m));
        }
    }

   public Double getCartPrice(String userName){
       User u = getUser(userName);
       if (u == null)
           return -1.0;
      return u.getMyShopCart().getTotalPrice();
   }

    public String addSystemManager(String userName,String managerToAdd){
        if(systemManagers.contains(userName)){
            Member m = getMember(managerToAdd);
            if(m != null){
                if(!systemManagers.contains(m.getUserName())){
                    Member sm = new Member(m.getUserName(), m.getPassword(),m.getName(),m.getLastName());
                    members.remove(m);
                    systemManagers.add(sm.getUserName());
                    users.add(sm);
                    AllRepos.getSystemManagerRepo().save(new SystemManagerDTO(sm.getUserName()));
                    logger.info("new system manager - " + managerToAdd);
                    return null;
                }
                else
                    return managerToAdd+" is already a system manager\n";

            }else
            return managerToAdd+ " is not a registered member\n";

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
        AllRepos.getMemberRepo().save(new MemberDTO(m));
        logger.info("new user - " + userName);
        return true;
    }
    public String enterSystem(){
        Guest online = new Guest("guest"+guestId);
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
//                    if(isConnected(m.userName)){
//                        return  "You are already connected\n";
//                    }
                    m.setConnected(true);
                    logger.info(userName + " has logged in");
                    return null;

                }
                else
                    return "Invalid Username or Password\n";
            }
        }
        return "Invalid Username or Password\n";
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
//        Optional<MemberDTO> memberDTO = AllRepos.getMemberRepo().findById(userName);
//        if (memberDTO.isPresent()) {
//            Member member = new Member(memberDTO.get());
//
//        }
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
       Member m = getMember(userName);
       if(m != null) {
           logger.info("product - " + productName + " has been added to cart");
           return m.addProdToCart(storeName,quantity,productName);
       }
       Guest g = getGuest(userName);
       if(g!= null){
            logger.info("product - " + productName + " has been added to cart");
            return g.addProdToCart(storeName,quantity,productName);
        }

       return "User "+userName+" does not exist\n";
    }
    public String removeProductFromShoppingCart(String userName,Store s,int quantity,String prodName){
        User user = getUser(userName);
        if(user != null) {
            if (user.getClass().equals(Member.class))

            return user.removeProd(s, quantity, prodName);
        }
        return "User: "+userName+" does not exist\n";
    }
    public HashMap<String, Pair<Integer, String>> displayShoppingCart(String userName){
        User user = getUser(userName);
        if(user != null)
            return user.displayCart();
        return null;
    }
    public synchronized Result<List<String>> purchase(String userName, String creditCard, int month, int year , String holderName, int cvv, int id, String country, String city, String street, int stNum, int apartmentNum, int zip){
        User user = getUser(userName);
        SupplyAddress sa;
        if(user != null) {
            logger.info("new purchase by - " + userName);
            if(user.getCard() == null) {
                CreditCard card = new CreditCard(creditCard, month,year, cvv,id,holderName);
                if (user.getAddress() == null) {
                    sa = new SupplyAddress(country,city,street,stNum,apartmentNum,zip);
                    return user.purchase(card, sa);
                } else {
                    return user.purchase(card, user.getAddress());
                }
            }
            else if (user.getAddress() == null) {
                sa = new SupplyAddress(country,city,street,stNum,apartmentNum,zip);
                return user.purchase(user.getCard(), sa);
            } else
                return user.purchase(user.getCard(), user.getAddress());
        }
        return new Result<>(null, "FAIL!User: "+userName+" does not exist\n");
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
        for(String sm : systemManagers){
            if(sm.equals(userName))
                return getMember(sm);
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
            systemManagers.remove(sm2.getUserName());
            AllRepos.getSystemManagerRepo().delete(new SystemManagerDTO(sm2.getUserName()));
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
                AllRepos.getMemberRepo().delete(new MemberDTO(m));
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

    public boolean hasAdmin(){
        if(this.systemManagers.size() > 0)
            return true;
        return false;
    }
    public List<String>  getOnlineMembers(String userName) {
        if(!isUserSysManager(userName)){
            return null;
        }
        List<String> ret = new ArrayList<>();
        for(Member m : members){
            if(isConnected(m.getUserName())){
                ret.add(m.getUserName());
            }
        }
        ret.remove(userName);
        return ret;
    }
    public List<String> getOfflineMembers(String userName) {
        if(!isUserSysManager(userName)){
            return null;
        }
        List<String> ret = new ArrayList<>();
        for(Member m : members){
            if(!isConnected(m.getUserName())){
                ret.add(m.getUserName());
            }
        }
        return ret;
    }

    public List<Notification> getMessages(String userName) {
        Member member = getMember(userName);
        if(member == null) {
            return null;
        }
        return member.getMessages();
    }

    public void purchaseBid(String userName, Bid approved) {
        User user = getUser(userName);
        if(user == null) {
            return;
        }
        user.purchaseBid(approved);
    }
}
