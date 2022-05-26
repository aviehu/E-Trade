package com.workshop.ETrade.Tests.Acceptance.User.Admin;

import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloseStorePermanentlyTest {
    @Autowired
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.login(guestName, "domain", "domain");
        systemService.openStore("domain", "Mega", 123);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void closeStorePermanentlySuccessTest(){
        Assert.assertTrue(systemService.getStoreInfo("domain", "Mega").isSuccess());
        systemService.adminCloseStorePermanently("domain", "Mega");
        Assert.assertFalse(systemService.getStoreInfo("domain", "Mega").isSuccess());
    }

}
