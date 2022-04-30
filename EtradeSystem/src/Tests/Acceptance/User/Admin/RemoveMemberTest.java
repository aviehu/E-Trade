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
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void RemoveMemberSuccessTest() {
        Assert.assertTrue(systemService.login(guestName, "Andalus", "100").getVal());
        guestName = systemService.logOut("Andalus").getVal();
        systemService.login(guestName, "domain", "domain");
        systemService.removeMember("domain", "Andalus");
        guestName = systemService.logOut("domain").getVal();
        Assert.assertFalse((systemService.login(guestName, "Andalus", "100").getVal()));
    }
}
