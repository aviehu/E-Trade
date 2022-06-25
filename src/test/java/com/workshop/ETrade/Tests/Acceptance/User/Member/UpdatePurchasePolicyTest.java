package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.PredicateForm;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorLeaf;
import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import com.workshop.ETrade.Domain.Stores.Predicates.PredicateBuilder;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdatePurchasePolicyTest {
    @Autowired
    private SystemService systemService;
    private String name;

    @Before
    public void setUp() throws Exception {
        //systemService = new SystemService();
        name = systemService.enterSystem().getVal();
        systemService.signUp(name, "Andalus17", "100", "Anda", "lus");
        systemService.login(name, "Andalus17", "100");
        systemService.openStore("Andalus17", "Mega6", 123);
        systemService.addProductToStore("Andalus17", "Mega6", "Bamba", 100, 5, "snacks");
        systemService.addProductToStore("Andalus17", "Mega6", "Bisly", 200, 5, "snacks");
        systemService.addProductToShoppingCart("Andalus17", "Bamba", "Mega6", 20);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void UpdatePurchasePolicySuccessTest() {
        Assert.assertTrue(systemService.purchase("Andalus17", "123", 4,2024,"Andalus Andalus", 123,20000,"Israel", "BeerSheva", "Masada", 12, 4,400000).getVal());
        List<PredicateForm> opList = new ArrayList<>();
        opList.add(new PredicateForm("amount", "Bamba", 5, 300, null, null));
        Assert.assertTrue(systemService.addPolicy("Andalus17", "Mega6", "snacks", "", PolicyType.CATEGORY, opList, "and").getVal() > 0);
        systemService.addProductToShoppingCart("Andalus17", "Bamba", "Mega6", 20);
        Assert.assertTrue(systemService.purchase("Andalus17", "123", 4,2024,"Andalus Andalus", 123,20000,"Israel", "BeerSheva", "Masada", 12, 4,400000).isSuccess());
    }

    @Test
    public void UpdatePurchasePolicyFailTest() { //not founder
        name = systemService.logOut("Andalus17").getVal();
        systemService.signUp(name, "Andalus1", "200", "Anda", "lus1");
        systemService.login(name, "Andalus1", "200");
        Assert.assertFalse(systemService.addPolicy("Andalus1", "Mega6", "Bamba", "", PolicyType.CATEGORY, new ArrayList<>(), "and").isSuccess());
    }
}