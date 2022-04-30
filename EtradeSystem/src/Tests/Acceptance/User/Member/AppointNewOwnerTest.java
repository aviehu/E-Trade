package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppointNewOwnerTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Mira", "200");
        systemService.signUp(guestName, "Andalus", "100");
        systemService.signUp(guestName, "Andalus2", "102");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.appointStoreManager("Andalus", "Mega", "Mira");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AppointNewOwnerSuccessTest(){
        systemService.logOut("Andalus");
        String guestName = systemService.enterSystem().getVal();
        systemService.login(guestName, "Mira", "200");
        Assert.assertFalse(systemService.appointStoreOwner("Mira", "Mega", "Andalus1").isSuccess());
        systemService.logOut("Mira");
        guestName = systemService.enterSystem().getVal();
        systemService.login(guestName, "Andalus", "100");
        systemService.appointStoreOwner("Andalus", "Mega", "Mira");
        Assert.assertTrue(systemService.appointStoreOwner("Mira", "Mega", "Andalus1").isSuccess());
    }

    @Test
    public void AppointNewOwnerFailTest() {
        Assert.assertFalse(systemService.appointStoreOwner("Andalus", "Mega", "Itay").isSuccess());
    }

}
