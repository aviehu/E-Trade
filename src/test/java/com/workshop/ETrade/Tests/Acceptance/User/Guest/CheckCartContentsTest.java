package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckCartContentsTest {
    @Autowired
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalush", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalush", "100");
        systemService.openStore("Andalush", "Mega1", 123);
        systemService.addProductToStore("Andalush", "Mega1",
                "Bamba", 100, 5,"snacks");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void CheckCartContentsSuccessTest(){
        List<ProductForm> cartInfo = systemService.displayShoppingCart("Andalush").getVal();
        Assert.assertTrue(cartInfo.isEmpty());
        systemService.addProductToShoppingCart("Andalush", "Bamba", "Mega1", 5);
        Assert.assertEquals("Bamba", systemService.displayShoppingCart("Andalush").getVal().get(0).productName);
    }

    @Test
    public void CheckCartContentsFailTest(){

        systemService.addProductToShoppingCart("Andalush", "Bisly", "Mega1", 5);
        Assert.assertFalse(systemService.displayShoppingCart("Andalush").getVal().contains("Bisly"));
        systemService.editProductInCart("Andalus","Bisly","Mega1",0);
    }
}
