package Tests.Acceptance.User.Guest;

import Service.SystemService;
import org.junit.Assert;
import org.junit.Test;

public class CheckCartContentsTest {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.enterSystem();
        String guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "Andalus", "100");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void CheckCartContentsSuccessTest(){
        String cartInfo = systemService.displayShoppingCart("Andalus").getVal();
        Assert.assertFalse(cartInfo.contains("Bamba"));
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);
        Assert.assertTrue(systemService.displayShoppingCart("Andalus").getVal().contains("Bamba"));
    }

    @Test
    public void CheckCartContentsFailTest(){
        systemService.addProductToShoppingCart("Andalus", "Bisly", "Mega", 5);
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().contains("Bisly"));
    }
}
