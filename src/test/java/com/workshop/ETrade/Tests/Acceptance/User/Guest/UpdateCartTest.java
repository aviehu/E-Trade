package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UpdateCartTest {
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
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void UpdateCartSuccessTest(){
        Assert.assertTrue(isInList(systemService.displayShoppingCart("Andalus").getVal(), "Bamba"));
        systemService.removeProductFromShoppingCart("Andalus", "Mega", 5, "Bamba");
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().contains("Bamba"));
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
    public void UpdateCartFailTest(){
        systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 5);
        Assert.assertTrue(isInList(systemService.displayShoppingCart("Andalus").getVal(), "Bamba"));
        systemService.removeProductFromShoppingCart("Andalus", "Mega", 5, "Bisly");
        Assert.assertFalse(systemService.displayShoppingCart("Andalus").getVal().contains("Bisly"));
    }
}
