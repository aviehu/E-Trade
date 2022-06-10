package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.PredicateForm;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class PredicateDiscountPurchase {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.appointStoreOwner("Andalus", "Mega", "Andalus2");
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
        systemService.addProductToStore("Andalus", "Mega",
                "Bisly", 100, 5,"snacks");
        List<PredicateForm> forms = new LinkedList<>();
        forms.add(new PredicateForm("amount", "Bamba", 2, 10, null, null));
        forms.add(new PredicateForm("amount", "Bisly", 2, 10, null, null));
        systemService.addPreDiscount("Andalus", "Mega", "snacks", 50, "discount!", DiscountType.CATEGORY,forms ,"and");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void purchaseWithDiscountSuccess(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);
        systemService.addProductToShoppingCart("Andalus", "Bisly", "Mega", 5);
        Assert.assertNotEquals(50,systemService.getCartPrice("Andalus").getVal(), 0.0);
    }

    @Test
    public void purchaseWithDiscountFail(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);
        Assert.assertEquals(25,systemService.getCartPrice("Andalus").getVal(), 0.0);
    }
}
