package Tests.Acceptance.User.Guest;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnterSystemAsGuestTest {
    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void EnterSystemAsGuestTest(){
        systemService.enterSystem();
        String name = systemService.getOnline().getVal();
        Assert.assertTrue(name.contains("guest"));
    }
}
