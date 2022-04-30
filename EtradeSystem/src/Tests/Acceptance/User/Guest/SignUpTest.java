package Tests.Acceptance.User.Guest;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignUpTest {

    private SystemService systemService;
    String guestName0;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        guestName0 = systemService.enterSystem().getVal();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void signUpSuccessTest(){
        Assert.assertFalse(systemService.login(guestName0, "Andalus", "100").isSuccess());
        systemService.signUp(guestName0, "Andalus", "123");
        Assert.assertTrue(systemService.login(guestName0, "Andalus", "100").isSuccess());
    }

    @Test
    public void signUpFailTest(){
        guestName0 = systemService.enterSystem().getVal();
        systemService.signUp(guestName0,"Andalus", "100").getVal();
        Assert.assertTrue(systemService.login(guestName0, "Andalus", "100").isSuccess());
        systemService.signUp(guestName0,"Andalus", "90").getVal();
        Assert.assertTrue(systemService.login(guestName0, "Andalus", "90").isSuccess());
    }
}
