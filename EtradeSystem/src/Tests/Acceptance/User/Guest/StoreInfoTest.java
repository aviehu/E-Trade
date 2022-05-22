package Tests.Acceptance.User.Guest;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoreInfoTest {

    private SystemService systemService;
    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName1 = systemService.enterSystem().getVal();
        systemService.signUp(guestName1,"Andalus", "100","Andalus","Andalus");
        systemService.login(guestName1,"Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void StoreInfoSuccessTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
    }

    @Test
    public void StoreInfoFailTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Shufer").isSuccess());
    }
}
