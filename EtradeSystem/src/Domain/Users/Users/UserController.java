package Domain.Users.Users;

import Domain.Stores.Store;
import Service.ResultPackge.ResultBool;
import Service.ResultPackge.ResultMsg;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    public static int guestId;
    List<Member> members;
    public static int memberDiscount;

    private List<Guest> guests;
    private List<Member> systemManagers;
    private List<User> users;
    private Guest online;

    public UserController() {
        guestId = 0;
        memberDiscount = 0;
        this.members = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.systemManagers = new ArrayList<>();
        users = new ArrayList<>();


        init();
    }
    public void init(){
        //load from database
        Member systemManager = new Member("domain","domain");
//        Member member = new Member("member","member");
//        members.add(member);
        members.add(systemManager);
        systemManagers.add(systemManager);
        users.addAll(members);
        users.addAll(guests);
        users.addAll(systemManagers);
    }

   //unique userName

//    public boolean openStore(String storeName,int card){
//        Store s = new Store(storeName,this.userName,card);
//        return true;
//    }

    public boolean addSystemManager(String userName,String managerToAdd){
        if(systemManagers.contains(userName)){
            Member m = getMember(managerToAdd);
            if(!systemManagers.contains(managerToAdd)&& m != null){
                SystemManager sm = new SystemManager(m.getUserName(),m.getPassword());
                members.remove(m);
                systemManagers.add(sm);
                users.add(sm);
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean signUp(String userName,String password) {
        for (Member m : members) {
            if (m.getUserName() == userName)
                return false;
        }
        Member m = new Member(userName, password);
        members.add(m);
        users.add(m);
        return true;
    }
    public String enterSystem(){
        online = new Guest("guest"+guestId);
        online.setConnected(true);
        this.guests.add(online);
        this.users.add(online);
        guestId++;
        return online.getUserName();
    }
    public boolean logIn(String userName,String password){
        for(Member m : members){
            if(m.getUserName().equals(userName) && m.getPassword().equals(password)) {
                m.setConnected(true);
                return true;
            }
        }
        return false;
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
       if(user != null)
           return user.addProdToCart(storeName,quantity,productName);
       return null;
    }
    public String removeProductFromShoppingCart(String userName,Store s,int quantity,String prodName){
        User user = getUser(userName);
        if(user != null)
            return user.removeProd(s,quantity,prodName);
        return null;
    }
    public String displayShoppingCart(String userName){
        User user = getUser(userName);
        if(user != null)
            return user.displayCart();
        return null;
    }
    public boolean purchase(String userName, int creditCard, LocalTime expDate,int cvv,String city,String street,int stNum,int apartmentNum){
        User user = getUser(userName);
        SupplyAddress sa;
        if(user != null) {
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
        return false;
    }
    public boolean isValidPassword(String password){
        //return password.length() >= 8 && containsUpperCase(password);
        return true;
    }
    public boolean isUserNameExist(String userName){
        User u = getUser(userName);
        if(u != null)
            return true;
        return false;
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
            if(sm.getUserName() == userName)
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
            Member m = new Member(sm2.getUserName(),sm2.getPassword());
            members.add(m);
            systemManagers.remove(sm2);
            return true;
        }
        return false;


    }
    public boolean removeMember(String userName,String memberToRemove){
        Member sm = getSysManager(userName);
        Member m = getMember(memberToRemove);
        if(sm != null && m != null){
            members.remove(m);
            users.remove(m);
            return true;
        }
        return false;
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
}
