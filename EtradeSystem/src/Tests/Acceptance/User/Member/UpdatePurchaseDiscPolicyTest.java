package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdatePurchaseDiscPolicyTest {

    private SystemService systemService;
    String guestName;


    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        guestName = systemService.enterSystem().getVal();
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void UpdatePurchaseDiscPolicySuccessTest(){
       

    }
}
