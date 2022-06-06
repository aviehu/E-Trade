package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorLeaf;
import com.workshop.ETrade.Domain.Stores.Predicates.Predicate;
import com.workshop.ETrade.Domain.Stores.Predicates.PredicateBuilder;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UpdatePurchasePolicyTest {
    private SystemService systemService;
    private String name;
    private Predicate p;
    private List<Predicate> l;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        name = systemService.enterSystem().getVal();
        systemService.signUp(name, "Andalus", "100", "Anda", "lus");
        systemService.login(name, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega", "Bamba", 100, 5, "snacks");
        systemService.addProductToStore("Andalus", "Mega", "Bisly", 200, 5, "snacks");
        p = new PredicateBuilder().getProductAmountPredicate("Bamba", 30, 100);
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 20);
        l = new ArrayList<>();
        l.add(p);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void UpdatePurchasePolicySuccessTest() {
        Assert.assertTrue(systemService.purchase("Andalus", 123, LocalTime.MAX, 123, "BeerSheva", "Masada", 12, 4).getVal());
        Assert.assertTrue(systemService.addPolicy("Andalus","Mega", "snacks", "", PolicyType.CATEGORY, new OperatorLeaf("and", l), "").getVal() > 0);
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 20);
//        systemService.purchase("Andalus", 123, LocalTime.MAX, 123, "BeerSheva", "Masada", 12, 4);
        Assert.assertFalse(systemService.purchase("Andalus", 123, LocalTime.MAX, 123, "BeerSheva", "Masada", 12, 4).getVal());
        //TODO: payment & supply not contacted, (storage, shopping cart, storepurchasegistory) not changed
    }

    @Test
    public void UpdatePurchasePolicyFailTest() { //not founder
        name = systemService.logOut("Andalus").getVal();
        systemService.signUp(name, "Andalus1", "200", "Anda", "lus1");
        systemService.login(name, "Andalus1", "200");
        Assert.assertEquals(java.util.Optional.of(-1), systemService.addPolicy("Andalus1", "Mega", "", "", PolicyType.CATEGORY, new OperatorLeaf("and", l), "").getVal());
    }
}