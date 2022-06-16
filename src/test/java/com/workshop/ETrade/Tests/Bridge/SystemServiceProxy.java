package com.workshop.ETrade.Tests.Bridge;

import com.workshop.ETrade.Controller.Forms.*;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Service.ResultPackge.Result;
import com.workshop.ETrade.Service.ServiceInterface;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class SystemServiceProxy implements ServiceInterface {

    private ServiceInterface real;

    public void setReal(ServiceInterface real){
        this.real = real;
        //real.init();
    }

    @Override
    public void init() throws Exception {
        if (real == null)
            throw new NotImplementedException();
        real.init();
    }

    @Override
    public Result<Integer> addComplexPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, ComponentPredicateForm predicateForms, String connectionType) {
        if (real == null)
            throw new NotImplementedException();
        return real.addComplexPolicy(userName, store,  policyOn, description, policyType, predicateForms,  connectionType);
    }

    @Override
    public Result<Integer> addComplexDiscount(String userName, String storeName, String discountOn, int discountPercentage, String description, DiscountType discountType, ComponentPredicateForm predicateForms, String connectionType) {
        if (real == null)
            throw new NotImplementedException();
        return real.addComplexDiscount(userName, storeName,  discountOn,  discountPercentage,  description,  discountType,  predicateForms,  connectionType);
    }

    @Override
    public Result<Double> getCartPrice(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getCartPrice(userName);
    }

    @Override
    public Result<Integer> addPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, List<PredicateForm> predicateForms, String connectionType) {
        if (real == null)
            throw new NotImplementedException();
        return real.addPolicy(userName, store, policyOn, description, policyType, predicateForms, connectionType );
    }
    @Override
    public Result<Integer> addDiscount(String userName, String store, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        if (real == null)
            throw new NotImplementedException();
        return real.addDiscount(userName, store, discountOn, discountPercentage, description, discountType);
    }

    @Override
    public Result<Double> getProdPrice(String store, String prod) {
        if (real == null)
            throw new NotImplementedException();
        return real.getProdPrice(store,prod);
    }

    @Override
    public Result<Integer> getProdAmount(String store, String prod) {
        if (real == null)
            throw new NotImplementedException();
        return real.getProdAmount(store, prod);
    }

    @Override
    public Result<List<Notification>> getMessages(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getMessages(userName);
    }

    @Override
    public Result<Boolean> isAdmin(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.isAdmin(userName);
    }

    @Override
    public Result<Integer> addPreDiscount(String userName, String storeName, String discountOn, int discountPercentage, String description, DiscountType discountType, List<PredicateForm> predicateForms, String connectionType) {
        if (real == null)
            throw new NotImplementedException();
        return real.addPreDiscount(userName, storeName, discountOn, discountPercentage, description, discountType, predicateForms, connectionType);
    }

    @Override
    public Result<Boolean> addBid(String userName, String storeName, String productName, double bidAmount, CreditCardForm creditCard, SupplyAddressForm supplyAddress) {
        if (real == null)
            throw new NotImplementedException();
        return real.addBid(userName, storeName, productName, bidAmount, creditCard, supplyAddress);
    }

    @Override
    public Result<List<BidForm>> getStoreBids(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoreBids(userName, storeName);
    }

    @Override
    public Result<Boolean> reviewBid(String userName, String storeName, int bidId, boolean approve) {
        if (real == null)
            throw new NotImplementedException();
        return real.reviewBid(userName, storeName, bidId, approve);
    }

    @Override
    public Result<Boolean> counterBid(String userName, String storeName, int bidId, double newOffer) {
        return null;
    }

    @Override
    public Result<List<BidForm>> userBids(String userName) {
        return null;
    }

    @Override
    public Result<Boolean> counterBidReview(String userName, String storeName, int bidId, boolean approve) {
        return null;
    }


    @Override
    public Result<List<String>> getOnlineMembers(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getOnlineMembers(userName);
    }

    @Override
    public Result<List<String>> getOfflineMembers(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getOfflineMembers(userName);
    }

    @Override
    public Result<Boolean> supplyServiceExists() {
        if (real == null)
            throw new NotImplementedException();
        return real.supplyServiceExists();
    }

    @Override
    public Result<Boolean> paymentServiceExists() {
        if (real == null)
            throw new NotImplementedException();
        return real.paymentServiceExists();
    }

    @Override
    public Result<Boolean> hasAdmin() {
        if (real == null)
            throw new NotImplementedException();
        return real.hasAdmin();
    }

    @Override
    public Result<List<String>> getAllStores(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getAllStores(userName);
    }

    @Override
    public Result<Boolean> removeMember(String userName, String memberToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeMember(userName, memberToRemove);
    }

    @Override
    public Result<String> enterSystem() {
        if (real == null)
            throw new NotImplementedException();
        return real.enterSystem();
    }

    @Override
    public Result<Boolean> addSystemManager(String userName, String managerToAdd) {
        if (real == null)
            throw new NotImplementedException();
        return real.addSystemManager(userName, managerToAdd);
    }

    @Override
    public Result<Boolean> removeSystemManager(String userName, String managerToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeSystemManager(userName, managerToRemove);
    }

    @Override
    public Result<Boolean> addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.addExternalPaymentService(paymentAdaptee);
    }

    @Override
    public Result<Boolean> changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeExternalPaymentService(userName, paymentAdaptee);
    }

    @Override
    public Result<Boolean> editExternalPaymentService() {
        if (real == null)
            throw new NotImplementedException();
        return real.editExternalPaymentService();
    }

    @Override
    public Result<Boolean> addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.addExternalSupplyService(supplyAdaptee);
    }

    @Override
    public Result<Boolean> changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeExternalSupplyService(userName, supplyAdaptee);
    }

    @Override
    public Result<Boolean> editExternalSupplyService() {
        if (real == null)
            throw new NotImplementedException();
        return real.editExternalSupplyService();
    }

    @Override
    public Result<Boolean> exitSystem(String name) {
        if (real == null)
            throw new NotImplementedException();
        return real.exitSystem(name);
    }

    @Override
    public Result<Boolean> signUp(String userName, String newUserName, String password, String name, String lastName) {
        if (real == null)
            throw new NotImplementedException();
        return real.signUp(userName, newUserName, password,name,lastName);
    }

    @Override
    public Result<Boolean> login(String userName, String memberUserName, String password) {
        if (real == null)
            throw new NotImplementedException();
        return real.login(userName, memberUserName, password);
    }

    @Override
    public Result<List<ProductForm>> getStoreInfo(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoreInfo(userName, storeName);
    }

    @Override
    public Result<List<String>> searchByKeyword(String userName, String keyword) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByKeyword(userName, keyword);
    }

    @Override
    public Result<List<String>> searchByCategory(String userName, String category) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByCategory(userName, category);
    }

    @Override
    public Result<List<String>> searchByName(String userName, String productName) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByName(userName, productName);
    }

    @Override
    public Result<String> addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        if (real == null)
            throw new NotImplementedException();
        return real.addProductToShoppingCart(userName, productName, storeName, quantity);
    }

    @Override
    public Result<List<ProductForm>> displayShoppingCart(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.displayShoppingCart(userName);
    }

    @Override
    public Result<String> removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeProductFromShoppingCart(userName, storeName, quantity, prodName);
    }

    @Override
    public Result<Boolean> purchase(String userName, String creditCard, int month, int year, String holderName, int cvv, int id, String country, String city, String street, int stNum, int apartmentNum, int zip) {
        if (real == null)
            throw new NotImplementedException();
        return real.purchase(userName, creditCard, month, year,holderName, cvv, id, country, city, street, stNum, apartmentNum, zip);
    }


    @Override
    public Result<String> logOut(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.logOut(userName);
    }

    @Override
    public Result<Boolean> openStore(String founderName, String storeName, int card) {
        if (real == null)
            throw new NotImplementedException();
        return real.openStore(founderName, storeName, card);
    }

    @Override
    public Result<Boolean> addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        if (real == null)
            throw new NotImplementedException();
        return real.addProductToStore(userName, storeName, productName, amount, price, category);
    }

    @Override
    public Result<Boolean> removeProductFromStore(String userName, String storeName, String productName) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeProductFromStore(userName, storeName, productName);
    }

    @Override
    public Result<Boolean> editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductName(userName, storeName, oldProductName, newProductName);
    }

    @Override
    public Result<Boolean> editProductPrice(String userName, String storeName, String ProductName, double newPrice) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductPrice(userName, storeName, ProductName, newPrice);
    }

    @Override
    public Result<Boolean> editProductQuantity(String userName, String storeName, String ProductName, int newQuantity) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductQuantity(userName, storeName, ProductName, newQuantity);
    }

    @Override
    public Result<Boolean> changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption) {
        if (real == null)
            throw new NotImplementedException();
        return real.changePurchaseOption(userName, storeName, ProductName, newOption);
    }

    @Override
    public Result<Boolean> appointStoreOwner(String userName, String storeName, String newOwner) {
        if (real == null)
            throw new NotImplementedException();
        return real.appointStoreOwner(userName, storeName, newOwner);
    }

    @Override
    public Result<Boolean> removeStoreOwner(String userName, String storeName, String ownerToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeStoreOwner(userName, storeName, ownerToRemove);
    }

    @Override
    public Result<Boolean> appointStoreManager(String userName, String storeName, String newManager) {
        if (real == null)
            throw new NotImplementedException();
        return real.appointStoreManager(userName, storeName, newManager);
    }

    @Override
    public Result<Boolean> removeStoreManager(String userName, String storeName, String managerToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeStoreManager(userName, storeName, managerToRemove);
    }

    @Override
    public Result<Boolean> changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
    }

    @Override
    public Result<Boolean> closeStore(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.closeStore(userName, storeName);
    }

    @Override
    public Result<String> getStoresManagement(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresManagement(userName, storeName);
    }

    @Override
    public Result<String> getStoresPurchaseHistory(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresPurchaseHistory(userName, storeName);
    }

    @Override
    public Result<Boolean> adminCloseStorePermanently(String adminName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.adminCloseStorePermanently(adminName, storeName);
    }

    @Override
    public Result<List<String>> getStoresOfUser(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresOfUser(userName);
    }

    @Override
    public Result<String> adminGetStoresPurchaseHistory(String adminName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.adminGetStoresPurchaseHistory(adminName, storeName);
    }

    @Override
    public Result<Boolean> addKeyword(String userName, String productName, String storeName, String keyWord) {
        if (real == null)
            throw new NotImplementedException();
        return real.addKeyword(userName, productName, storeName, keyWord);
    }

    @Override
    public Result<Integer> getProductAmount(String storeName, String prodName) {
        if(real == null)
            throw new NotImplementedException();
        return real.getProductAmount(storeName, prodName);
    }

    @Override
    public String getOnline() {
        if(real == null)
            throw new NotImplementedException();
        return real.getOnline();
    }


}