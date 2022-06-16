package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddProductToCartTest {

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
    public void addProductToCartSuccessTest(){
        List<ProductForm> cartInfo = systemService.displayShoppingCart("Andalus").getVal();
        Assert.assertTrue(cartInfo.isEmpty());
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);
        cartInfo = systemService.displayShoppingCart("Andalus").getVal();
        Assert.assertEquals("Bamba", cartInfo.get(0).productName);
    }

    @Test
    public void addProductToCartAndLogoutSuccessTest(){
        String guest = systemService.logOut("Andalus").getVal();
        Assert.assertTrue(systemService.addProductToShoppingCart(guest, "Bamba", "Mega", 10).isSuccess()); //added successfully
        Assert.assertTrue(!systemService.displayShoppingCart(guest).getVal().isEmpty()); //added successfully
        String newGuest = systemService.logOut(guest).getVal();
        Assert.assertNull(systemService.displayShoppingCart(newGuest).getVal());
    }


    @Test
    public void addProductToCartFailTest(){
        systemService.addProductToShoppingCart("Andalus", "Bisly", "Mega", 5);
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().contains("Bisly"));
    }
}
