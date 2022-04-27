package Domain.Users.Users;

import Domain.Stores.Store;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    public static int guestId;
    List<Member> members;
    public static int memberDiscount;
    private boolean isConnected;
    private List<Guest> guests;
    private List<User> users;

    public UserController() {
        guestId = 0;
        memberDiscount = 0;
        this.members = new ArrayList<>();
        this.guests = new ArrayList<>();
        users = new ArrayList<>();
        users.addAll(members);
        users.addAll(guests);
    }

   //unique userName

//    public boolean openStore(String storeName,int card){
//        Store s = new Store(storeName,this.userName,card);
//        return true;
//    }
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
    public boolean enterSystem(){
        Guest g = new Guest("guest"+guestId);
        this.guests.add(g);
        this.users.add(g);
        guestId++;
        return true;
    }
    public boolean logIn(String userName,String password){
        for(Member m : members){
            if(m.getUserName() == userName && m.getPassword() == password) {
                m.setConnected(true);
                return true;
            }
        }
        return false;
    }
    public void exitSystem(String userName){
        if(this.members.contains(userName)){
            //save to data
        }
        else{
            for(Guest g : guests){
                if(g.getUserName() == userName)
                    g.setConnected(false);
            }
        }

    }
    public boolean logOut(String userName){
        Member m = getMember(userName);
        if(m != null){
            //save to date base
            m.setConnected(false);
            enterSystem();
        }
        return false;

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
            if(m.getUserName() == userName)
                return m;
        }
        return null;
    }
    public Guest getGuest(String userName){
        for(Guest g : guests){
            if(g.getUserName() == userName)
                return g;
        }
        return null;
    }
    public User getUser(String userName){
        for(User u : users){
            if(u.getUserName() == userName)
                return u;
        }
        return null;
    }


}
