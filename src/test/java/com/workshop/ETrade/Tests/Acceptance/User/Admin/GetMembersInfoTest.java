package com.workshop.ETrade.Tests.Acceptance.User.Admin;

import com.workshop.ETrade.Service.SystemService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetMembersInfoTest {

    private Thread t1;
    private Thread t2;
    @Autowired
    private SystemService systemService;
    private String guestName;

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
                systemService.login(guestName2, "domain", "domain");
            }
        };
        guestName = systemService.enterSystem().getVal();
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
        List<String> online =systemService.getOnlineMembers("domain").getVal();
            Assert.assertNotNull(online);
            Assert.assertTrue(online.contains("Andalus"));

    }

    @Test
    public void GetOfflineMembersInfoTest(){
        systemService.signUp(guestName, "Andalus1", "200","Anda","lus1");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException ignored) {}
        List<String> offline1 = systemService.getOfflineMembers("domain").getVal();
        Assert.assertNotNull(offline1);
        Assert.assertTrue(offline1.contains("Andalus1"));
        Assert.assertFalse(systemService.getOfflineMembers("domain").getVal().contains("Andalus"));
    }
}
