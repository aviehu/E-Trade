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
        systemService.signUp("Andalus", "100");
        systemService.login("Andalus", "100");
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

    public void StoreInfoFailTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Shufer").getVal().contains("Bamba"));
    }
}
