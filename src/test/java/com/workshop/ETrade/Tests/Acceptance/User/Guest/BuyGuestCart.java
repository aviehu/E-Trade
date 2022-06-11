package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;

public class BuyGuestCart {
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
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void guestBuysCartSuccess(){
        systemService.addProductToShoppingCart("Andalus","Bamba", "Mega", 20);
        String newGuest = systemService.logOut("Andalus").getVal();
        Assert.assertTrue(systemService.addProductToShoppingCart(newGuest, "Bamba", "Mega", 20).isSuccess());
        Assert.assertFalse(systemService.displayShoppingCart(newGuest).getVal().isEmpty());
        systemService.purchase(newGuest,"123123", 10, 2025, "Andalus", 123, 123123, "Israel","BeerSheva", "Rager", 1, 2, 12312);
        Assert.assertTrue(systemService.displayShoppingCart(newGuest).getVal().isEmpty());
        systemService.login(newGuest, "Andalus", "100");
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().isEmpty());
    }
}
