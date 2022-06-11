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
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.signUp(guestName, "Andalus2", "102","Andalus2","Andalus2");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        //systemService.appointStoreManager("Andalus", "Mega", "Mira");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AppointNewOwnerSuccessTest(){
        Assert.assertTrue(systemService.appointStoreOwner("Andalus", "Mega", "Mira").isSuccess());
        String guestName = systemService.logOut("Andalus").getVal();
        systemService.login(guestName, "Mira", "200");
        Assert.assertTrue(systemService.appointStoreOwner("Mira", "Mega", "Andalus2").isSuccess());
        systemService.login(guestName, "Andalus", "100");
        systemService.removeStoreOwner("Andalus","Mega","Mira");

    }

    @Test
    public void AppointNewOwnerFailTest() {
        Assert.assertFalse(systemService.appointStoreOwner("Andalus", "Mega", "Itay").isSuccess());
    }

    @Test
    public void AppointNewOwnerRemoveAndAppintAsManager() {
        Assert.assertTrue(systemService.appointStoreOwner("Andalus", "Mega", "Mira").isSuccess());
        Assert.assertTrue(systemService.removeStoreOwner("Andalus", "Mega", "Mira").isSuccess());
        Assert.assertTrue(systemService.appointStoreManager("Andalus", "Mega", "Mira").isSuccess());

    }
    @Test
    public void AppointThreeManagersTreeTest() {
        systemService.signUp(guestName,"Andalus2","102","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.appointStoreOwner("Andalus","Mega","Andalus2");
        String guest2 = systemService.enterSystem().getVal();
        systemService.login(guest2,"Andalus2", "102");
        systemService.appointStoreOwner("Andalus2","Mega","Mira");
        String guest3 = systemService.enterSystem().getVal();
        systemService.login(guest3,"Mira","200");
        // mira cant remove her appointee
        Assert.assertFalse(systemService.removeStoreOwner("Mira","Mega","Andalus2").getVal());
        // Andalus2 cant remove her appointee
        Assert.assertFalse(systemService.removeStoreOwner("Andalus2","Mega","Andalus").getVal());
        Assert.assertTrue(systemService.removeStoreOwner("Andalus","Mega","Andalus2").getVal());
        boolean b1 = systemService.getStoresManagement("Andalus","Mega").getVal().contains("Andalus2");
        boolean b2 = systemService.getStoresManagement("Andalus","Mega").getVal().contains("Mira");
        Assert.assertTrue(!b1);
        Assert.assertTrue(!b2);





    }

}
