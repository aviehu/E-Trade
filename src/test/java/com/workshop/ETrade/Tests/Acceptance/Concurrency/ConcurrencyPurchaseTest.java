package com.workshop.ETrade.Tests.Acceptance.Concurrency;

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

import java.time.LocalTime;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcurrencyPurchaseTest {
    private Thread t1;
    private Thread t2;
    @Autowired
    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        t1 = new Thread(){
            public void run(){
                systemService.purchase("Andalus", "123", 4,2028,"Andalus Andalus", 776,202020202,"Israel",
                        "BeerSheva", "Andalusia", 7, 7,4590011);
            }
        };

        t2 = new Thread(){
            public void run(){
                systemService.purchase("Andalus2", "123",2,2024,"Andalus Andalus", 776,200000000,"Israel",
                        "BeerSheva", "Andalusia", 7, 7,4333222);
            }
        };
        //systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.signUp(guestName, "Andalus2", "200","Andalus","Andalus");
        systemService.signUp(guestName, "Seller", "123","sell","seller");
        systemService.login(guestName, "Seller", "123");
        systemService.openStore("Seller", "victory", 123);
        systemService.addProductToStore("Seller", "victory",
                "Bamba", 10, 5,"snacks");
        systemService.login("Seller", "Andalus", "100");
        systemService.addProductToShoppingCart("Andalus", "Bamba", "victory", 10);
        systemService.login("Seller", "Andalus2", "200");
        systemService.addProductToShoppingCart("Andalus2", "Bamba", "victory", 10);
    }

    @After
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
    public void concurrencyPurchaseCartTest(){
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException ignored) {}
        Assert.assertEquals(0, (long)systemService.getProductAmount("victory", "Bamba").getVal());
        Assert.assertTrue(isInList(systemService.displayShoppingCart("Andalus").getVal(), "Bamba") ^
                          isInList(systemService.displayShoppingCart("Andalus2").getVal(), "Bamba"));
    }

}
