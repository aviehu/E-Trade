package Tests.Acceptance.User.Guest;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class PurchaseCartTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void purchaseCartSuccessTest(){
        systemService.purchase("Andalus", 123, LocalTime.MAX, 776,
                "Beer-Sheva", "Andalus", 7, 7);
        Assert.assertEquals("", systemService.displayShoppingCart("Andalus").getVal());
        Assert.assertEquals(90, systemService.getProductAmount("Mega", "Bamba"));
    }

    @Test
    public void purchaseCartFailTest(){
        String cart = systemService.displayShoppingCart("Andalus").getVal();
        int prodAmount = systemService.getProductAmount("Mega", "Bamba");
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 200);
        systemService.purchase("Andalus", 123, LocalTime.MAX, 776,
                "Beer-Sheva", "Andalus", 7, 7);
        Assert.assertEquals(cart, systemService.displayShoppingCart("Andalus").getVal());
        Assert.assertEquals(prodAmount, systemService.getProductAmount("Mega", "Bamba"));
    }
}
