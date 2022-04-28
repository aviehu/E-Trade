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
        systemService.enterSystem();
        String guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "newMember", "123").getVal();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginSuccessTest() {
        List<String> loggedInMembers = systemService.getLoggedMembers().getVal();
        int numOfLoggedIn = loggedInMembers.size();
        systemService.login(guestName, "newMember", "123");
        Assert.assertTrue(numOfLoggedIn + 1 == systemService.getLoggedMembers().getVal().size());
        Assert.assertTrue(systemService.getLoggedMembers().getVal().contains("newMember"));
    }

    public void loginFailTest() {
        List<String> loggedInMembers = systemService.getLoggedMembers().getVal();
        int numOfLoggedIn = loggedInMembers.size();
        systemService.login(guestName, "newMember", "1234");
        Assert.assertTrue(numOfLoggedIn == systemService.getLoggedMembers().getVal().size());
        Assert.assertFalse(systemService.getLoggedMembers().getVal().contains("newMember"));
    }
}
}
