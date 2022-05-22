package Tests.Acceptance.User.Guest;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LoginTest {

    private SystemService systemService;
    String guestName;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "newMember", "123","newMember","newMember").getVal();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginSuccessTest() {
        Assert.assertFalse(systemService.logOut("newMember").isSuccess());
        systemService.login(guestName, "newMember", "123");
        Assert.assertTrue(systemService.logOut("newMember").isSuccess());
    }

    @Test
    public void loginFailTest() {
        Assert.assertFalse(systemService.logOut("newMember").isSuccess());
    }
}

