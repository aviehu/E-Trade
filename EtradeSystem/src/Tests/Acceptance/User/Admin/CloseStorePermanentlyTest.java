package Tests.Acceptance.User.Admin;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CloseStorePermanentlyTest {
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
