package com.workshop.ETrade.Controller;

import com.workshop.ETrade.Controller.Forms.*;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Purchase;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Service.ResultPackge.Result;
import com.workshop.ETrade.Service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/stores")
@CrossOrigin
public class StoresController {

    @Autowired
    private ServiceInterface systemService;
    @Autowired
    private SimpMessagingTemplate smt;

    @PostMapping("/changepurchaseoption/{store}")
    public Result<Boolean> changePurchaseOption(@RequestHeader("Authorization") String userName, @RequestBody ChangePurchaseOptionForm form, @PathVariable("store") String storeName) {
        return systemService.changePurchaseOption(userName, storeName, form.productName, form.purchaseOption);
    }

    @GetMapping("/getmanagement/{store}")
    public Result<ManagementForm> getStoreManagement(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName){
        return systemService.getStoreManagement(userName, storeName);
    }

    @PostMapping("/editproduct/{store}")
    public Result<Boolean> editProduct(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody EditProductForm form) {
        return systemService.editProduct(userName, storeName,form.productName,form.amount, form.price);
    }

    @GetMapping("/info/{store}")
    public Result<List<ProductForm>> getStoreInfo(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName) {
        return systemService.getStoreInfo(userName, storeName);
    }

    @GetMapping("/approveowner/getwaiting/{store}")
    public Result<Map<String, OwnerWaitingForApproveForm>> getOwnersWaitingForApprove(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName ) {
        return systemService.getOwnersWaitingForApprove(userName, storeName);
    }

    @PostMapping("/approveowner/approve/{store}")
    public Result<String> approveOwner(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody ApproveAppointmentForm form) {
        return systemService.approveNewOwner(userName, storeName, form.appointee, form.approve);
    }

    @GetMapping("/")
    public Result<List<String>> getAllStores(@RequestHeader("Authorization") String userName) {
        return systemService.getAllStores(userName);
    }

    @PostMapping("/searchbykw")
    public Result<List<ProductForm>> searchByKeyword(@RequestHeader("Authorization") String userName, @RequestBody String keyword) {
        return systemService.searchByKeyword(userName, keyword);
    }

    @GetMapping("/ofuser")
    public Result<List<String>> storesOfUser(@RequestHeader("Authorization") String userName) {
        return systemService.getStoresOfUser(userName);
    }

    @PostMapping("/searchbycat")
    public Result<List<String>> searchByCategory(@RequestHeader("Authorization") String userName, @RequestBody SearchForm form) {
        return systemService.searchByCategory(userName, form.search);
    }

    @PostMapping("/searchbyname")
    public Result<List<String>> searchByName(@RequestHeader("Authorization") String userName, @RequestBody SearchForm form) {
        return systemService.searchByName(userName, form.search);
    }

    @PostMapping("/addproducttocart")
    public Result<String> addProductToShoppingCart(@RequestHeader("Authorization") String userName, @RequestBody ShoppingCartProductForm form) {
        return systemService.addProductToShoppingCart(userName, form.productName, form.storeName, form.quantity);
    }

    @GetMapping("/displaycart")
    public Result<List<ProductForm>> displayShoppingCart(@RequestHeader("Authorization") String userName) {
        return systemService.displayShoppingCart(userName);
    }

    @PostMapping("/removeproductfromcart")
    public Result<String> removeProductFromShoppingCart(@RequestHeader("Authorization") String userName, @RequestBody ShoppingCartProductForm form) {
        return systemService.removeProductFromShoppingCart(userName, form.storeName, form.quantity, form.productName);
    }

