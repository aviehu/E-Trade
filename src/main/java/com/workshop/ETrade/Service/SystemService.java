package com.workshop.ETrade.Service;

import com.workshop.ETrade.Domain.Facade;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ResultPackge.ResultNum;
import com.workshop.ETrade.Service.ResultPackge.newResult;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Service
public class SystemService implements ServiceInterface {
    private Facade facade;


    public SystemService() {
        init();
    }

    public void init(){
        this.facade = new Facade();
    }

    @Override
    public newResult<Double> getCartPrice(String userName) {
        return facade.getCartPrice(userName);
    }

    @Override
    public ResultNum addPolicy(String userName,String store, String policyOn, String description, PolicyType policyType, OperatorComponent operatorComponent) {
        return facade.addPolicy(userName,store,policyOn,description,policyType,operatorComponent);
    }

    @Override
    public ResultMsg getOnlineMembers(String userName) {
        return facade.getOnlineMembers(userName);
    }

    @Override
    public ResultMsg getOfflineMembers(String userName) {
        return facade.getOfflineMembers(userName);
    }

    public ResultBool supplyServiceExists(){
        return facade.supplyServiceExists();
    }

    public ResultBool paymentServiceExists(){
        return facade.paymentServiceExists();
    }

    public ResultBool hasAdmin(){
        return facade.hasAdmin();
    }

    @Override
    public newResult<List<String>> getAllStores(String userName) {
        return facade.getAllStores(userName);
    }

    @Override
    public ResultBool removeMember(String userName, String memberToRemove) {
        return facade.removeMember(userName, memberToRemove);
    }

    @Override
    public ResultMsg enterSystem() {
        return facade.enterSystem();
    }

    @Override
    public ResultBool addSystemManager(String userName, String managerToAdd) {
        return facade.addSystemManager(userName, managerToAdd);
    }

    @Override
    public ResultBool removeSystemManager(String userName, String managerToRemove) {
        return facade.removeSystemManager(userName, managerToRemove);
    }

    @Override
    public ResultBool addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        return facade.addExternalPaymentService(paymentAdaptee);
    }

    @Override
    public ResultBool changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee) {
        return facade.changeExternalPaymentService(userName, paymentAdaptee);
    }

    @Override
    public ResultBool editExternalPaymentService() {
        return facade.editExternalPaymentService();
    }

    @Override
    public ResultBool addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        return facade.addExternalSupplyService(supplyAdaptee);
    }

    @Override
    public ResultBool changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee) {
        return facade.changeExternalSupplyService(userName, supplyAdaptee);
    }

    @Override
    public ResultBool editExternalSupplyService() {
        return facade.editExternalSupplyService();
    }

    @Override
    public ResultBool exitSystem(String userName) {
        return facade.exitSystem(userName);
    }

    @Override
    public ResultBool signUp(String userName, String newUserName, String password, String name, String lastName) {
        return facade.signUp(userName, newUserName, password, name, lastName);
    }

    @Override
    public ResultBool login(String userName, String memberUserName, String password) {
        return facade.login(userName, memberUserName, password);
    }

    @Override
    public newResult<List<String>> getStoreInfo(String userName, String storeName) {
        return facade.getStoreInfo(userName, storeName);
    }

    @Override
    public ResultMsg searchByKeyword(String userName, String keyword) {
        return facade.searchByKeyword(userName, keyword);
    }

    @Override
    public ResultMsg searchByCategory(String userName, String category) {
        return facade.searchByCategory(userName, category);
    }

    @Override
    public ResultMsg searchByName(String userName, String productName) {
        return facade.searchByName(userName, productName);
    }

    @Override
    public ResultMsg addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        return facade.addProductToShoppingCart(userName, productName, storeName, quantity);
    }

    @Override
    public newResult<List<String>> displayShoppingCart(String userName) {
        return facade.displayShoppingCart(userName);
    }

    @Override
    public ResultMsg removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName) {
        return facade.removeProductFromShoppingCart(userName, storeName, quantity, prodName);
    }

    @Override
    public ResultBool purchase(String userName, int card, LocalTime expDate, int cvv, String city, String street, int stNum, int apartmentNum) {
        return facade.purchase(userName, card, expDate, cvv, city, street, stNum, apartmentNum);
    }

    @Override
    public ResultMsg logOut(String userName) {
        return facade.logOut(userName);
    }

    @Override
    public ResultBool openStore(String founderName, String storeName, int card) {
        return facade.openStore(founderName, storeName, card);
    }

    @Override
    public ResultBool addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        return facade.addProductToStore(userName, storeName, productName, amount, price, category);
    }

    @Override
    public ResultBool removeProductFromStore(String userName, String storeName, String productName) {
        return facade.removeProductFromStore(userName, storeName, productName);
    }

    @Override
    public ResultBool editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        return facade.editProductName(userName, storeName, oldProductName, newProductName);
    }

    @Override
    public ResultBool editProductPrice(String userName, String storeName, String ProductName, double newPrice) {
        return facade.editProductPrice(userName, storeName, ProductName, newPrice);
    }

    @Override
    public ResultBool editProductQuantity(String userName, String storeName, String ProductName, int newQuantity) {
        return facade.editProductQuantity(userName, storeName, ProductName, newQuantity);
    }

    @Override
    public ResultBool changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption) {
        return facade.changePurchaseOption(userName, storeName, ProductName, newOption);
    }

    @Override
    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner) {
        return facade.appointStoreOwner(userName, storeName, newOwner);
    }

    @Override
    public ResultBool appointStoreManager(String userName, String storeName, String newManager) {
        return facade.appointStoreManager(userName, storeName, newManager);
    }

    @Override
    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        return facade.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
    }

    @Override
    public ResultBool closeStore(String userName, String storeName) {
        return facade.closeStore(userName, storeName);
    }

    @Override
    public ResultMsg getStoresManagement(String userName, String storeName) {
        return facade.getStoresManagement(userName, storeName);
    }

    @Override
    public ResultMsg getStoresPurchaseHistory(String userName, String storeName) {
        return facade.getStoresPurchaseHistory(userName, storeName);
    }

    @Override
    public ResultBool adminCloseStorePermanently(String adminName, String storeName) {
        return facade.adminCloseStorePermanently(adminName, storeName);
    }

    @Override
    public newResult<List<String>> getStoresOfUser(String userName) {
        return facade.getStoresOfUser(userName);
    }

//    @Override
//    public ResultBool adminTerminateUser(String adminName, String userToTerminate) {
//        return facade.adminTerminateUser(adminName, userToTerminate);
//    }

    @Override
    public ResultMsg adminGetStoresPurchaseHistory(String adminName, String storeName) {
        return facade.adminGetStoresPurchaseHistory(adminName, storeName);
    }

    public String getOnline() {
        return facade.getOnline();
    }

    @Override
    public ResultNum addDiscount(String userName,String store, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        return facade.addDiscount(userName,store, discountOn, discountPercentage, description, discountType);
    }

    public ResultNum getProductAmount(String storeName, String prodName){
        return facade.getProductAmount(storeName,prodName);
    }

    public ResultBool addKeyword(String userName, String productName, String storeName, String keyWord) {
        return facade.addKeyword(userName, productName, storeName, keyWord);
    }

}
