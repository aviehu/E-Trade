package Tests.Acceptance.User.Admin;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RemoveMemberTest {
    SystemService systemService;
    String guestName;

    @Before
    public void setUp() throws Exception {

        systemService = new SystemService();
        systemService.enterSystem();
        guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "Andalus", "100");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void RemoveMemberSuccessTest() {
        Assert.assertTrue(systemService.login(guestName, "Andalus", "100").isSuccess());
        systemService.logOut("Andalus");
        systemService.login(systemService.getOnline().getVal(), "domain", "domain");
        systemService.removeMember("domain", "Andalus");
        systemService.logOut("domain");
        Assert.assertFalse((systemService.login(systemService.getOnline().getVal(), "Andalus", "100")));
    }
}
