package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CloseStoreTest {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.enterSystem();
        String guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "Andalus", "100");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void closeStoreSuccessTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").isSuccess());
        systemService.closeStore("Andalus", "Mega");
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").isSuccess());
    }

    @Test
    public void closeStoreFailTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").isSuccess());
        systemService.closeStore("Andalus", "Mega");
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").isSuccess());
    }
}
