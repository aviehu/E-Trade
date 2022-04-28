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
        systemService.enterSystem();
        String guestName0 = systemService.getOnline().getVal();
        systemService.signUp(guestName0, "Andalus", "123");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void signUpSuccessTest(){
        int numOfMembers = systemService.getMembers().size();
        systemService.enterSystem();
        String guestName1 = systemService.getOnline().getVal();
        systemService.signUp(guestName1, "newMember", "123").getVal();
        Assert.assertTrue(numOfMembers + 1 == systemService.getMembers().size());
        Assert.assertTrue( systemService.getMembers().contains("Andalus"));

    }

    @Test
    public void signUpFailTest(){
        int numOfMembers = systemService.getMembers().size();
        systemService.enterSystem();
        String guestName1 = systemService.getOnline().getVal();
        systemService.signUp(guestName1,"Andalus", "123").getVal();
        Assert.assertTrue(numOfMembers == systemService.getMembers().size());
        Assert.assertTrue( systemService.getMembers().contains("Andalus"));
    }
}
