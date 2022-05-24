package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdateProductTest {
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
    public void updateProductPriceSuccessTest(){

        Assert.assertEquals(5.0,systemService.getProdPrice("Mega","Bamba").getVal(),0);
        systemService.editProductPrice("Andalus", "Mega", "Bamba", 8);
        Assert.assertEquals(8.0,systemService.getProdPrice("Mega","Bamba").getVal(),0);
    }

    @Test
    public void updateProductPriceFailTest(){
        Assert.assertEquals(5.0,systemService.getProdPrice("Mega","Bamba").getVal(),0);
        systemService.editProductPrice("Andalus", "Mega", "Bamba", 8);
        Assert.assertNotEquals(5.0,systemService.getProdPrice("Mega","Bamba").getVal(),0);
    }

    @Test
    public void updateProductQuantitySuccessTest(){
        Assert.assertEquals(systemService.getProductAmount("Mega","Bamba").getVal(),100);
        systemService.editProductQuantity("Andalus", "Mega", "Bamba", 8);
        Assert.assertEquals(systemService.getProductAmount("Mega","Bamba").getVal(),8);
    }

    @Test
    public void updateProductQuantityFailTest(){
        Assert.assertEquals(systemService.getProductAmount("Mega","Bamba").getVal(),100);
        Assert.assertFalse(systemService.editProductQuantity("Andalus", "Mega", "Bisly", 8).getVal());
        Assert.assertEquals(systemService.getProductAmount("Mega","Bamba").getVal(),100);
    }
}
