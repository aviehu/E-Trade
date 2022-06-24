package com.workshop.ETrade.Tests.Acceptance.System;

import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationTest {
    @Autowired
    private SystemService service;
    String userName;
        //add manager,remove manager, close store, purchase;
    @Before
    public void setUp() throws Exception {
        userName = service.enterSystem().getVal();
        service.initFacade();
        service.signUp(userName,"Batya","123","a","a");
        service.signUp(userName,"Batya1","123","a","a"); // purchase
        service.signUp(userName,"Batya2","123","a","a");
        service.signUp(userName,"Batya3","123","a","a");
        service.login(userName,"domain","domain");
        service.openStore("domain","Hila",123);
        service.addProductToStore("domain","Hila","apple",100,20.0,"fruits");


    }
    @Test
    public void addOwnerNotificationTest(){
        service.login(userName,"Batya","123");
        List<Notification> notificationList = service.getMessages("Batya").getVal();
        Assert.assertEquals(0,notificationList.size());
        service.logOut("Batya");
        service.appointStoreManager("domain","Hila","Batya");
        service.login(userName,"Batya","123");
        notificationList = service.getMessages("Batya").getVal();
        Assert.assertEquals(1,notificationList.size());
        Assert.assertEquals("domain",notificationList.get(0).getSentFrom());


    }
    @Test
    public void purchaseNotificationsTest(){
        List<Notification> notificationList;
        service.appointStoreManager("domain","Hila","Batya2");
        service.logOut("domain");

        service.login(userName,"Batya1","123");
        service.addProductToShoppingCart("Batya1","apple","Hila",2);
        service.purchase("Batya1","123",3,2030,"nb",100,234,"fff","dfsf","dfs",23,123,324423);
        service.login(userName,"domain","domain");
        service.login(userName,"Batya2","123");
        notificationList = service.getMessages("domain").getVal();
        Assert.assertEquals(1,notificationList.size());
        Assert.assertEquals("Batya1",notificationList.get(0).getSentFrom());
        notificationList = service.getMessages("Batya2").getVal();
        Assert.assertEquals(2,notificationList.size());
        String sentFrom = notificationList.get(1).getSentFrom();
        Assert.assertEquals("Batya1",sentFrom);

    }
    @Test
    public void adminCloseStoreNotificationsTest(){
        List<Notification> notificationList;
        service.appointStoreManager("domain","Hila","Batya3");

        service.adminCloseStorePermanently("domain","Hila");
        service.login(userName,"Batya3","123");
        notificationList = service.getMessages("Batya3").getVal();
        Assert.assertEquals(2,notificationList.size());
        String sentFrom = notificationList.get(0).getSentFrom();
        Assert.assertEquals("domain",sentFrom);
        String message = notificationList.get(1).getMessage();
        Assert.assertEquals("Your Store Hila has been close permanently by Admin",message);

    }




}
