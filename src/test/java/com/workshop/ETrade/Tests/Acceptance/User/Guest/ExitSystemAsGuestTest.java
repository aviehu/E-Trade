package com.workshop.ETrade.Tests.Acceptance.User.Guest;

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
public class ExitSystemAsGuestTest {

    private SystemService systemService;
    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void exitSystemAsGuestTest(){
        Assert.assertTrue(true);
    }

    @Test
    public void exitSystemAsGuestTestSuccess(){
        String guestName = systemService.enterSystem().getVal();
        systemService.login(guestName,"domain", "domain");
        Assert.assertTrue(systemService.getOnlineGuests().getVal().contains(guestName));
    }
}
