package com.workshop.ETrade.Tests.Acceptance.System;

import com.workshop.ETrade.Domain.Users.ExternalService.HttpClient;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalServiceTest {

    SystemService systemService;
    String guestName;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.initFacade();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
    }

    @Test
    public void addPaymentServiceSuccess(){
        systemService.login(guestName, "domain", "domain");
        Assert.assertTrue(systemService.changeExternalPaymentService("domain", new PaymentAdaptee(new HttpClient())).getVal());
        Assert.assertTrue(systemService.paymentServiceExists().getVal());
    }

    @Test
    public void addPaymentServiceFail(){
        systemService.login(guestName,"Andalus","100");
        Assert.assertFalse(systemService.changeExternalPaymentService("Andalus", new PaymentAdaptee(new HttpClient())).getVal());
        Assert.assertTrue(systemService.paymentServiceExists().getVal());
    }

    @Test
    public void addSupplyServiceSuccess(){
        systemService.login(guestName, "domain", "domain");
        Assert.assertTrue(systemService.changeExternalSupplyService("domain", new SupplyAdaptee(new HttpClient())).getVal());
        Assert.assertTrue(systemService.supplyServiceExists().getVal());
    }

    @Test
    public void addSupplyServiceFail(){
        systemService.login(guestName,"Andalus","100");
        Assert.assertFalse(systemService.changeExternalSupplyService("Andalus", new SupplyAdaptee(new HttpClient())).getVal());
        Assert.assertTrue(systemService.supplyServiceExists().getVal());
    }
}
