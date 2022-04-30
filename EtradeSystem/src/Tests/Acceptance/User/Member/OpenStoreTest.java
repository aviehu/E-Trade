package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OpenStoreTest {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.enterSystem();
        String guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "Andalus", "100");
        systemService.login(guestName, "Andalus", "100");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void openStoreSuccessTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").isSuccess());
        systemService.openStore("Andalus", "Mega", 123);
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").isSuccess());
    }

    @Test
    public void openStoreFailTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").isSuccess());
        systemService.openStore("Andalus", "Mega", 123);
        Assert.assertFalse(systemService.openStore("Andalus", "Mega", 123).isSuccess());
    }
}
