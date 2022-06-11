package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppointNewManagerTest {

    private SystemService systemService;
    private String guestName;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Mira", "200","Mira","Mira");
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        guestName = systemService.logOut("Andalus").getVal();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AppointNewManagerSuccessTest(){
        systemService.login(guestName, "Mira", "200");
        Assert.assertFalse(systemService.getStoresPurchaseHistory("Mira", "Mega").isSuccess());
        guestName = systemService.logOut("Mira").getVal();
        systemService.login(guestName, "Andalus", "100");
        systemService.appointStoreManager("Andalus", "Mega", "Mira");
        guestName = systemService.logOut("Andalus").getVal();
        systemService.login(guestName, "Mira", "200");
        Assert.assertTrue(systemService.getStoresPurchaseHistory("Mira", "Mega").isSuccess());
    }

    @Test
    public void AppointNewManagerFailTest() {
        Assert.assertFalse(systemService.appointStoreManager("Andalus", "Mega", "Itay").isSuccess());
    }



}
