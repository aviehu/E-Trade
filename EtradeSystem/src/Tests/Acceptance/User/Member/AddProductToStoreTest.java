package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddProductToStoreTest {

    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void addProductToStoreSuccessTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
    }

    @Test
    public void addProductToStoreFailTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
        Assert.assertFalse(systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks").isSuccess());
    }
}
