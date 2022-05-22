package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class PurchaseCartTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void purchaseCartSuccessTest(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 10);
        Assert.assertTrue(systemService.purchase("Andalus", 123, LocalTime.MAX, 776,
                "BeerSheva", "Andalus", 7, 7).isSuccess());
        Assert.assertEquals("", systemService.displayShoppingCart("Andalus").getVal());
        Assert.assertEquals(90, systemService.getProductAmount("Mega", "Bamba").getVal());
    }

    @Test
    public void purchaseCartFailTest(){
        String cart = systemService.displayShoppingCart("Andalus").getVal();
        int prodAmount = systemService.getProductAmount("Mega", "Bamba").getVal();
        String storePurchaseHistory = systemService.getStoresPurchaseHistory("Andalus", "Mega").getVal();
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 200);
        systemService.purchase("Andalus", 123, LocalTime.MAX, 776,
                "BeerSheva", "Andalus", 7, 7);
        // the cart didn't change
        Assert.assertEquals(cart, systemService.displayShoppingCart("Andalus").getVal());
        // the amount in the store didn't change
        Assert.assertEquals(prodAmount, systemService.getProductAmount("Mega", "Bamba").getVal());
        // store purchase history didn't change
        Assert.assertEquals(storePurchaseHistory, systemService.getProductAmount("Mega", "Bamba").getVal());

    }
}
