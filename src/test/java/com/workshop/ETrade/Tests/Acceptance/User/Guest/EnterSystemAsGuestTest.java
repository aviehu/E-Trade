package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnterSystemAsGuestTest {
    SystemService systemService;
    String name;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void EnterSystemAsGuestTestSuccess() throws Exception {
        systemService = new SystemService();
        name = systemService.enterSystem().getVal();
        Assert.assertTrue(name.contains("guest"));
    }

    @Test
    public void EnterSystemAsGuestTestFail(){
        try {
            name = systemService.enterSystem().getVal();
        }
        catch (Exception e){
            Assert.assertNull(name);
        }


    }
}
