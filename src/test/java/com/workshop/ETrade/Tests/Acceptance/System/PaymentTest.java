package com.workshop.ETrade.Tests.Acceptance.System;

import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentTest {
    @Autowired
    SystemService systemService;
    String guestName;

    @Before
    public void setUp() throws Exception {
        ExtSysController extSysController = ExtSysController.getInstance();
        //systemService = new SystemService();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.signUp(guestName, "Andalus2", "100","Andalus","Andalus");
        systemService.login(guestName,"Andalus","100");
        systemService.openStore("Andalus","Mega",123);
        systemService.addProductToStore("Andalus","Mega","chips",100,20,"snacks");
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void paymentFail(){

        systemService.login(systemService.enterSystem().getVal(),"Andalus2","100");
        systemService.addProductToShoppingCart("Andalus2","chips","Mega",2);
        Assert.assertFalse(systemService.purchase("Andalus2","123",4,2023,"aa",986,2033,"jj","ff","dd",123,123,123).isSuccess());
        Assert.assertEquals(100,systemService.getProdAmount("Mega","chips").getVal(),0);
    }

}