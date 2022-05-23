package com.workshop.ETrade.Controller;

import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ResultPackge.ResultNum;
import com.workshop.ETrade.Service.ServiceInterface;
import com.workshop.ETrade.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;


@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoresController {

    @Autowired
    private ServiceInterface systemService;


    @GetMapping("/info/{store}")
    public ResultMsg getStoreInfo(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName) {
        return systemService.getStoreInfo(userName, storeName);
    }

    @GetMapping("/searchbykw/{word}")
    public ResultMsg searchByKeyword(String userName,@PathVariable("word") String keyword) {
        return systemService.searchByKeyword(userName, keyword);
    }

    @GetMapping("/searchbycat/{cat}")
    public ResultMsg searchByCategory(String userName,@PathVariable("cat") String category) {
        return systemService.searchByCategory(userName, category);
    }

    @GetMapping("/searchbyname/{name}")
    public ResultMsg searchByName(String userName,@PathVariable("name") String productName) {
        return systemService.searchByName(userName, productName);
    }

    @PostMapping("/addproducttocart")
    public ResultMsg addProductToShoppingCart(String userName, ProductForm form) {
        return systemService.addProductToShoppingCart(userName, form.productName, form.storeName, form.quantity);
    }

    @GetMapping("/displaycart")
    public ResultMsg displayShoppingCart(String userName) {
        return systemService.displayShoppingCart(userName);
    }

    @PostMapping("/removeproductfromcart")
    public ResultMsg removeProductFromShoppingCart(String userName, ProductForm form) {
        return systemService.removeProductFromShoppingCart(userName, form.storeName, form.quantity, form.productName);
    }

    @PostMapping("/puchase")
    public ResultBool purchase(String userName, int card, LocalTime expDate, int cvv, String city, String street, int stNum, int apartmentNum) {
        return systemService.purchase(userName, card, expDate, cvv, city, street, stNum, apartmentNum);
    }

    @PostMapping("/openstore")
    public ResultBool openStore(@RequestHeader("Authorization") String userName, @RequestBody OpenStoreForm form) {
        return systemService.openStore(userName, form.storeName, form.card);
    }

    @PostMapping("/addproducttostore")
    public ResultBool addProductToStore(String userName, NewProductForm form) {
        return systemService.addProductToStore(userName, form.storeName, form.productName, form.amount, form.price, form.category);
    }

    @GetMapping("/removeproductfromstore/{store}/{product}")
    public ResultBool removeProductFromStore(String userName, @PathVariable("store") String storeName,@PathVariable("product") String productName) {
        return systemService.removeProductFromStore(userName, storeName, productName);
    }

    @PostMapping("/editproductname/{name}")
    public ResultBool editProductName(String userName, EditProductForm form,@PathVariable("name") String newProductName) {
        return systemService.editProductName(userName, form.storeName, form.productName, newProductName);
    }

    @PostMapping("/editproductprice/{price}")
    public ResultBool editProductPrice(String userName, EditProductForm form,@PathVariable("price") double newPrice) {
        return systemService.editProductPrice(userName, form.storeName, form.productName, newPrice);
    }

    @PostMapping("/editproductquantity/{quantity}")
    public ResultBool editProductQuantity(String userName, EditProductForm form,@PathVariable("quantity") int newQuantity) {
        return systemService.editProductQuantity(userName, form.storeName, form.productName, newQuantity);
    }

    @PostMapping("/changepurchaseoption")//TODO: option?
    public ResultBool changePurchaseOption(String userName, EditProductForm form, purchaseOption newOption) {
        return systemService.changePurchaseOption(userName, form.storeName, form.productName, newOption);
    }

    @GetMapping("/appointowner/{store}/{user}")
    public ResultBool appointStoreOwner(String userName,@PathVariable("store") String storeName,@PathVariable("user") String newOwner) {
        return systemService.appointStoreOwner(userName, storeName, newOwner);
    }

    @GetMapping("/appointmanager/{store}/{user}")
    public ResultBool appointStoreManager(String userName,@PathVariable("store") String storeName,@PathVariable("user") String newManager) {
        return systemService.appointStoreManager(userName, storeName, newManager);
    }

    @GetMapping("/changePermission")//TODO:permission?
    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        return systemService.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
    }

    @GetMapping("/closestore/{store}")
    public ResultBool closeStore(String userName,@PathVariable("store") String storeName) {
        return systemService.closeStore(userName, storeName);
    }

    @GetMapping("/storemanagers/{store}")
    public ResultMsg getStoresManagement(String userName,@PathVariable("store") String storeName) {
        return systemService.getStoresManagement(userName, storeName);
    }

    @GetMapping("/gethistory/{store}")
    public ResultMsg getStoresPurchaseHistory(String userName,@PathVariable("store")  String storeName) {
        return systemService.getStoresPurchaseHistory(userName, storeName);
    }

    @GetMapping("/admin/closepermanent/{store}")
    public ResultBool adminCloseStorePermanently(String adminName,@PathVariable("store") String storeName) {
        return systemService.adminCloseStorePermanently(adminName, storeName);
    }

    @GetMapping("/admin/gethistory/{store}")
    public ResultMsg adminGetStoresPurchaseHistory(String adminName,@PathVariable("store") String storeName) {
        return systemService.adminGetStoresPurchaseHistory(adminName, storeName);
    }

    @GetMapping("/productamount/{store}/{product}")
    public ResultNum getProductAmount(@PathVariable("store") String storeName,@PathVariable("product") String prodName){
        return systemService.getProductAmount(storeName,prodName);
    }

    @PostMapping("/addkeyword/{keyword}")
    public ResultBool addKeyword(String userName, EditProductForm form, String keyWord) {
        return systemService.addKeyword(userName, form.productName, form.storeName, keyWord);
    }
}
