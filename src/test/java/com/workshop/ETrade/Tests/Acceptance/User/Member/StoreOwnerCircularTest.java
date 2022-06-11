package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StoreOwnerCircularTest {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.signUp(guestName, "Andalus2", "100","Andalus2","Andalus2");
        systemService.signUp(guestName, "Andalus3", "100","Andalus3","Andalus3");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.appointStoreOwner("Andalus", "Mega", "Andalus2");
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void removeOwnerThatAppointedOwner(){
        String newGuest = systemService.logOut("Andalus").getVal();
        systemService.login(newGuest, "Andalus2", "100");
        Assert.assertFalse(systemService.removeStoreOwner("Andalus2", "Mega", "Andalus").isSuccess());

    }

    @Test
    public void removeAllOwnersUnderOwner(){
        String newGuest = systemService.logOut("Andalus").getVal();
        systemService.login(newGuest, "Andalus2", "100");
        systemService.appointStoreOwner("Andalus2", "Mega", "Andalus3");
        newGuest = systemService.logOut("Andalus2").getVal();
        systemService.login(newGuest, "Andalus3", "100");
        Assert.assertFalse(systemService.getStoresOfUser("Andalus3").getVal().isEmpty());
        newGuest = systemService.logOut("Andalus3").getVal();
        systemService.login(newGuest, "Andalus", "100");
        systemService.removeStoreOwner("Andalus","Mega", "Andalus2");
        newGuest = systemService.logOut("Andalus").getVal();
        systemService.login(newGuest, "Andalus3", "100");
        Assert.assertTrue(systemService.getStoresOfUser("Andalus3").getVal().isEmpty());
    }
}
