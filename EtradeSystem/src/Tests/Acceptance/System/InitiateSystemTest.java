package Tests.Acceptance.System;

import Service.SystemService;
import org.junit.Assert;
import org.junit.Test;

public class InitiateSystemTest {

    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.init();

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void atLeastOneAdmin(){
        Assert.assertTrue(systemService.getAdmins().size() > 0);
    }

    @Test
    public void externalServicesConnected(){
        Assert.assertTrue(systemService.supplyServiceExists());
        Assert.assertTrue(systemService.paymentServiceExists());
    }
}