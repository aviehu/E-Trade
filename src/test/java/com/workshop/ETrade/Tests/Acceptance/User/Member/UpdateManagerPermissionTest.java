package com.workshop.ETrade.Tests.Acceptance.User.Member;

import com.workshop.ETrade.Controller.Forms.ProductForm;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateManagerPermissionTest {
    private SystemService systemService;

    @org.junit.Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        String guestName = systemService.enterSystem().getVal();
        systemService.signUp(guestName, "Andalus", "100","Andalus","Andalus");
        systemService.signUp(guestName, "Andalus2", "100","Andalus","Andalus");
        systemService.login(guestName, "Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.appointStoreManager("Andalus", "Mega", "Andalus2");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void updateManagerPermissionSuccessTest() {
        Assert.assertTrue(systemService.changeStoreManagersPermission("Andalus", "Mega", "Andalus2", managersPermission.MID).isSuccess());
    }


    @Test
    public void updateManagerPermissionFailTest() {
        Assert.assertFalse(systemService.changeStoreManagersPermission("Andalus", "Mega", "Anda", managersPermission.MID).isSuccess());
    }
}
