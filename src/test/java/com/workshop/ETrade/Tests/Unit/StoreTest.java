package com.workshop.ETrade.Tests.Unit;

import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Tests.MockObjects.MemberMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StoreTest {

    private Store store;
    private MemberMock m1;
    private MemberMock m2;

    @Before
    public void setUp() throws Exception {
        m1 = new MemberMock("Andalus", "100");
        m2 = new MemberMock("Andalus1", "200");
        store = new Store("Mega", "Andalus", 123);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void changeStoreManagersPermission() {
        Assert.assertFalse(store.changeStoreManagersPermission("Andalus", "Andalus", managersPermission.MID));
    }

    @Test
    public void closeStoreSuccess() {
        store.closeStore("Andalus");
        Assert.assertTrue(store.isClosed());
    }

    @Test
    //not the founder - can't close
    public void closeStoreFail() {
        store.closeStore("Andalus1");
        Assert.assertFalse(store.isClosed());
    }


    @Test
    public void purchase() {
        Map<String, Integer> products = new HashMap<>();
        products.put("Bamba", 10);
        products.put("Bisly", 5);
        products.put("Laptop", 2);
        Assert.assertFalse(store.purchase(products, "Andalus1"));
    }

    @Test
    public void getHistory() {
        Assert.assertNotNull(store.getHistory("Andalus")); // owner
        Assert.assertNull(store.getHistory("Andalus1")); //just member
    }

    @Test
    public void addManager() {
        store.addManager("Andalus", "Andalus1");
       // Assert.assertTrue(store.getManagers("Andalus").contains("Andalus1"));
    }

    @Test
    public void addOwnerSuccess() {
        store.addOwner("Andalus", "Andalus1");
        Assert.assertTrue(store.getOwners("Andalus1") != null);
    }

    @Test
    public void addProduct() {
        Assert.assertTrue(store.addProduct("Andalus", "Bamba", 100, 2.5, "snacks"));
    }

    @Test
    public void removeOwner() {
        store.addOwner("Andalus", "Andalus1");
        Assert.assertTrue(store.removeOwner("Andalus", "Andalus1"));
        Assert.assertFalse(store.removeOwner("Andalus", "Andalus"));
    }

    @Test
    public void removeManager() {
        store.addManager("Andalus", "Andalus1");
        Assert.assertTrue(store.removeManager("Andalus", "Andalus1"));
    }

    @Test
    public void rateStore() {
        //TODO: after function implemented
    }

    @Test
    public void ask() {
        //TODO: after function implemented
    }

    @Test
    public void changeProductQuantity() {
        Assert.assertFalse(store.changeProductQuantity("Andalus","Bamba",70));
    }

    @Test
    public void addPolicy(){

    }

}