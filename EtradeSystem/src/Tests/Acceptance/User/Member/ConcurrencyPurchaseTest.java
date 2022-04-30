package Tests.Acceptance.User.Member;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class ConcurrencyPurchaseTest {
    private Thread t1;
    private Thread t2;
    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        t1 = new Thread(){
            public void run(){
                systemService.enterSystem();
                String guestName = systemService.getOnline().getVal();
                systemService.login(guestName, "Andalus", "100");
                systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 10);
                systemService.purchase("Andalus", 123, LocalTime.MAX, 776,
                        "Beer-Sheva", "Andalus", 7, 7);
            }
        };

        t2 = new Thread(){
            public void run(){
                systemService.enterSystem();
                String guestName = systemService.getOnline().getVal();
                systemService.login(guestName, "Andalus2", "200");
                systemService.addProductToShoppingCart("Andalus", "Bamba", "Mega", 10);
                systemService.purchase("Andalus2", 123, LocalTime.MAX, 776,
                        "Beer-Sheva", "Andalus", 7, 7);
            }
        };
        systemService = new SystemService();
        systemService.enterSystem();
        String guestName = systemService.getOnline().getVal();
        systemService.signUp(guestName, "Andalus", "100");
        systemService.signUp(guestName, "Andalus2", "200");
        systemService.signUp(guestName, "Seller", "123");
        systemService.login(guestName, "Seller", "123");
        systemService.openStore("Seller", "Mega", 123);
        systemService.addProductToStore("Seller", "Mega",
                "Bamba", 10, 5,"snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void concurrencyPurchaseCartTest(){
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {
        }
        Assert.assertEquals(systemService.getProductAmount("Mega", "Bamba"), 0);
        Assert.assertTrue(systemService.displayShoppingCart("Andalus").getVal().equals("") ^
                          systemService.displayShoppingCart("Andalus2").getVal().equals(""));
    }

}
