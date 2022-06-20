package com.workshop.ETrade.Tests.Acceptance.User.Member;


import com.workshop.ETrade.Controller.Forms.ComplexPredicateForm;
import com.workshop.ETrade.Controller.Forms.ComponentPredicateForm;
import com.workshop.ETrade.Controller.Forms.LeafPredicateForm;
import com.workshop.ETrade.Controller.Forms.PredicateForm;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComplexDiscountTest {

    @Autowired
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        //systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andaluss", "100","Andalus","Andalus");
        systemService.login(guestName, "Andaluss", "100");
        systemService.openStore("Andaluss", "bb", 123);
        systemService.appointStoreOwner("Andaluss", "bb", "Andalus2");
        systemService.addProductToStore("Andaluss", "bb",
                "Bamba", 100, 5,"snacks");
        systemService.addProductToStore("Andaluss", "bb",
                "Bisly", 100, 5,"snacks");
        List<ComponentPredicateForm> lpfs = new ArrayList<>();
        List<PredicateForm> forms1 = new LinkedList<>();
        forms1.add(new PredicateForm("amount", "Bamba", 2, 10, null, null));
        lpfs.add(new LeafPredicateForm("or", forms1));
        List<PredicateForm> forms2 = new LinkedList<>();
        forms2.add(new PredicateForm("amount", "Bisly", 2, 10, null, null));
        lpfs.add(new LeafPredicateForm("or", forms2));
        systemService.addComplexDiscount("Andaluss", "bb", "Bamba", 50, "blablabla", DiscountType.PRODUCT,new ComplexPredicateForm("and", lpfs), "and");
        //if  10 > bamba amount > 2 and 10 > bisly amount > 2 then apply discount 50% on bamba
    }
    @Test
    public void purchaseWithDiscountFail(){
        systemService.purchase("Andaluss", "123", 4,2024,"Andalus Andalus", 776,200000000,"Israel",
                "BeerSheva", "Andaluss", 7, 7,399949);
        systemService.addProductToShoppingCart("Andaluss", "Bamba", "bb", 5);
        Assert.assertEquals(25,systemService.getCartPrice("Andaluss").getVal(), 0.0);

    }

    @Test
    public void purchaseWithDiscountSuccess(){
        systemService.purchase("Andaluss", "123", 4,2024,"Andalus Andalus", 776,200000000,"Israel",
                "BeerSheva", "Andaluss", 7, 7,399949);
        systemService.addProductToShoppingCart("Andaluss", "Bamba", "bb", 5);
        systemService.addProductToShoppingCart("Andaluss", "Bisly", "bb", 5);
        Assert.assertEquals(37.5,systemService.getCartPrice("Andaluss").getVal(), 0.0);

    }


}
