package com.workshop.ETrade.Tests.Acceptance.User.Admin;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
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
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100", "Anda", "lus");
        systemService.signUp(guestName, "Andalus2", "100", "Anda", "lus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega", "Bamba", 100, 5, "snacks");
        systemService.addProductToStore("Andalus", "Mega", "Bisly", 100, 5, "snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void GetStorePurchaseHistoryTest(){
        systemService.login(guestName, "domain", "domain");

    }
}
