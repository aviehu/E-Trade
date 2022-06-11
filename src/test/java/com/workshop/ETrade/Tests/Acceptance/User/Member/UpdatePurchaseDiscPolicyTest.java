package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdatePurchaseDiscPolicyTest {

    private SystemService systemService;
    String guestName;


    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100", "Anda", "lus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega", "Bamba", 200, 5, "snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void UpdatePurchaseDiscPolicySuccessTest(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 20);
        double prevPrice = systemService.getCartPrice("Andalus").getVal();
        Assert.assertTrue(systemService.addDiscount("Andalus", "Mega", "Bamba", 10, "", DiscountType.PRODUCT).getVal() > 0);
        double updatePrice = systemService.getCartPrice("Andalus").getVal();
        systemService.getCartPrice("Andalus");
        Assert.assertTrue(prevPrice >= updatePrice); //TODO: 0.9
        //TODO: add another test: predicate discount, complex discount
    }
}
