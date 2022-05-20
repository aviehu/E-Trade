package Tests.Acceptance.Concurrency;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

public class ConcurrencyAppointNewOwnerTest {

    private Thread t1;
    private Thread t2;
    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        t1 = new Thread(){
            public void run(){
                String guestName1 = systemService.enterSystem().getVal();
                systemService.login(guestName1, "Andalus", "100");
                systemService.appointStoreOwner("Andalus", "Mega", "Andalus2");
            }
        };

        t2 = new Thread(){
            public void run(){
                String guestName2 = systemService.enterSystem().getVal();
                systemService.login(guestName2, "Andalus1", "200");
                systemService.appointStoreOwner("Andalus1", "Mega", "Andalus2");
            }
        };
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100");
        systemService.signUp(guestName, "Andalus1", "200");
        systemService.signUp(guestName, "Andalus2", "300");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.appointStoreOwner("Andalus", "Mega","Andalus1");
        systemService.login("Andalus", "Andalus1", "200");
        // both Andalus & Andalus1 are now owners
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void ConcurrencyAppointNewOwnerTest(){
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ignored) {}
        String guestName = systemService.enterSystem().getVal();
        systemService.login(guestName, "Andalus2", "300");
        // check only one of the appointees succeeded
//        Assert.assertTrue(systemService.appointedBy("Mega", "Andalus2").getVal().equals("Andalus") ^
//                systemService.appointedBy("Mega", "Andalus2").getVal().equals("Andalus1"));
        // Andalus2 was added to the staff info
        //Assert.assertTrue(systemService.getStoreStaffInfo("Andalus2", "Mega").getVal().contains("Andalus2"));
    }
}
