package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.ComplexPredicateForm;
import com.workshop.ETrade.Controller.Forms.ComponentPredicateForm;
import com.workshop.ETrade.Controller.Forms.LeafPredicateForm;
import com.workshop.ETrade.Controller.Forms.PredicateForm;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
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
public class ComplexPolicyTest {

    @Autowired
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
       // systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "aa", 123);
        systemService.appointStoreOwner("Andalus", "aa", "Andalus2");
        systemService.addProductToStore("Andalus", "aa",
                "Bamba", 100, 5,"snacks");
        systemService.addProductToStore("Andalus", "aa",
                "Bisly", 100, 5,"snacks");
        List<ComponentPredicateForm> lpfs = new ArrayList<>();
        List<PredicateForm> forms1 = new LinkedList<>();
        forms1.add(new PredicateForm("amount", "Bamba", 2, 10, null, null));
        lpfs.add(new LeafPredicateForm("or", forms1));
        List<PredicateForm> forms2 = new LinkedList<>();
        forms2.add(new PredicateForm("amount", "Bisly", 2, 10, null, null));
        lpfs.add(new LeafPredicateForm("or", forms2));
        systemService.addComplexPolicy("Andalus", "aa", "Bamba", "blablabla", PolicyType.BASKET, new ComplexPredicateForm("and", lpfs), "and");
        //if  10 > bamba amount > 2 and 10 > bisly amount > 2 then apply discount 50% on bamba
    }

    @Test
    public void purchaseWithPolicySuccess(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "aa", 5);
        systemService.addProductToShoppingCart("Andalus", "Bisly", "aa", 5);
        Assert.assertTrue(systemService.purchase("Andalus", "123", 4,2024,"Andalus Andalus", 776,2000000,"Israel",
                "BeerSheva", "Andalus", 7, 7,399949).isSuccess());

    }

    @Test
    public void purchaseWithPolicyFail(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "aa", 5);
        Assert.assertFalse(systemService.purchase("Andalus", "123", 4,2024,"Andalus Andalus", 776,2000000,"Israel",
                "BeerSheva", "Andalus", 7, 7,399949).isSuccess());
    }
}
