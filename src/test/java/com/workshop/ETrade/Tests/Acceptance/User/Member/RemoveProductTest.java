package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    private boolean isInList(List<ProductForm> productForms, String productName)  {
        for(ProductForm pf : productForms) {
            if(pf.productName.equals(productName)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void removeProductFromStoreSuccessTest(){
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal().first, "Bamba"));
        systemService.removeProductFromStore("Andalus", "Mega", "Bamba");
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").getVal().first.contains("Bamba"));
    }

    @Test
    public void removeProductFromStoreFailTest(){
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal().first, "Bamba"));
        Assert.assertFalse(systemService.removeProductFromStore("Andalus", "Mega", "Bisly").isSuccess());
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal().first,"Bamba"));
    }
}
