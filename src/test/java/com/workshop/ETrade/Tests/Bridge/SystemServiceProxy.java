package com.workshop.ETrade.Tests.Bridge;

import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ResultPackge.ResultNum;
import com.workshop.ETrade.Service.ResultPackge.newResult;
import com.workshop.ETrade.Service.ServiceInterface;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalTime;
import java.util.List;

public class SystemServiceProxy implements ServiceInterface {

    private ServiceInterface real;

    public void setReal(ServiceInterface real){
        this.real = real;
        //real.init();
    }

    @Override
    public void init() {
        if (real == null)
            throw new NotImplementedException();
        real.init();
    }

    @Override
    public newResult<Double> getCartPrice(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getCartPrice(userName);
    }

    @Override
    public ResultNum addPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, OperatorComponent operatorComponent) {
        if (real == null)
            throw new NotImplementedException();
        return real.addPolicy(userName, store, policyOn, description, policyType, operatorComponent);
    }
    @Override
    public ResultNum addDiscount(String userName, String store, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        if (real == null)
            throw new NotImplementedException();
        return real.addDiscount(userName, store, discountOn, discountPercentage, description, discountType);
    }

    @Override
    public newResult<Double> getProdPrice(String store, String prod) {
        if (real == null)
            throw new NotImplementedException();
        return real.getProdPrice(store,prod);
    }

    @Override
    public newResult<Integer> getProdAmount(String store, String prod) {
        if (real == null)
            throw new NotImplementedException();
        return real.getProdAmount(store, prod);
    }

    @Override
    public newResult<List<Notification>> getMessages(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getMessages(userName);
    }

    @Override
    public newResult<Boolean> isAdmin(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.isAdmin(userName);
    }


    @Override
    public newResult<List<String>> getOnlineMembers(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getOnlineMembers(userName);
    }

    @Override
    public newResult<List<String>> getOfflineMembers(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getOfflineMembers(userName);
    }

    @Override
    public ResultBool supplyServiceExists() {
        if (real == null)
            throw new NotImplementedException();
        return real.supplyServiceExists();
    }

    @Override
    public ResultBool paymentServiceExists() {
        if (real == null)
            throw new NotImplementedException();
        return real.paymentServiceExists();
    }

    @Override
    public ResultBool hasAdmin() {
        if (real == null)
            throw new NotImplementedException();
        return real.hasAdmin();
    }

    @Override
    public newResult<List<String>> getAllStores(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getAllStores(userName);
    }

    @Override
    public ResultBool removeMember(String userName, String memberToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeMember(userName, memberToRemove);
    }

    @Override
    public ResultMsg enterSystem() {
        if (real == null)
            throw new NotImplementedException();
        return real.enterSystem();
    }

    @Override
    public ResultBool addSystemManager(String userName, String managerToAdd) {
        if (real == null)
            throw new NotImplementedException();
        return real.addSystemManager(userName, managerToAdd);
    }

    @Override
    public ResultBool removeSystemManager(String userName, String managerToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeSystemManager(userName, managerToRemove);
    }

    @Override
    public ResultBool addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.addExternalPaymentService(paymentAdaptee);
    }

    @Override
    public ResultBool changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeExternalPaymentService(userName, paymentAdaptee);
    }

    @Override
    public ResultBool editExternalPaymentService() {
        if (real == null)
            throw new NotImplementedException();
        return real.editExternalPaymentService();
    }

    @Override
    public ResultBool addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.addExternalSupplyService(supplyAdaptee);
    }

    @Override
    public ResultBool changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeExternalSupplyService(userName, supplyAdaptee);
    }

    @Override
    public ResultBool editExternalSupplyService() {
        if (real == null)
            throw new NotImplementedException();
        return real.editExternalSupplyService();
    }

    @Override
    public ResultBool exitSystem(String name) {
        if (real == null)
            throw new NotImplementedException();
        return real.exitSystem(name);
    }

    @Override
    public ResultBool signUp(String userName, String newUserName, String password, String name, String lastName) {
        if (real == null)
            throw new NotImplementedException();
        return real.signUp(userName, newUserName, password,name,lastName);
    }

    @Override
    public ResultBool login(String userName, String memberUserName, String password) {
        if (real == null)
            throw new NotImplementedException();
        return real.login(userName, memberUserName, password);
    }

    @Override
    public newResult<List<String>> getStoreInfo(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoreInfo(userName, storeName);
    }

    @Override
    public newResult<List<String>> searchByKeyword(String userName, String keyword) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByKeyword(userName, keyword);
    }

    @Override
    public newResult<List<String>> searchByCategory(String userName, String category) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByCategory(userName, category);
    }

    @Override
    public newResult<List<String>> searchByName(String userName, String productName) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByName(userName, productName);
    }

    @Override
    public ResultMsg addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        if (real == null)
            throw new NotImplementedException();
        return real.addProductToShoppingCart(userName, productName, storeName, quantity);
    }

    @Override
    public newResult<List<String>> displayShoppingCart(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.displayShoppingCart(userName);
    }

    @Override
    public ResultMsg removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeProductFromShoppingCart(userName, storeName, quantity, prodName);
    }

    @Override
    public ResultBool purchase(String userName, int card, LocalTime expDate, int cvv, String city, String street, int stNum, int apartmentNum) {
        if (real == null)
            throw new NotImplementedException();
        return real.purchase(userName, card, expDate, cvv, city, street, stNum, apartmentNum);
    }

    @Override
    public ResultMsg logOut(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.logOut(userName);
    }

    @Override
    public ResultBool openStore(String founderName, String storeName, int card) {
        if (real == null)
            throw new NotImplementedException();
        return real.openStore(founderName, storeName, card);
    }

    @Override
    public ResultBool addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        if (real == null)
            throw new NotImplementedException();
        return real.addProductToStore(userName, storeName, productName, amount, price, category);
    }

    @Override
    public ResultBool removeProductFromStore(String userName, String storeName, String productName) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeProductFromStore(userName, storeName, productName);
    }

    @Override
    public ResultBool editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductName(userName, storeName, oldProductName, newProductName);
    }

    @Override
    public ResultBool editProductPrice(String userName, String storeName, String ProductName, double newPrice) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductPrice(userName, storeName, ProductName, newPrice);
    }

    @Override
    public ResultBool editProductQuantity(String userName, String storeName, String ProductName, int newQuantity) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductQuantity(userName, storeName, ProductName, newQuantity);
    }

    @Override
    public ResultBool changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption) {
        if (real == null)
            throw new NotImplementedException();
        return real.changePurchaseOption(userName, storeName, ProductName, newOption);
    }

    @Override
    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner) {
        if (real == null)
            throw new NotImplementedException();
        return real.appointStoreOwner(userName, storeName, newOwner);
    }

    @Override
    public newResult<Boolean> removeStoreOwner(String userName, String storeName, String ownerToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeStoreOwner(userName, storeName, ownerToRemove);
    }

    @Override
    public ResultBool appointStoreManager(String userName, String storeName, String newManager) {
        if (real == null)
            throw new NotImplementedException();
        return real.appointStoreManager(userName, storeName, newManager);
    }

    @Override
    public newResult<Boolean> removeStoreManager(String userName, String storeName, String managerToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeStoreManager(userName, storeName, managerToRemove);
    }

    @Override
    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
    }

    @Override
    public ResultBool closeStore(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.closeStore(userName, storeName);
    }

    @Override
    public ResultMsg getStoresManagement(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresManagement(userName, storeName);
    }

    @Override
    public ResultMsg getStoresPurchaseHistory(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresPurchaseHistory(userName, storeName);
    }

    @Override
    public ResultBool adminCloseStorePermanently(String adminName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.adminCloseStorePermanently(adminName, storeName);
    }

    @Override
    public newResult<List<String>> getStoresOfUser(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresOfUser(userName);
    }

    @Override
    public ResultMsg adminGetStoresPurchaseHistory(String adminName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.adminGetStoresPurchaseHistory(adminName, storeName);
    }

    @Override
    public ResultBool addKeyword(String userName, String productName, String storeName, String keyWord) {
        if (real == null)
            throw new NotImplementedException();
        return real.addKeyword(userName, productName, storeName, keyWord);
    }

    @Override
    public ResultNum getProductAmount(String storeName, String prodName) {
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