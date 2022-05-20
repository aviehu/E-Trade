package Tests.Unit;

import Domain.Stores.PurchasePolicy;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PurchasePolicyTest {

    PurchasePolicy empty_pp;
    PurchasePolicy notempty_pp1;
    PurchasePolicy notempty_pp2;

    @Before
    public void setUp() throws Exception {
        empty_pp = new PurchasePolicy();
        List<String> prods = new ArrayList<>();
        prods.add("Bamba");
        prods.add("Bisly");
        prods.add("Laptop");
        notempty_pp1 = new PurchasePolicy(prods, 5);
        notempty_pp2 = new PurchasePolicy(prods, 20);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addProduct() {
        Assert.assertTrue(empty_pp.addProduct("Bamba"));
        Assert.assertFalse(empty_pp.addProduct("Bamba"));
    }

    @Test
    public void canPurchase() {
        Map<String, Integer> prods = new HashMap<>();
        prods.put("Bamba", 6);
        prods.put("Bisly", 10);
        Assert.assertTrue(notempty_pp1.canPurchase(prods));
        Assert.assertFalse(notempty_pp2.canPurchase(prods));
    }
}