    @PostMapping("/addsimplediscount/{store}")
    public Result<Integer> addSimpleDiscount(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody SimpleDiscountForm form) {
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
    public Result<Integer> addPreDiscount(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody PredicateDiscountForm form) {
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
        return systemService.addPreDiscount(userName, storeName, form.discountOn, form.discountPercentage, form.description, discountType, form.predicateForms, form.connectionType);
    }

    @PostMapping("/addpolicy/{store}")
    public Result<Integer> addPolicy(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody PolicyForm form) {
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
        return systemService.addPolicy(userName,storeName, form.policyOn, form.description, policyType,form.predicateForms, form.connectionType);
    }

    @PostMapping("/purchase")
    public Result<Boolean> purchase(@RequestHeader("Authorization") String userName, @RequestBody PurchaseForm form) {
        return systemService.purchase(userName, form.card, form.month,form.year,form.holderName, form.cvv,form.id,form.country, form.city, form.street, form.stNum, form.apartmentNum, form.zip);
    }

    @PostMapping("/openstore")
    public Result<Boolean> openStore(@RequestHeader("Authorization") String userName, @RequestBody OpenStoreForm form) {
        return systemService.openStore(userName, form.storeName, form.card);
    }

    @PostMapping("/addproducttostore")
    public Result<Boolean> addProductToStore(@RequestHeader("Authorization") String userName, @RequestBody NewProductForm form) {
        return systemService.addProductToStore(userName, form.storeName, form.productName, form.amount, form.price, form.category);
    }

    @PostMapping("/addbid/{store}")
    public Result<Boolean> addBid(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AddBidForm form) {
        return systemService.addBid(userName, storeName, form.productName, form.bidAmount, form.creditCardForm, form.supplyAddressForm);
    }

    @PostMapping("/counterbid/{store}")
    public Result<Boolean> counterBid(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody CounterBidForm form) {
        return systemService.counterBid(userName,storeName,form.bidId,form.newOffer);
    }

    @GetMapping("/bids/{store}")
    public Result<List<BidForm>> getStoreBids(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName) {
        return systemService.getStoreBids(userName, storeName);
    }

    @PostMapping("/offerreview/{store}")
    public Result<Boolean> counterBidReview(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody ReviewBidForm form) {
        return systemService.counterBidReview(userName,storeName,form.bidId, form.approve);
    }

    @PostMapping("/reviewbid/{store}")
    public Result<Boolean> reviewBid(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody ReviewBidForm form) {
        return systemService.reviewBid(userName, storeName, form.bidId, form.approve);
    }

    @PostMapping("/removeproductfromstore/{store}")
    public Result<Boolean> removeProductFromStore(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody RemoveProductForm form) {
        return systemService.removeProductFromStore(userName, storeName, form.productName);
    }

    @GetMapping("/getcartprice")
    public Result<Double> getCartPrice(@RequestHeader("Authorization") String userName) {
        return systemService.getCartPrice(userName);
    }

    @GetMapping("/storepurchasehistory/{store}")
    public Result<List<PurchaseHistoryForm>> getStorePurchaseHistory(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName) {
        Result<List<Purchase>> serviceAns = systemService.getStorePurchaseHistory(userName,storeName);
        if(serviceAns.isSuccess()) {
            List<PurchaseHistoryForm> ans = new ArrayList<>();
            for(Purchase p : serviceAns.getVal()) {
                ans.add(new PurchaseHistoryForm(p));
            }
            return new Result<>(ans, null);
        }
        return new Result<>(null, serviceAns.getErr());
    }

    @PostMapping("/appointowner/{store}")
    public Result<String> appointStoreOwner(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointOwnerForm form) {
        return systemService.appointStoreOwner(userName, storeName, form.appointee);
    }

    @PostMapping("/removeowner/{store}")
    public Result<Boolean> removeStoreOwner(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointOwnerForm form) {
        Result<Boolean> res = systemService.removeStoreOwner(userName, storeName, form.appointee);
        return res;
    }

    @PostMapping("/appointmanager/{store}")
    public Result<Boolean> appointStoreManager(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointManagerForm form) {
        return systemService.appointStoreManager(userName, storeName, form.appointee, form.permission);
    }

    @PostMapping("/removemanager/{store}")
    public Result<Boolean> removeStoreManager(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName, @RequestBody AppointOwnerForm form) {
        Result<Boolean> res = systemService.removeStoreManager(userName, storeName, form.appointee);
        return res;
    }

    @GetMapping("/changePermission")//TODO:permission?
    public Result<Boolean> changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        return systemService.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
    }

    @GetMapping("/closestore/{store}")
    public Result<Boolean> closeStore(String userName, @PathVariable("store") String storeName) {
        return systemService.closeStore(userName, storeName);
    }

    @GetMapping("/storemanagers/{store}")
    public Result<String> getStoresManagement(String userName, @PathVariable("store") String storeName) {
        return systemService.getStoresManagement(userName, storeName);
    }

    @GetMapping("/gethistory/{store}")
    public Result<String> getStoresPurchaseHistory(String userName, @PathVariable("store")  String storeName) {
        return systemService.getStoresPurchaseHistory(userName, storeName);
    }

    @GetMapping("/admin/closepermanent/{store}")
    public Result<Boolean> adminCloseStorePermanently(@RequestHeader("Authorization") String userName, @PathVariable("store") String storeName) {
        return systemService.adminCloseStorePermanently(userName, storeName);
    }

    @GetMapping("/admin/gethistory/{store}")
    public Result<String> adminGetStoresPurchaseHistory(String adminName, @PathVariable("store") String storeName) {
        return systemService.adminGetStoresPurchaseHistory(adminName, storeName);
    }

    @GetMapping("/productamount/{store}/{product}")
    public Result<Integer> getProductAmount(@PathVariable("store") String storeName, @PathVariable("product") String prodName){
        return systemService.getProductAmount(storeName,prodName);
    }
}
