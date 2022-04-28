package Tests.Acceptance.User.Guest;

import Service.SystemService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SearchProductTest {

    private SystemService systemService;

    @Before
    public void setUp() throws Exception {
        systemService = new SystemService();
        systemService.signUp("Andalus", "100");
        systemService.login("Andalus", "100");
        systemService.openStore("Andalus", "Mega", 123);
        systemService.addProductToStore("Andalus", "Mega",
                "Bamba", 100, 5,"snacks");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchProdByCategorySuccessTest(){
        Assert.assertTrue(systemService.searchByCategory("Andalus", "snacks").getVal().contains("Bamba"));
    }

    @Test
    public void searchProdByCategoryFailTest(){
        Assert.assertFalse(systemService.searchByCategory("Andalus", "drinks").getVal().contains("Bamba"));
    }
    @Test
    public void searchProdByKeyWordSuccessTest(){
        Assert.assertTrue(systemService.searchByKeyword("Andalus", "Bam").getVal().contains("Bamba"));
    }

    @Test
    public void searchProdByKeyWordFailTest(){
        Assert.assertFalse(systemService.searchByKeyword("Andalus", "Ban").getVal().contains("Bamba"));
    }
    @Test
    public void searchProdByNameSuccessTest(){
        Assert.assertTrue(systemService.searchByName("Andalus", "Bamba").getVal().contains("Bamba"));
    }

    @Test
    public void searchProdByNameFailTest(){
        Assert.assertFalse(systemService.searchByName("Andalus", "Bisly").getVal().contains("Bamba"));
    }

}
