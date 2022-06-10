package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UpdateManagerPermissionTest {
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
    public void updateManagerPermissionSuccessTest(){
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal(), "Bamba"));
        systemService.removeProductFromStore("Andalus", "Mega", "Bamba");
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").getVal().contains("Bamba"));
    }

    private boolean isInList(List<ProductForm> productForms, String productName)  {
        for(ProductForm pf : productForms) {
            if(pf.productName.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void removeProductFromStoreFailTest(){
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal(), "Bamba"));
        Assert.assertFalse(systemService.removeProductFromStore("Andalus", "Mega", "Bisly").isSuccess());
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal(), "Bamba"));
    }
}
