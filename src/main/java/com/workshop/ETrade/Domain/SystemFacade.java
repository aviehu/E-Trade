package com.workshop.ETrade.Domain;

import com.workshop.ETrade.Controller.Forms.*;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Service.ResultPackge.Result;

import java.util.List;

public interface SystemFacade {
    public Result<Double> getCartPrice(String userName);
    public Result<List<String>> getOnlineMembers(String userName);
    public Result<List<String>> getOfflineMembers(String userName);
    public Result<Boolean> removeMember(String userName, String memberToRemove);
    public Result<String> enterSystem();
    public Result<Boolean> addSystemManager(String userName, String managerToAdd);
    public Result<Boolean> removeSystemManager(String userName, String managerToRemove);

    public Result<Boolean> addExternalPaymentService(PaymentAdaptee paymentAdaptee);

    public Result<Boolean> changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee);

    public Result<Boolean> editExternalPaymentService();

    public Result<Boolean> addExternalSupplyService(SupplyAdaptee supplyAdaptee);

    public Result<Boolean> changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee);

    public Result<Boolean> editExternalSupplyService();

    public Result<Boolean> exitSystem(String name);

//    public newResult<Boolean> exitSystemAsGuest(String name);

    public Result<Boolean> signUp(String userName, String newUserName, String password, String name, String lastName);

    public Result<Boolean> login(String userName, String memberUserName, String password);

    public Result<List<Product>> getStoreInfo(String userName, String storeName);

    public Result<List<String>> searchByKeyword(String userName, String keyword);

    public Result<List<String>> searchByCategory(String userName, String category);
    public Result<Boolean> isAdmin(String userName);
    public Result<List<String>> searchByName(String userName, String productName);

    public Result<List<Notification>> getMessages(String userName);
    public Result<List<String>> getStoresOfUser(String userName);
    public Result<String> addProductToShoppingCart(String userName, String productName, String storeName, int quantity);

    public Result<List<ProductForm>> displayShoppingCart(String userName);

//    public newResult<String> addProductToShoppingCart(String userName, Store s, int quantity, String prodName);

    public Result<String> removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName);

    public Result<Boolean> purchase(String userName, String creditCard, int month, int year , String holderName, int cvv, int id, String country, String city, String street, int stNum, int apartmentNum, int zip);

    public Result<String> logOut(String userName);

    public Result<Boolean> openStore(String founderName, String storeName, int card);

    public Result<Boolean> addProductToStore(String userName, String storeName, String productName, int amount, double price, String category);

    public Result<Boolean> removeProductFromStore(String userName, String storeName, String productName);

    public Result<Boolean> editProductName(String userName, String storeName, String oldProductName, String newProductName);

    public Result<Boolean> editProductPrice(String userName, String storeName, String ProductName, double newPrice);

    public Result<Boolean> editProductQuantity(String userName, String storeName, String ProductName, int newQuantity);

    public Result<Boolean> changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption);

    public Result<Boolean> appointStoreOwner(String userName, String storeName, String newOwner);

    public Result<Boolean> removeStoreOwner(String userName, String storeName, String ownerToRemove);

    public Result<Boolean> appointStoreManager(String userName, String storeName, String newManager);

    public Result<Boolean> removeStoreManager(String userName, String storeName, String managerToRemove);

    public Result<Boolean> changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission);

    public Result<Boolean> closeStore(String userName, String storeName);

    public Result<String> getStoresManagement(String userName, String storeName);

    public Result<String> getStoresPurchaseHistory(String userName, String storeName);

    public Result<Boolean> adminCloseStorePermanently(String adminName, String storeName);

//    public newResult<Boolean> adminTerminateUser(String adminName, String userToTerminate);

    public Result<String> adminGetStoresPurchaseHistory(String adminName, String storeName);

    public Result<Boolean> addKeyword(String userName, String productName, String storeName, String keyWord);
    public Result<Integer> addPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, List<PredicateForm> predicateForms, String connectionType);
    public Result<Integer> addDiscount(String userName, String store, String discountOn, int discountPercentage, String description, DiscountType discountType);

    Result<Integer> addPreDiscount(String userName, String storeName, String discountOn, int discountPercentage, String description, DiscountType discountType, List<PredicateForm> predicateForms, String connectionType);

    Result<Boolean> addBid(String userName, String storeName, String productName, double bidAmount, CreditCardForm creditCard, SupplyAddressForm supplyAddress);

    Result<List<BidForm>> getStoreBids(String userName, String storeName);

    Result<Boolean> reviewBid(String userName, String storeName, int bidId, boolean approve);

    Result<Boolean> counterBid(String userName, String storeName, int bidId, double newOffer);

    Result<List<BidForm>> userBids(String userName);

    Result<Boolean> counterBidReview(String userName, String storeName, int bidId, boolean approve);

//    public newResult<Boolean> exitSystem();

}
