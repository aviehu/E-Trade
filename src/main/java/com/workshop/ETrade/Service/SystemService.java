package com.workshop.ETrade.Service;

import com.workshop.ETrade.Controller.Forms.*;
import com.workshop.ETrade.Domain.Facade;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.Purchase;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.Users.TotalTraffic;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Service.ResultPackge.Result;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SystemService implements ServiceInterface {
    private Facade facade;

    public SystemService() throws Exception {
        init();
    }

    public void init() throws Exception {
        this.facade = new Facade();
    }

    @Override
    public Result<Integer> addComplexPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, ComponentPredicateForm predicateForms, String connectionType) {
        try {
            return facade.addComplexPolicy(userName, store, policyOn, description, policyType, predicateForms, connectionType);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Integer> addComplexDiscount(String userName, String storeName, String discountOn, int discountPercentage, String description, DiscountType discountType, ComponentPredicateForm predicateForms, String connectionType) {
        try {
            return facade.addComplexDiscount(userName, storeName, discountOn, discountPercentage, description, discountType, predicateForms, connectionType);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Double> getCartPrice(String userName) {
        try {
            return facade.getCartPrice(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Integer> addPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, List<PredicateForm> predicateForms, String connectionType) {
        try {
            return facade.addPolicy(userName, store, policyOn, description, policyType, predicateForms, connectionType);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }


    @Override
    public Result<List<String>> getOnlineMembers(String userName) {
        try {
            return facade.getOnlineMembers(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<String>> getOfflineMembers(String userName) {
        try {
            return facade.getOfflineMembers(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<String>> getOnlineGuests() {
        try {
            return facade.getOnlineGuests();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }
    @Override
    public Result<List<String>> getOfflineGuests() {
        try {
            return facade.getOfflineGuests();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }
    public Result<Boolean> supplyServiceExists(){
        try {
            return facade.supplyServiceExists();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    public Result<Boolean> paymentServiceExists(){
        try {
            return facade.paymentServiceExists();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    public Result<Boolean> hasAdmin(){
        try {
            return facade.hasAdmin();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<String>> getAllStores(String userName) {
        try {
            return facade.getAllStores(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> removeMember(String userName, String memberToRemove) {
        try {
            return facade.removeMember(userName, memberToRemove);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> enterSystem() {
        try {
            return facade.enterSystem();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> addSystemManager(String userName, String managerToAdd) {
        try {
            return facade.addSystemManager(userName, managerToAdd);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> removeSystemManager(String userName, String managerToRemove) {
        try {
            return facade.removeSystemManager(userName, managerToRemove);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        try {
            return facade.addExternalPaymentService(paymentAdaptee);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee) {
        try {
            return facade.changeExternalPaymentService(userName, paymentAdaptee);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> editExternalPaymentService() {
        try {
            return facade.editExternalPaymentService();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        try {
            return facade.addExternalSupplyService(supplyAdaptee);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee) {
        try {
            return facade.changeExternalSupplyService(userName, supplyAdaptee);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> editExternalSupplyService() {
        try {
            return facade.editExternalSupplyService();
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> exitSystem(String userName) {
        try {
            return facade.exitSystem(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> signUp(String userName, String newUserName, String password, String name, String lastName) {
        try {
            return facade.signUp(userName, newUserName, password, name, lastName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> login(String userName, String memberUserName, String password) {
        try {
            return facade.login(userName, memberUserName, password);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<ProductForm>> getStoreInfo(String userName, String storeName) {
        try {
            List<Product> products = facade.getStoreInfo(userName, storeName).getVal();
            if(products == null) {
                return new Result<>(null, "Store Doesn't Exist");
            }
            List<ProductForm> formProds = new LinkedList<>();
            for(Product p : products) {
                formProds.add(new ProductForm(p, storeName));
            }
            return new Result(formProds, null);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }

    }

    @Override
    public Result<Map<String, managersPermission>> getStaffInfo(String userName, String storeName) {
        try {
            Map<String, managersPermission> staff = facade.getStaffInfo(userName, storeName).getVal();
            if(staff == null) {
                return new Result<>(null, "Store Doesn't Exist");
            }
            return new Result(staff, null);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<String>> searchByKeyword(String userName, String keyword) {
        try {
            return facade.searchByKeyword(userName, keyword);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<String>> searchByCategory(String userName, String category) {
        try {
            return facade.searchByCategory(userName, category);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<String>> searchByName(String userName, String productName) {
        try {
            return facade.searchByName(userName, productName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        try {
            return facade.addProductToShoppingCart(userName, productName, storeName, quantity);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<ProductForm>> displayShoppingCart(String userName) {
        try {
            return facade.displayShoppingCart(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName) {
        try {
            return facade.removeProductFromShoppingCart(userName, storeName, quantity, prodName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> purchase(String userName, String creditCard, int month, int year, String holderName , int cvv, int id, String country, String city, String street, int stNum, int apartmentNum, int zip) {
        try {
            return facade.purchase(userName, creditCard, month, year,holderName, cvv, id, country, city, street, stNum, apartmentNum, zip);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> logOut(String userName) {
        try {
            return facade.logOut(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> openStore(String founderName, String storeName, int card) {
        try {
            return facade.openStore(founderName, storeName, card);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        try {
            return facade.addProductToStore(userName, storeName, productName, amount, price, category);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> removeProductFromStore(String userName, String storeName, String productName) {
        try {
            return facade.removeProductFromStore(userName, storeName, productName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        try {
            return facade.editProductName(userName, storeName, oldProductName, newProductName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> editProductPrice(String userName, String storeName, String ProductName, double newPrice) {
        try {
            return facade.editProductPrice(userName, storeName, ProductName, newPrice);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> editProductQuantity(String userName, String storeName, String ProductName, int newQuantity) {
        try {
            return facade.editProductQuantity(userName, storeName, ProductName, newQuantity);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption) {
        try {
            return facade.changePurchaseOption(userName, storeName, ProductName, newOption);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> appointStoreOwner(String userName, String storeName, String newOwner) {
        try {
            return facade.appointStoreOwner(userName, storeName, newOwner);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> removeStoreOwner(String userName, String storeName, String ownerToRemove) {
        try {
            return facade.removeStoreOwner(userName, storeName, ownerToRemove);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> appointStoreManager(String userName, String storeName, String newManager) {
        try {
            return facade.appointStoreManager(userName, storeName, newManager);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> appointStoreManager(String userName, String storeName, String newManager, String permission) {
        try {
            return facade.appointStoreManager(userName, storeName, newManager, permission);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> removeStoreManager(String userName, String storeName, String managerToRemove) {
        try {
            return facade.removeStoreManager(userName, storeName, managerToRemove);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        try {
            return facade.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> closeStore(String userName, String storeName) {
        try {
            return facade.closeStore(userName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> getStoresManagement(String userName, String storeName) {
        try {
            return facade.getStoresManagement(userName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> getStoresPurchaseHistory(String userName, String storeName) {
        try {
            return facade.getStoresPurchaseHistory(userName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> adminCloseStorePermanently(String adminName, String storeName) {
        try {
            return facade.adminCloseStorePermanently(adminName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<String>> getStoresOfUser(String userName) {
        try {
            return facade.getStoresOfUser(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

//    @Override
//    public newResult<Boolean> adminTerminateUser(String adminName, String userToTerminate) {
//        return facade.adminTerminateUser(adminName, userToTerminate);
//    }

    @Override
    public Result<String> adminGetStoresPurchaseHistory(String adminName, String storeName) {
        try {
            return facade.adminGetStoresPurchaseHistory(adminName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }


    @Override
    public Result<Integer> addDiscount(String userName, String store, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        try {
            return facade.addDiscount(userName,store, discountOn, discountPercentage, description, discountType);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Double> getProdPrice(String store, String prod) {
        try {
            return facade.getProdPrice(store, prod);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Integer> getProdAmount(String store, String prod) {
        try {
            return facade.getProdAmount(store, prod);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<Notification>> getMessages(String userName) {
        try {
            return facade.getMessages(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> isAdmin(String userName) {
        try {
            return facade.isAdmin(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Integer> addPreDiscount(String userName, String storeName, String discountOn, int discountPercentage, String description, DiscountType discountType, List<PredicateForm> predicateForms, String connectionType) {
        try {
            return facade.addPreDiscount(userName, storeName, discountOn, discountPercentage, description, discountType, predicateForms, connectionType);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> addBid(String userName, String storeName, String productName, double bidAmount, CreditCardForm creditCard, SupplyAddressForm supplyAddress) {
        try {
            return facade.addBid(userName, storeName, productName, bidAmount, creditCard, supplyAddress);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<BidForm>> getStoreBids(String userName, String storeName) {
        try {
            return facade.getStoreBids(userName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> reviewBid(String userName, String storeName, int bidId, boolean approve) {
        try {
            return facade.reviewBid(userName, storeName, bidId, approve);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> counterBid(String userName, String storeName, int bidId, double newOffer) {
        try {
            return facade.counterBid(userName, storeName, bidId, newOffer);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<BidForm>> userBids(String userName) {
        try {
            return facade.userBids(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> counterBidReview(String userName, String storeName, int bidId, boolean approve) {
        try {
            return facade.counterBidReview(userName, storeName, bidId, approve);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Map<String, OwnerWaitingForApproveForm>> getOwnersWaitingForApprove(String userName, String storeName) {
        try {
            return facade.getOwnersWaitingForApprove(userName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<String> approveNewOwner(String userName, String storeName, String appointee, boolean approve) {
        try {
            return facade.approveOwner(userName, storeName,appointee, approve);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<List<Purchase>> getStorePurchaseHistory(String userName, String storeName) {
        try {
            return facade.getStorePurchaseHistory(userName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    public Result<Integer> getProductAmount(String storeName, String prodName){
        try {
            return facade.getProductAmount(storeName,prodName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    public Result<Boolean> addKeyword(String userName, String productName, String storeName, String keyWord) {
        try {
            return facade.addKeyword(userName, productName, storeName, keyWord);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }
    public void initFacade(){
        this.facade.init();
    }

    public Result<TotalTraffic> getTrafficByDates(int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay){
        try {
            return this.facade.getTrafficByDates(startYear, startMonth, startDay, endYear, endMonth, endDay);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }

    }
    public Result<Boolean> guestEnteredMarket(String userName){
        try {
            return facade.guestEnteredMarket(userName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<Boolean> editProduct(String userName, String storeName,String productName, int amount, int price) {
        try {
            return facade.editProduct(userName, storeName,productName, amount, price);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    @Override
    public Result<ManagementForm> getStoreManagement(String userName, String storeName) {
        try {
            return facade.getStoreManagement(userName, storeName);
        } catch (Exception e) {
            return new Result<>(null, e.getMessage());
        }
    }

    public void allLogOut(){
        try {
            this.facade.allLogOut();
        } catch (Exception ignored) {
        }
    }

}
