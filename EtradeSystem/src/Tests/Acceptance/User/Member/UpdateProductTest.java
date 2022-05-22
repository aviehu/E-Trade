package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UpdateProductTest {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateProductPriceSuccessTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("5"));
        systemService.editProductPrice("Andalus", "Mega", "Bamba", 8);
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("8"));
    }

    @Test
    public void updateProductPriceFailTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("5"));
        systemService.editProductPrice("Andalus", "Mega", "Bisly", 8);
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("5"));
    }

    @Test
    public void updateProductQuantitySuccessTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("100"));
        systemService.editProductQuantity("Andalus", "Mega", "Bamba", 8);
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("8"));
    }

    @Test
    public void updateProductQuantityFailTest(){
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("5"));
        systemService.editProductQuantity("Andalus", "Mega", "Bisly", 8);
        Assert.assertTrue(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("5"));
    }
}
