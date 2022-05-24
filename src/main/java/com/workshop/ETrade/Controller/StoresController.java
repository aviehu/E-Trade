package com.workshop.ETrade.Controller;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ResultPackge.ResultNum;
import com.workshop.ETrade.Service.ResultPackge.newResult;
import com.workshop.ETrade.Service.ServiceInterface;
import com.workshop.ETrade.Service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoresController {

    @Autowired
    private ServiceInterface systemService;
    @Autowired
    private SimpMessagingTemplate smt;


    @GetMapping("/info/{store}")
    public newResult<List<String>> getStoreInfo(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName) {
        return systemService.getStoreInfo(userName, storeName);
    }

    @GetMapping("/")
    public newResult<List<String>> getAllStores(@RequestHeader("Authorization") String userName) {
        return systemService.getAllStores(userName);
    }

    @PostMapping("/searchbykw")
    public newResult<List<String>> searchByKeyword(@RequestHeader("Authorization") String userName,@RequestBody String keyword) {
        return systemService.searchByKeyword(userName, keyword);
    }

    @GetMapping("/ofuser")
    public newResult<List<String>> storesOfUser(@RequestHeader("Authorization") String userName) {
        return systemService.getStoresOfUser(userName);
    }

    @PostMapping("/searchbycat")
    public newResult<List<String>> searchByCategory(@RequestHeader("Authorization") String userName,@RequestBody SearchForm form) {
        return systemService.searchByCategory(userName, form.search);
    }

    @PostMapping("/searchbyname")
    public newResult<List<String>> searchByName(@RequestHeader("Authorization") String userName, @RequestBody SearchForm form) {
        return systemService.searchByName(userName, form.search);
    }

    @PostMapping("/addproducttocart")
    public ResultMsg addProductToShoppingCart(@RequestHeader("Authorization") String userName, @RequestBody ProductForm form) {
        return systemService.addProductToShoppingCart(userName, form.productName, form.storeName, form.quantity);
    }

    @GetMapping("/displaycart")
    public newResult<List<String>> displayShoppingCart(@RequestHeader("Authorization") String userName) {
        return systemService.displayShoppingCart(userName);
    }

    @PostMapping("/removeproductfromcart")
    public ResultMsg removeProductFromShoppingCart(String userName, ProductForm form) {
        return systemService.removeProductFromShoppingCart(userName, form.storeName, form.quantity, form.productName);
    }

    @PostMapping("/purchase")
    public ResultBool purchase(@RequestHeader("Authorization") String userName,@RequestBody PurchaseForm form) {
        return systemService.purchase(userName, form.card, LocalTime.now().plusHours(2), form.cvv, form.city, form.street, form.stNum, form.apartmentNum);
    }

    @PostMapping("/openstore")
    public ResultBool openStore(@RequestHeader("Authorization") String userName, @RequestBody OpenStoreForm form) {
        return systemService.openStore(userName, form.storeName, form.card);
    }

    @PostMapping("/addproducttostore")
    public ResultBool addProductToStore(@RequestHeader("Authorization") String userName,@RequestBody NewProductForm form) {
        return systemService.addProductToStore(userName, form.storeName, form.productName, form.amount, form.price, form.category);
    }

    @PostMapping("/removeproductfromstore/{store}")
    public ResultBool removeProductFromStore(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName,@RequestBody RemoveProductForm form) {
        return systemService.removeProductFromStore(userName, storeName, form.productName);
    }

    @PostMapping("/editproductname/{name}")
    public ResultBool editProductName(String userName, EditProductForm form,@PathVariable("name") String newProductName) {
        return systemService.editProductName(userName, form.storeName, form.productName, newProductName);
    }

    @GetMapping("/getcartprice")
    public newResult<Double> getCartPrice(@RequestHeader("Authorization") String userName) {
        return systemService.getCartPrice(userName);
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

    @PostMapping("/appointowner/{store}")
    public ResultBool appointStoreOwner(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName,@RequestBody AppointForm form) {
        String msg = "you have been appointed to store owner at - " + storeName + " by - " + userName;
        smt.convertAndSend("/topic/" + form.appointee, new Notification(LocalDate.now(), "server", msg, userName));
        return systemService.appointStoreOwner(userName, storeName, form.appointee);
    }

    @PostMapping("/removeowner/{store}")
    public newResult<Boolean> removeStoreOwner(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointForm form) {
        newResult<Boolean> res = systemService.removeStoreOwner(userName, storeName, form.appointee);
        String msg = "you have been removed from store Owner at - " + storeName + " by - " + userName;
        if(res.isSuccess())
            smt.convertAndSend("/topic/" + form.appointee, new Notification(LocalDate.now(), "server", msg, userName));
        return res;
    }

    @PostMapping("/appointmanager/{store}")
    public ResultBool appointStoreManager(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName,@RequestBody AppointForm form) {
        String msg = "you have been appointed to store manager at - " + storeName + " by - " + userName;
        smt.convertAndSend("/topic/" + form.appointee, new Notification(LocalDate.now(), "server", msg, userName));
        return systemService.appointStoreManager(userName, storeName, form.appointee);
    }

    @PostMapping("/removemanager/{store}")
    public newResult<Boolean> removeStoreManager(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointForm form) {
        newResult<Boolean> res = systemService.removeStoreManager(userName, storeName, form.appointee);
        String msg = "you have been removed from store manager at - " + storeName + " by - " + userName;
        if(res.isSuccess())
            smt.convertAndSend("/topic/" + form.appointee, new Notification(LocalDate.now(), "server", msg, userName));
        return res;
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
    public ResultBool adminCloseStorePermanently(@RequestHeader("Authorization") String userName,@PathVariable("store") String storeName) {
        return systemService.adminCloseStorePermanently(userName, storeName);
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
