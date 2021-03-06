package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseCartTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.signUp(guestName, "Andalus2", "100","Andalus","Andalus");
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
        //assert total price is working
        Assert.assertEquals(systemService.getCartPrice("Andalus").getVal(), 50.0,0);
        Assert.assertTrue(systemService.purchase("Andalus", "123", 4,2024,"Andalus Andalus", 776,200000000,"Israel",
                "BeerSheva", "Andalus", 7, 7,399949).isSuccess());
        //assert cart is an empty list after purchase
        List<ProductForm> cart = systemService.displayShoppingCart("Andalus").getVal();
        List<String> expected = new ArrayList<>();
        Assert.assertEquals(expected, cart);
        Assert.assertEquals(90, (long)systemService.getProductAmount("Mega", "Bamba").getVal());
    }

    @Test
    public void purchaseCartFailTest(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 50);
        String guestName = systemService.logOut("Andalus").getVal();
        systemService.login(guestName, "Andalus2", "100");
        systemService.addProductToShoppingCart("Andalus2", "Bamba", "Mega", 80);
        boolean purchased = systemService.purchase("Andalus2", "123", 4,2024,"Andalus Andalus", 100,200000000,"Israel",
                "BeerSheva", "Andalus", 7, 7,399949).getVal();
        guestName = systemService.logOut("Andalus2").getVal();
        systemService.login(guestName, "Andalus", "100");
        String storePurchaseHistory = systemService.getStoresPurchaseHistory("Andalus", "Mega").getVal();
        int prodAmount = systemService.getProductAmount("Mega", "Bamba").getVal(); //20
        systemService.purchase("Andalus", "123", 4,2024,"Andalus Andalus", 776,200000000,"Israel",
                "BeerSheva", "Andalus", 7, 7,399949);
        // the cart didn't change
        List<ProductForm> cart = systemService.displayShoppingCart("Andalus").getVal();
        Assert.assertTrue(cart.get(0).productName.equals("Bamba") && cart.get(0).amount == 50);
        // the amount in the store didn't change
        Assert.assertEquals(prodAmount, (int)systemService.getProductAmount("Mega", "Bamba").getVal());
        // store purchase history didn't change
        String newHistory = systemService.getStoresPurchaseHistory("Andalus", "Mega").getVal();
        Assert.assertEquals(storePurchaseHistory, newHistory);
    }
}
