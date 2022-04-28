package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LogoutTest {
    private SystemService systemService;
    String guestName;
    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.enterSystem();
        guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "newMember", "123").getVal();
        systemService.login(guestName, "newMember", "123");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void logoutSuccessTest() {
        List<String> loggedInMembers = systemService.getLoggedMembers().getVal();
        int numOfLoggedIn = loggedInMembers.size();
        systemService.logOut("newMember");
        Assert.assertTrue(numOfLoggedIn - 1 == systemService.getLoggedMembers().getVal().size());
        Assert.assertFalse(systemService.getLoggedMembers().getVal().contains("newMember"));
    }

    @Test
    public void logoutFailTest() {
        systemService.login(systemService.getOnline().getVal(), "newMember", "123");
        List<String> loggedInMembers = systemService.getLoggedMembers().getVal();
        int numOfLoggedIn = loggedInMembers.size();
        systemService.logOut("Andalus");
        Assert.assertTrue(numOfLoggedIn == systemService.getLoggedMembers().getVal().size());
        Assert.assertTrue(systemService.getLoggedMembers().getVal().contains("newMember"));
    }
}
