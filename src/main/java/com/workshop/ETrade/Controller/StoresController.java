package com.workshop.ETrade.Controller;

import com.workshop.ETrade.Controller.Forms.*;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ResultPackge.ResultNum;
import com.workshop.ETrade.Service.ResultPackge.newResult;
import com.workshop.ETrade.Service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoresController {

    @Autowired
    private ServiceInterface systemService;
    @Autowired
    private SimpMessagingTemplate smt;

    @PostMapping("/changepurchaseoption/{store}")
    public newResult<Boolean> changePurchaseOption(@RequestHeader("Authorization") String userName, @RequestBody ChangePurchaseOptionForm form, @PathVariable("store") String storeName) {
        return systemService.changePurchaseOption(userName, storeName, form.productName, form.purchaseOption);
    }

    @GetMapping("/info/{store}")
    public newResult<List<com.workshop.ETrade.Controller.Forms.ProductForm>> getStoreInfo(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName) {
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
    public newResult<List<com.workshop.ETrade.Controller.Forms.ProductForm>> displayShoppingCart(@RequestHeader("Authorization") String userName) {
        return systemService.displayShoppingCart(userName);
    }

    @PostMapping("/removeproductfromcart")
    public ResultMsg removeProductFromShoppingCart(String userName, ProductForm form) {
        return systemService.removeProductFromShoppingCart(userName, form.storeName, form.quantity, form.productName);
    }

    @PostMapping("/addsimplediscount/{store}")
    public newResult<Integer> addSimpleDiscount(@RequestHeader("Authorization") String userName,@PathVariable("store") String storeName, @RequestBody SimpleDiscountForm form) {
        DiscountType discountType = DiscountType.PRODUCT;
        switch (form.type) {
            case "category":
                discountType = DiscountType.CATEGORY;
                break;
            case "store":
                discountType = DiscountType.STORE;
                break;
            default:
        }
        return systemService.addDiscount(userName, storeName, form.discountOn, form.discountPercentage, form.description, discountType);
    }

    @PostMapping("/addprediscount/{store}")
    public newResult<Integer> addPreDiscount(@RequestHeader("Authorization") String userName,@PathVariable("store") String storeName, @RequestBody PredicateDiscountForm form) {
        DiscountType discountType = DiscountType.PRODUCT;
        switch (form.type) {
            case "category":
                discountType = DiscountType.CATEGORY;
                break;
            case "store":
                discountType = DiscountType.STORE;
                break;
            default:
        }
        return systemService.addPreDiscount(userName, storeName, form.discountOn, form.discountPercentage, form.description, discountType, form.predicates, form.connectionType);
    }

    @PostMapping("/addpolicy/{store}")
    public newResult<Integer> addPolicy(@RequestHeader("Authorization") String userName,@PathVariable("store") String storeName, @RequestBody PolicyForm form) {
        PolicyType policyType = PolicyType.BASKET;
        switch (form.type) {
            case "product":
                policyType = PolicyType.PRODUCT;
                break;
            case "category":
                policyType = PolicyType.CATEGORY;
                break;
            default:
        }
        return systemService.addPolicy(userName,storeName, form.policyOn, form.description, policyType,form.predicates, form.connectionType);
    }

    @PostMapping("/purchase")
    public ResultBool purchase(@RequestHeader("Authorization") String userName,@RequestBody PurchaseForm form) {
        //return systemService.purchase(userName, form.card, 4,2028,"Israel Israel", form.cvv,200000000,"Israel", form.city, form.street, form.stNum, form.apartmentNum, 8454202);
        return systemService.purchase(userName, form.card, form.month,form.year,form.holderName, form.cvv,form.id,form.country, form.city, form.street, form.stNum, form.apartmentNum, form.zip);
    }

    @PostMapping("/openstore")
    public ResultBool openStore(@RequestHeader("Authorization") String userName, @RequestBody OpenStoreForm form) {
        return systemService.openStore(userName, form.storeName, form.card);
    }

    @PostMapping("/addproducttostore")
    public ResultBool addProductToStore(@RequestHeader("Authorization") String userName,@RequestBody NewProductForm form) {
        return systemService.addProductToStore(userName, form.storeName, form.productName, form.amount, form.price, form.category);
    }

    @PostMapping("/addbid/{store}")
    public newResult<Boolean> addBid(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName,@RequestBody AddBidForm form) {
        return systemService.addBid(userName, storeName, form.productName, form.bidAmount, form.creditCardForm, form.supplyAddressForm);
    }

    @PostMapping("/counterbid/{store}")
    public newResult<Boolean> counterBid(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName,@RequestBody CounterBidForm form) {
        return systemService.counterBid(userName,storeName,form.bidId,form.newOffer);
    }

    @GetMapping("/bids/{store}")
    public newResult<List<BidForm>> getStoreBids(@RequestHeader("Authorization") String userName,@PathVariable("store") String storeName) {
        return systemService.getStoreBids(userName, storeName);
    }

    @PostMapping("/offerreview/{store}")
    public  newResult<Boolean> counterBidReview(@RequestHeader("Authorization") String userName,@PathVariable("store") String storeName,@RequestBody ReviewBidForm form) {
        return systemService.counterBidReview(userName,storeName,form.bidId, form.approve);
    }

    @PostMapping("/reviewbid/{store}")
    public newResult<Boolean> reviewBid(@RequestHeader("Authorization") String userName,@PathVariable("store") String storeName, @RequestBody ReviewBidForm form) {
        return systemService.reviewBid(userName, storeName, form.bidId, form.approve);
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

    @PostMapping("/appointowner/{store}")
    public ResultBool appointStoreOwner(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName,@RequestBody AppointForm form) {
        return systemService.appointStoreOwner(userName, storeName, form.appointee);
    }

    @PostMapping("/removeowner/{store}")
    public newResult<Boolean> removeStoreOwner(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointForm form) {
        newResult<Boolean> res = systemService.removeStoreOwner(userName, storeName, form.appointee);
        return res;
    }

    @PostMapping("/appointmanager/{store}")
    public ResultBool appointStoreManager(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName,@RequestBody AppointForm form) {
        return systemService.appointStoreManager(userName, storeName, form.appointee);
    }

    @PostMapping("/removemanager/{store}")
    public newResult<Boolean> removeStoreManager(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointForm form) {
        newResult<Boolean> res = systemService.removeStoreManager(userName, storeName, form.appointee);
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
