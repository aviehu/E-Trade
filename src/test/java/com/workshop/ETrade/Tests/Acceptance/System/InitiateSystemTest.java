package com.workshop.ETrade.Tests.Acceptance.System;

import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitiateSystemTest {

    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.initFacade();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void atLeastOneAdmin() throws Exception {
        systemService.init();
        systemService.initFacade();
        systemService.enterSystem();
        Assert.assertTrue(systemService.hasAdmin().getVal());
    }

    @Test
    public void externalServicesConnected() throws Exception {
        systemService.init();
        Assert.assertTrue(systemService.supplyServiceExists().getVal());
        Assert.assertTrue(systemService.paymentServiceExists().getVal());
    }
}