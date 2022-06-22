package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetStaffInfoTest {

    SystemService systemService;
    String name;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        name = systemService.enterSystem().getVal();
        systemService.signUp(name, "Andalus", "100", "Anda", "lus");
        systemService.signUp(name, "Andalus2", "100", "Anda", "lus");
        systemService.login(name, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.appointStoreManager("Andalus", "Mega", "Andalus2");
        systemService.changeStoreManagersPermission("Andalus", "Mega", "Andalus2", managersPermission.LOW);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStaffInfoSuccessTest(){
        Map<String, managersPermission> staff = systemService.getStaffInfo("Andalus", "Mega").getVal();
        Assert.assertTrue(staff.containsKey("Andalus"));
        Assert.assertTrue(staff.get("Andalus2").equals(managersPermission.LOW));
        Assert.assertTrue(staff.containsKey("Andalus2"));
    }

    @Test //doesnt have permission
    public void getStaffInfoSuccessFail(){
        name = systemService.logOut("Andalus").getVal();
        systemService.login(name, "Andalus2", "100");
        Assert.assertNull(systemService.getStaffInfo("Andalus2", "Mega").getVal());

    }
}
