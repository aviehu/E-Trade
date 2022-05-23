package com.workshop.ETrade.Tests.Acceptance.User.Member;

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
        //int price = getTotalPrice();
        //להוסיף מדיניות הנחה
        //Assert.assertNotEquals(price, getTotal());
       

    }
}
