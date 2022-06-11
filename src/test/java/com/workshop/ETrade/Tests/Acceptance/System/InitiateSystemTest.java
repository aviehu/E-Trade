package com.workshop.ETrade.Tests.Acceptance.System;

import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;

public class InitiateSystemTest {

    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void atLeastOneAdmin() throws Exception {
        systemService.init();
        Assert.assertTrue(systemService.hasAdmin().getVal());
    }

    @Test
    public void externalServicesConnected(){
        //systemService.init();
        //Assert.assertTrue(systemService.supplyServiceExists().getVal());
        //Assert.assertTrue(systemService.paymentServiceExists().getVal());
    }
}