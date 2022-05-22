package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdateCartTest {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void UpdateCartSuccessTest(){
        Assert.assertTrue(systemService.displayShoppingCart("Andalus").getVal().contains("Bamba"));
        systemService.removeProductFromShoppingCart("Andalus", "Mega", 5, "Bamba");
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().contains("Bamba"));
    }

    @Test
    public void UpdateCartFailTest(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);
        Assert.assertTrue(systemService.displayShoppingCart("Andalus").getVal().contains("Bamba"));
        systemService.removeProductFromShoppingCart("Andalus", "Mega", 5, "Bisly");
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().contains("Bisly"));
    }
}
