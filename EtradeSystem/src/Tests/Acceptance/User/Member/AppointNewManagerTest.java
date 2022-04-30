package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppointNewManagerTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.enterSystem();
        String guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "Mira", "200");
        systemService.signUp(guestName, "Andalus", "100");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void AppointNewManagerSuccessTest(){
        systemService.logOut("Andalus");
        systemService.enterSystem();
        String guestName = systemService.getOnline().getVal();
        systemService.login(guestName, "Mira", "200");
        Assert.assertFalse(systemService.addProductToStore("Mira", "Mega", "Bamba", 20, 5, "snacks").isSuccess());
        systemService.logOut("Mira");
        systemService.enterSystem();
        guestName = systemService.getOnline().getVal();
        systemService.login(guestName, "Andalus", "100");
        systemService.appointStoreManager("Andalus", "Mega", "Mira");
        Assert.assertTrue(systemService.addProductToStore("Mira", "Mega", "Bamba", 20, 5, "snacks").isSuccess());
    }

    @Test
    public void AppointNewManagerFailTest() {
        Assert.assertFalse(systemService.appointStoreManager("Andalus", "Mega", "Itay").isSuccess());
    }

}
