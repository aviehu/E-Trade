package com.workshop.ETrade.Tests.Acceptance.User.Member;

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
public class AppointNewOwnerTest {
    @Autowired
    private SystemService systemService;
    String guestName;

    @Before
    public void setUp() throws Exception {
        //systemService = new SystemService();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Mira", "200","Mira","Mira");
        systemService.signUp(guestName, "Mira1", "200","Mira","Mira");
        systemService.signUp(guestName, "Mira2", "200","Mira","Mira");
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.signUp(guestName, "Andalus2", "102","Andalus2","Andalus2");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "jj", 123);
        systemService.openStore("Andalus", "gg", 123);
        //systemService.appointStoreManager("Andalus", "Mega", "Mira");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AppointNewOwnerSuccessTest(){
        systemService.login(guestName, "Andalus", "100");
        Assert.assertTrue(systemService.appointStoreOwner("Andalus", "gg", "Mira1").isSuccess());
        String guestName = systemService.logOut("Andalus").getVal();
        systemService.login(guestName, "Mira1", "200");
        Assert.assertTrue(systemService.appointStoreOwner("Mira1", "gg", "Andalus2").isSuccess());
        systemService.login(guestName, "Andalus", "100");
        systemService.removeStoreOwner("Andalus","gg","Mira1");

    }

    @Test
    public void AppointNewOwnerFailTest() {
        Assert.assertFalse(systemService.appointStoreOwner("Andalus", "jj", "Itay").isSuccess());
    }

    @Test
    public void AppointNewOwnerRemoveAndAppintAsManager() {
        Assert.assertTrue(systemService.appointStoreOwner("Andalus", "jj", "Mira2").isSuccess());
        Assert.assertTrue(systemService.removeStoreOwner("Andalus", "jj", "Mira2").isSuccess());
        Assert.assertTrue(systemService.appointStoreManager("Andalus", "jj", "Mira2").isSuccess());
        systemService.removeStoreManager("Andalus", "jj", "Mira2").isSuccess();

    }
    @Test
    public void AppointThreeManagersTreeTest() {
        systemService.signUp(guestName,"Andalus2","102","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.appointStoreOwner("Andalus","jj","Andalus2");
        String guest2 = systemService.enterSystem().getVal();
        systemService.login(guest2,"Andalus2", "102");
        systemService.appointStoreOwner("Andalus2","jj","Mira");
        String guest3 = systemService.enterSystem().getVal();
        systemService.login(guest3,"Mira","200");
        // mira cant remove her appointee
        Assert.assertFalse(systemService.removeStoreOwner("Mira","jj","Andalus2").getVal());
        // Andalus2 cant remove her appointee
        Assert.assertFalse(systemService.removeStoreOwner("Andalus2","jj","Andalus").getVal());
        Assert.assertTrue(systemService.removeStoreOwner("Andalus","jj","Andalus2").getVal());
        boolean b1 = systemService.getStoresManagement("Andalus","jj").getVal().contains("Andalus2");
        boolean b2 = systemService.getStoresManagement("Andalus","jj").getVal().contains("Mira");
        Assert.assertTrue(!b1);
        Assert.assertTrue(!b2);





    }

}
