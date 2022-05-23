package com.workshop.ETrade.Tests.Acceptance.User.Admin;

import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetMembersInfoTest {

    private Thread t1;
    private Thread t2;
    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        t1 = new Thread(){
            public void run(){
                String guestName1 = systemService.enterSystem().getVal();
                systemService.login(guestName1, "Andalus", "100");
            }
        };

        t2 = new Thread(){
            public void run(){
                String guestName2 = systemService.enterSystem().getVal();
                systemService.login(guestName2, "admin", "admin");
            }
        };
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Anda","lus");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void GetOnlineMembersInfoTest(){
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException ignored) {}
        Assert.assertTrue(systemService.getOnlineMembers("admin").getVal().contains("Andalus"));
    }

    @Test
    public void GetOfflineMembersInfoTest(){}
}
