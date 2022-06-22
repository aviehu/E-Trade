package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetStorePurchaseHistoryTest {

    SystemService systemService;
    String guestName;
    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.initFacade();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100", "Anda", "lus");
        systemService.signUp(guestName, "Andalus2", "100", "Anda", "lus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega", "Bamba", 100, 5, "snacks");
        systemService.addProductToStore("Andalus", "Mega", "Bisly", 100, 5, "snacks");
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 10);
        systemService.addProductToShoppingCart("Andalus", "Bisly", "Mega", 8);
        systemService.purchase("Andalus", "123", 12, 2030, "Andalus", 100, 123, "a", "a", "a", 1, 1, 1);
        guestName = systemService.logOut("Andalus").getVal();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void GetStorePurchaseHistorySuccessTest(){
        systemService.login(guestName, "Andalus", "100");
        String purchaseHistory = systemService.getStoresPurchaseHistory("Andalus", "Mega").getVal();
        Assert.assertTrue(purchaseHistory.contains("Bamba"));
        Assert.assertTrue(purchaseHistory.contains("10"));
        Assert.assertTrue(purchaseHistory.contains("Bisly"));
        Assert.assertTrue(purchaseHistory.contains("8"));
    }

    @Test
    public void GetStorePurchaseHistoryFailTest(){
        systemService.login(guestName, "Andalus2", "100");
        Assert.assertEquals("",systemService.getStoresPurchaseHistory("Andalus2", "Mega").getVal());

    }
}
