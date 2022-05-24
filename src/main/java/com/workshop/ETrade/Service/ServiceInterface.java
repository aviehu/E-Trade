package com.workshop.ETrade.Service;

import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ResultPackge.ResultNum;
import com.workshop.ETrade.Service.ResultPackge.newResult;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public interface ServiceInterface {
    public void init();
    public newResult<Double> getCartPrice(String userName);
    public ResultNum addPolicy(String userName,String store, String policyOn, String description, PolicyType policyType, OperatorComponent operatorComponent);
    public newResult<List<String>> getOnlineMembers(String userName);
    public newResult<List<String>> getOfflineMembers(String userName);
    public ResultBool supplyServiceExists();

    public ResultBool paymentServiceExists();

    public ResultBool hasAdmin();

    public newResult<List<String>> getAllStores(String userName);

    public ResultBool removeMember(String userName, String memberToRemove);

    public ResultMsg enterSystem();

    public ResultBool addSystemManager(String userName, String managerToAdd);

    public ResultBool removeSystemManager(String userName, String managerToRemove);

    public ResultBool addExternalPaymentService(PaymentAdaptee paymentAdaptee);

    public ResultBool changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee);

    public ResultBool editExternalPaymentService();

    public ResultBool addExternalSupplyService(SupplyAdaptee supplyAdaptee);

    public ResultBool changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee);

    public ResultBool editExternalSupplyService();

    public ResultBool exitSystem(String name);

//    public ResultBool exitSystemAsGuest(String name);

    public ResultBool signUp(String userName, String newUserName, String password,String name,String lastName);

    public ResultBool login(String userName, String memberUserName, String password);

    public newResult<List<String>> getStoreInfo(String userName, String storeName);

    public ResultMsg searchByKeyword(String userName, String keyword);

    public ResultMsg searchByCategory(String userName, String category);

    public ResultMsg searchByName(String userName, String productName);

    public ResultMsg addProductToShoppingCart(String userName, String productName, String storeName, int quantity);

    public newResult<List<String>> displayShoppingCart(String userName);

//    public ResultMsg addProductToShoppingCart(String userName, Store s, int quantity, String prodName);

    public ResultMsg removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName);

    public ResultBool purchase(String userName, int card, LocalTime expDate, int cvv, String city, String street, int stNum, int apartmentNum);

    public ResultMsg logOut(String userName);

    public ResultBool openStore(String founderName, String storeName, int card);

    public ResultBool addProductToStore(String userName, String storeName, String productName, int amount, double price, String category);

    public ResultBool removeProductFromStore(String userName, String storeName, String productName);

    public ResultBool editProductName(String userName, String storeName, String oldProductName, String newProductName);

    public ResultBool editProductPrice(String userName, String storeName, String ProductName, double newPrice);

    public ResultBool editProductQuantity(String userName, String storeName, String ProductName, int newQuantity);

    public ResultBool changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption);

    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner);

    public newResult<Boolean> removeStoreOwner(String userName, String storeName, String ownerToRemove);

    public ResultBool appointStoreManager(String userName, String storeName, String newManager);

    public newResult<Boolean> removeStoreManager(String userName, String storeName, String managerToRemove);

    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission);

    public ResultBool closeStore(String userName, String storeName);

    public ResultMsg getStoresManagement(String userName, String storeName);

    public ResultMsg getStoresPurchaseHistory(String userName, String storeName);

    public ResultBool adminCloseStorePermanently(String adminName, String storeName);

//    public ResultBool adminTerminateUser(String adminName, String userToTerminate);

    public newResult<List<String>> getStoresOfUser(String userName);

    public ResultMsg adminGetStoresPurchaseHistory(String adminName, String storeName);

    public ResultBool addKeyword(String userName, String productName, String storeName, String keyWord);

    public ResultNum getProductAmount(String storeName, String prodName);

    public String getOnline();
    public ResultNum addDiscount(String userName,String store,String discountOn, int discountPercentage, String description, DiscountType discountType);

    public newResult<Double> getProdPrice(String store,String prod);
    public newResult<Integer> getProdAmount(String store,String prod);
//    public ResultBool exitSystem();
}