package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CheckCartContentsTest {
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
    public void CheckCartContentsSuccessTest(){
        List<ProductForm> cartInfo = systemService.displayShoppingCart("Andalus").getVal();
        Assert.assertTrue(cartInfo.isEmpty());
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);
        Assert.assertEquals("Bamba", systemService.displayShoppingCart("Andalus").getVal().get(0).productName);
    }

    @Test
    public void CheckCartContentsFailTest(){
        systemService.addProductToShoppingCart("Andalus", "Bisly", "Mega", 5);
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().contains("Bisly"));
    }
}
