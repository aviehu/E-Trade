package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LogoutTest {
    private SystemService systemService;
    String guestName;
    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "newMember", "123","newMember","newMember").getVal();
        systemService.login(guestName, "newMember", "123");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void logoutSuccessTest() {
        Assert.assertTrue(systemService.logOut("newMember").isSuccess());
    }

    @Test
    public void logoutFailTest() {
        Assert.assertFalse(systemService.logOut("Andalus").isSuccess());
        Assert.assertTrue(systemService.logOut("newMember").isSuccess());
        Assert.assertFalse(systemService.logOut("newMember").isSuccess());
    }
}
