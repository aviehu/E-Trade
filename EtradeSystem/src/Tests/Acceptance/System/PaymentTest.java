package Tests.Acceptance.System;

import Domain.Stores.Store;
import Domain.Users.Users.Guest;
import Domain.Users.Users.Member;
import Domain.Users.Users.User;
import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.signUp("Andalus", "100");
        systemService.login("Andalus", "100");
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
        systemService.purchase("Andalus");
        Assert.assertEquals("", systemService.displayShoppingCart("Andalus").getVal());
        Assert.assertEquals(90, systemService.getProductAmount("Mega", "Bamba"));
    }

    public void purchaseCartFailTest(){
        String cart = systemService.displayShoppingCart("Andalus").getVal();
        int prodAmount = systemService.getProductAmount("Mega", "Bamba");
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 200);
        systemService.purchase("Andalus");
        Assert.assertEquals(cart, systemService.displayShoppingCart("Andalus").getVal());
        Assert.assertEquals(prodAmount, systemService.getProductAmount("Mega", "Bamba"));
    }
}