package com.workshop.ETrade.Tests.Acceptance.User.Member;

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
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    //login, add product, logout, login, purchase
    @Test
    public void purchaseCartSuccessTest1(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 10);
        //assert total price is working
        Assert.assertEquals(systemService.getCartPrice("Andalus").getVal(), 50.0,0);
        List<ProductForm> cartBeforeLogout = systemService.displayShoppingCart("Andalus").getVal();
        String guest = systemService.logOut("Andalus").getVal();
        systemService.login(guest, "Andalus", "100");
        List<ProductForm> cartAfterLogin = systemService.displayShoppingCart("Andalus").getVal();
        Assert.assertEquals(cartBeforeLogout.size(), cartAfterLogin.size()); //the cart saved
        for (int i = 0; i < cartAfterLogin.size(); i++){
            Assert.assertEquals(cartAfterLogin.get(i).productName, cartBeforeLogout.get(i).productName);
            Assert.assertEquals(cartAfterLogin.get(i).amount, cartBeforeLogout.get(i).amount);
        }
        Assert.assertTrue(systemService.purchase("Andalus", "123", 4,2024,"Andalus Andalus", 776,200000000,"Israel",
                "BeerSheva", "Andalus", 7, 7,399949).isSuccess());
    }

    //login, add product, logout, add product as member, login, purchase
    @Test
    public void purchaseCartSuccessTest2(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 10);
        //assert total price is working
        Assert.assertEquals(systemService.getCartPrice("Andalus").getVal(), 50.0,0);
        List<ProductForm> cartBeforeLogout = systemService.displayShoppingCart("Andalus").getVal();
        double priceBeforeLogout = systemService.getCartPrice("Andalus").getVal();
        String guest = systemService.logOut("Andalus").getVal();
        systemService.addProductToShoppingCart(guest, "Bamba", "Mega", 1);
        systemService.login(guest, "Andalus", "100");
        List<ProductForm> cartAfterLogin = systemService.displayShoppingCart("Andalus").getVal();
        double priceAfterLogin = systemService.getCartPrice("Andalus").getVal();
        Assert.assertEquals(priceBeforeLogout, priceAfterLogin, 0.001); //the cart saved
        Assert.assertEquals(cartBeforeLogout.size(), cartAfterLogin.size()); //the cart saved
        for (int i = 0; i < cartAfterLogin.size(); i++){
            Assert.assertEquals(cartAfterLogin.get(i).productName, cartBeforeLogout.get(i).productName);
            Assert.assertEquals(cartAfterLogin.get(i).amount, cartBeforeLogout.get(i).amount);
        }
        Assert.assertTrue(systemService.purchase("Andalus", "123", 4,2024,"Andalus Andalus", 776,200000000,"Israel",
                "BeerSheva", "Andalus", 7, 7,399949).isSuccess());
    }
}
