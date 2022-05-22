package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RemoveProductTest {

    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100", "Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void removeProductFromStoreSuccessTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
        systemService.removeProductFromStore("Andalus", "Mega", "Bamba");
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
    }

    @Test
    public void removeProductFromStoreFailTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
        Assert.assertFalse(systemService.removeProductFromStore("Andalus", "Mega", "Bisly").isSuccess());
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
    }
}
