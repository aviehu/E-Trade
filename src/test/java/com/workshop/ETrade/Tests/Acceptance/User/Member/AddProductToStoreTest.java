package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddProductToStoreTest {
    @Autowired
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        //systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
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
    public void addProductToStoreSuccessTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").getVal().first.contains("Bamba"));
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
        Assert.assertTrue(isInList(systemService.getStoreInfo("Andalus", "Mega").getVal().first,"Bamba"));
    }

    @Test
    public void addProductToStoreFailTest(){
        Assert.assertFalse(systemService.getStoreInfo("Andalus", "Mega").getVal().first.contains("Bamba"));
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
        Assert.assertFalse(systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks").isSuccess()); //already exists
    }
}
