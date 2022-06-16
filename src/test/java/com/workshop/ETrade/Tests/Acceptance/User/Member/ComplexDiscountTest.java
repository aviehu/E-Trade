package com.workshop.ETrade.Tests.Acceptance.User.Member;


import com.workshop.ETrade.Controller.Forms.ComplexPredicateForm;
import com.workshop.ETrade.Controller.Forms.ComponentPredicateForm;
import com.workshop.ETrade.Controller.Forms.LeafPredicateForm;
import com.workshop.ETrade.Controller.Forms.PredicateForm;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ComplexDiscountTest {

    @Autowired
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
        List<ComponentPredicateForm> lpfs = new ArrayList<>();
        List<PredicateForm> forms1 = new LinkedList<>();
        forms1.add(new PredicateForm("amount", "Bamba", 2, 10, null, null));
        lpfs.add(new LeafPredicateForm("or", forms1));
        List<PredicateForm> forms2 = new LinkedList<>();
        forms2.add(new PredicateForm("amount", "Bisly", 2, 10, null, null));
        lpfs.add(new LeafPredicateForm("or", forms2));
        systemService.addComplexDiscount("Andalus", "Mega", "Bamba", 50, "blablabla", DiscountType.PRODUCT,new ComplexPredicateForm("and", lpfs), "and");
        //if  10 > bamba amount > 2 and 10 > bisly amount > 2 then apply discount 50% on bamba
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
