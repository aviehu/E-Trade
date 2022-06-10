package com.workshop.ETrade.Tests.Acceptance.User.Guest;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal(), "Bamba"));
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
    public void StoreInfoFailTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Shufer").isSuccess());
    }
}
