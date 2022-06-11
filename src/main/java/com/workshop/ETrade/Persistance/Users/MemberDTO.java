package com.workshop.ETrade.Persistance.Users;

import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Notifications.NotificationManager;
import com.workshop.ETrade.Domain.Users.*;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.List;

public class MemberDTO {

    //User
    public SupplyAddress address;
    public boolean isConnected;
    @Id
    public String userName;
    public CreditCard card;

    //Member
    public List<Notification> awaitingNotification;
    public String password;
    public int age;
    public String name;
    public String lastName;
    public String mail;
//    private MemberPurchaseHistory pHistory;
    public int securityLvl;
    public HashMap<String,String> securityQuests;

    public int discount;

    public MemberDTO(Member member) {
        this.address = member.getAddress();
        this.isConnected = member.isConnected();
        this.userName = member.getUserName();
        this.card = member.getCard();
        this.awaitingNotification = member.getAwaitingNotification();
        this.password = member.getPassword();
        this.age = member.getAge();
        this.name = member.getName();
        this.lastName = member.getLastName();
        this.mail = member.getMail();
//        this.pHistory = pHistory;
        this.securityLvl = member.getSecurityLvl();
        this.securityQuests = member.getSecurityQuests();
        this.discount = member.getDiscount();
    }

    public MemberDTO() {}
}
