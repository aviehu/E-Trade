package com.workshop.ETrade.Domain;

import com.workshop.ETrade.Controller.Forms.*;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Notifications.NotificationThread;
import com.workshop.ETrade.Domain.Stores.*;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Users.*;
import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Service.ResultPackge.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Facade implements SystemFacade {

    public StoresFacade storesFacade;
    private UserController userController;

    private ExtSysController externalSys;
    private String myUserName;

    public Facade() {
        storesFacade = new StoresFacade();
        userController = new UserController();
        //need to change according to config file.
        externalSys = ExtSysController.getInstance(true,true);
        //
    }
    public void init(){
        storesFacade.init();
    }

    @Override
    public Result<Double> getCartPrice(String userName) {
        Double p = this.userController.getCartPrice(userName);
        if(p == -1)
            return new Result<>(null,"no such user\n");
        return new Result<>(p,null);
    }

    public Result<List<String>> getOnlineMembers(String userName){
        List<String> ret =  this.userController.getOnlineMembers(userName);
        if(ret == null){
            return new Result<>(null,"PERMISSION DENIED\n");
        }
//        if (ret.size() == 0){
//            return new newResult<>(null, "There are no connected members in the market\n");
//        }
        return new Result<>(ret,null);

    }
    public Result<List<String>> getOfflineMembers(String userName){
        List<String> ret = this.userController.getOfflineMembers(userName);
        if(ret == null){
            return  new Result<>(null,"PERMISSION DENIED\n");
        }
//        if (ret.equals("")){
//            return new newResult<String>("There are no members in the market\n",null);
//        }
        return new Result<>(ret,null);

    }
    @Override
    public Result<Boolean> removeMember(String userName, String memberToRemove) {
        if(userController.isConnected(userName)){
            if(!userController.isUserSysManager(userName)){
                return new Result<Boolean>(false,"PERMISSION DENIED\n");
            }
            if(isInManagment(userName,memberToRemove)){
                return new Result<Boolean>(false,"Can't remove " + memberToRemove+ ", "+ memberToRemove+" is in Management\n");
            }
            String ret = userController.removeMember(userName, memberToRemove);
            if(ret == null)
                return new Result<Boolean>(true,null);
            return new Result<Boolean>(false,ret);

        }
        return new Result<Boolean>(false, "User is not connected");
    }
    public boolean isInManagment(String admin,String memberToRemove){
        for(Store s : this.storesFacade.getStores()){
            if(this.storesFacade.getStoresManagement(admin,s.getName()).contains(memberToRemove))
                return true;
        }
        return false;
    }

    @Override
    public Result<String> enterSystem() {
        String userName = userController.enterSystem();
        if(userName != null) {
            this.myUserName = userName;
            return new Result<String>(userName, null);
        }
        return new Result<String>(null,"Can't enter System\n");
    }

    @Override
    public Result<Boolean> addSystemManager(String userName, String managerToAdd) {
        if(userController.isConnected(userName)){
            String ret = userController.addSystemManager(userName,managerToAdd);
            if(ret == null)
                return new Result<Boolean>(true,null);
            return new Result<Boolean>(false,ret);
        }
        return new Result<Boolean>(false, "User is not connected\n");
    }

    @Override
    public Result<Boolean> removeSystemManager(String userName, String managerToRemove) {
        if(userController.isConnected(userName)){
            if(userController.removeSystemManager(userName,managerToRemove))
                return new Result<Boolean>(true,null);
            return new Result<Boolean>(false,"cant remove "+managerToRemove+"\n");
        }
        return new Result<Boolean>(false, "User is not connected");
    }

    @Override
    public Result<Boolean> addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        return new Result<Boolean>(true, null);
    }


    @Override
    public Result<Boolean> changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee) {
        if(userController.isConnected(userName)) {
            if(userController.isUserSysManager(userName)) {
                if (this.externalSys.changePayment(paymentAdaptee))
                    return new Result<Boolean>(true, null);
                return new Result<Boolean>(false, "Failed to change payment service\n");
            }
            return new Result<Boolean>(false,"PERMISSION DENIED\n");
        }
        return new Result<Boolean>(false, "User is not connected\n");
    }

    @Override
    public Result<Boolean> editExternalPaymentService() {
        return new Result<Boolean>(true, null);
    }

    @Override
    public Result<Boolean> addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        return new Result<Boolean>(true, null);
    }

    @Override
    public Result<Boolean> changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee) {
        if(userController.isConnected(userName)) {
            if(userController.isUserSysManager(userName)) {
                if (this.externalSys.changeSupply(supplyAdaptee))
                    return new Result<Boolean>(true, null);
                return new Result<Boolean>(false,"Failed to change supply Service");
            }
            return new Result<Boolean>(false,"PERMISSION DENIED\n");
        }
        return new Result<Boolean>(false, "User is not connected\n");
    }

    @Override
    public Result<Boolean> editExternalSupplyService() {
        return new Result<Boolean>(true, null);
    }

    @Override
    public Result<Boolean> exitSystem(String name) {
        if(userController.isConnected(name)) {
            if (this.userController.exitSystem(name))
                return new Result<Boolean>(true, null);
            return new Result<Boolean>(false, "user is not in the system\n");
        }
        return new Result<Boolean>(false, "User is not connected\n");
    }

//    @Override
//    public newResult<Boolean> exitSystemAsGuest(String name) {
//        return new newResult<Boolean>(true, null);
//    }

    @Override
    public Result<Boolean> signUp(String userName, String newUserName, String password, String name, String lastName) {
        if(userController.isConnected(userName)) {
            if (!this.userController.isValidPassword(password))
                return new Result<Boolean>(false, "Invalid password\n password must be at least 8 characters and contain uppercase character\n");
            if (this.userController.isUserNameExist(newUserName))
                return new Result<Boolean>(false, "User name not available\n");
            String pass = this.externalSys.encode(password);
            userController.signUp(newUserName, pass,name,lastName);
            return new Result<Boolean>(true, null);
        }
        return new Result<Boolean>(false, "User is not connected\n");
    }

    @Override
    public Result<Boolean> login(String userName, String memberUserName, String password) {
        if(userController.isConnected(userName)) {
            if (!userController.isUserNameExist(memberUserName))
                return new Result<Boolean>(false, "Invalid Username or Password \n");
            String pass = this.externalSys.encode(password); //SECURITY
            String ret = userController.logIn(memberUserName, pass);
            if (ret == null){
                this.myUserName = memberUserName;
                return new Result<Boolean>(true, null);
            }
            else
                return new Result<Boolean>(false,ret);
        }
        return new Result<Boolean>(false, "User is not connected\n");
    }

    @Override
    public Result<List<Product>> getStoreInfo(String userName, String storeName) {
        if(userController.isConnected(userName)){
            List<Product> ans = storesFacade.displayStore(storeName);
            if(ans != null) {
                return new Result<>(ans, null);
            }
            return new Result<>(null, "No Such Store" + storeName);
        }
        return new Result<>(null, "User Is Not Connected");
    }

    @Override
    public Result<List<String>> searchByKeyword(String userName, String keyword) {
        if(userController.isConnected(userName)){
            List<String> ans = storesFacade.searchByKeyword(keyword);
            if(ans != null) {
                return new Result<>(ans, null);
            }
            return new Result<>(null, "No Items Matched Your Search");
        }
        return new Result<>(null, "User Is Not Connected");
    }

    @Override
    public Result<List<String>> searchByCategory(String userName, String category) {
        if(userController.isConnected(userName)){
            List<String> ans = storesFacade.searchByCategory(category);
            if(ans != null) {
                return new Result<>(ans, null);
            }
            return new Result<>(null, "No Items Matched Your Search");
        }
        return new Result<>(null, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> isAdmin(String userName) {
        return new Result<>(userController.isUserSysManager(userName), null);
    }

    @Override
    public Result<List<String>> searchByName(String userName, String productName) {
        if(userController.isConnected(userName)){
            List<String> ans = storesFacade.searchByName(productName);
            if(ans != null) {
                return new Result<>(ans, null);
            }
            return new Result<>(null, "No Items Matched Your Search");
        }
        return new Result<>(null, "User Is Not Connected");
    }

    @Override
    public Result<List<Notification>> getMessages(String userName) {
        if(userController.isConnected(userName)){
            List<Notification> ans = userController.getMessages(userName);
            if(ans != null) {
                return new Result<>(ans, null);
            }
            return new Result<>(null, "No Items Matched Your Search");
        }
        return new Result<>(null, "User Is Not Connected");
    }

    @Override
    public Result<String> addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        if(userController.isConnected(userName)){
            Store s = null;
            String result;
            if((s = storesFacade.getStore(storeName)) == null)
                return new Result<>(null, "No such store");
            else {
                result = userController.addProductToShoppingCart(userName, productName, s, quantity);

                if (result == null)
                    return new Result<>(null, "Could not add product from your shopping cart\n");
                else
                    return new Result<>(result, null);
            }
        }
        return new Result<>(null, "User is not connected");
    }

    @Override
    public Result<List<ProductForm>> displayShoppingCart(String userName) {
        List<ProductForm> ans = new LinkedList<>();
        if(userController.isConnected(userName)) {
            HashMap<String,Pair<Integer, String>> prods = userController.displayShoppingCart(userName);
            for(String prodName : prods.keySet()) {
                ans.add(new ProductForm(prodName, prods.get(prodName).first, prods.get(prodName).second));
            }
            return new Result<>(ans,null);
        }
        return new Result<>(null,"User is not connected");
    }

//    @Override
//    public newResult<String> addProductToShoppingCart(String userName, Store s, int quantity, String prodName) {
//        return new newResult<String>("", null);
//    }

    @Override
    public Result<String> removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName) {
        if(userController.isConnected(userName)){
            Store s = null;
            String result;
            if((s = storesFacade.getStore(storeName)) == null)
                return new Result<>(null, "No such store");
            else {
                result = userController.removeProductFromShoppingCart(userName,s,quantity,prodName);
                if (result == null)
                    return new Result<>(null, "Could not remove product from your shopping cart\n");
                else
                    return new Result<>(result, null);
            }
        }
        return new Result<>(null, "User is not connected");
    }

    @Override
    public Result<Boolean> purchase(String userName, String creditCard, int month, int year, String holderName, int cvv, int id, String country, String city, String street, int stNum, int apartmentNum, int zip) {
        if(userController.isConnected(userName)) {
            boolean success = true;
            String faile = "";
            List<String> ret = userController.purchase(userName, creditCard, month, year,holderName, cvv, id, country, city, street, stNum, apartmentNum, zip);
            if(ret.size() == 1){
                faile = ret.remove(0);
                if(faile.split("!")[0].equals("Faile"));
                    success = false;
            }
            if (success) {
                for(String store : ret){
                    Store s = storesFacade.getStore(store);
                    notifySubscribers(s.getSubscribers(),"Theres been a purchase in " + s.getName()+"\n",userName);
                }

                return new Result<>(true, null);
            }
            else
                return new Result<>(null,faile);
        }
        return new Result<>(false, "User is not connected");
    }

    @Override
    public Result<String> logOut(String userName) {
        if (userController.isConnected(userName)){
            String us = userController.logOut(userName);
            if(us != null){
                this.myUserName = us;
                return new Result<String>(us,null);
            }

            return new Result<String>(null,"Logout failed");
        }
        return new Result<String>(null, "User is not connected");
    }

    @Override
    public Result<Boolean> openStore(String founderName, String storeName, int card) {
        if(userController.isConnected(founderName)){
            if(storesFacade.addStore(storeName, founderName, card)) {
                //subscribe new founder
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(founderName);
                s.attach(m);
                return new Result<>(true, null);
            }
            return new Result<>(false, "Store With A Name - " + storeName + " exists");
        }
        return new Result<>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        if(userController.isConnected(userName)){
            if(storesFacade.addProductToStore(userName, storeName, productName, amount, price, category)) {
                return new Result<>(true, null);
            }
            return new Result<>(false, "Could Not Add Product To Store");
        }
        return new Result<>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> removeProductFromStore(String userName, String storeName, String productName) {
        if(userController.isConnected(userName)){
            if(storesFacade.removeProductFromStore(userName, storeName, productName)) {
                return new Result<>(true, null);
            }
            return new Result<>(false, "Could Not Remove Product From Store");
        }
        return new Result<>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        if(userController.isConnected(userName)){
            if(storesFacade.editProductName(userName, storeName, oldProductName, newProductName)) {
                return new Result<Boolean>(true, null);
            }
            return new Result<Boolean>(false, "No Such Product Or Store");
        }
        return new Result<Boolean>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> editProductPrice(String userName, String storeName, String productName, double newPrice) {
        if(userController.isConnected(userName)){
            if(storesFacade.editProductPrice(userName, storeName, productName, newPrice)) {
                return new Result<Boolean>(true, null);
            }
            return new Result<Boolean>(false, "Could Not Edit Product Price");
        }
        return new Result<Boolean>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> editProductQuantity(String userName, String storeName, String productName, int newQuantity) {
        if(userController.isConnected(userName)){
            if(storesFacade.editProductQuantity(userName, storeName, productName, newQuantity)) {
                return new Result<Boolean>(true, null);
            }
            return new Result<Boolean>(false, "Could Not Edit Product Quantity");
        }
        return new Result<Boolean>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> changePurchaseOption(String userName, String storeName, String productName, purchaseOption newOption) {
        if(userController.isConnected(userName) && userController.isUserNameExist(userName)){
            if(storesFacade.changePurchaseOption(userName, storeName, productName, newOption)) {
                return new Result<>(true, null);
            }
            return new Result<>(null, "Could Not Change Product Purchase Option");
        }
        return new Result<>(null, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> appointStoreOwner(String userName, String storeName, String newOwner) {
        if(userController.isConnected(userName) && userController.isUserNameExist(newOwner)){
            if(storesFacade.appointStoreOwner(userName, storeName, newOwner)) {
                //subscribe new owner
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(newOwner);
                s.attach(m);
                notifyOne("You have been appointed to store Owner at " +storeName,userName,newOwner);

                return new Result<>(true, null);
            }
            return new Result<>(false, "Could Not Appoint Store Owner");
        }
        return new Result<>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> removeStoreOwner(String userName, String storeName, String ownerToRemove) {
        if(userController.isConnected(userName) && userController.isUserNameExist(ownerToRemove)){
            if(storesFacade.removeStoreOwner(userName, storeName, ownerToRemove)) {
                //subscribe new owner
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(ownerToRemove);
                notifyOne("You are no longer Owner at " + s.getName(),userName,ownerToRemove);
                s.detach(m);
                return new Result<>(true, null);
            }
            return new Result<>(false, "Could Not Remove Store Owner");
        }
        return new Result<>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> appointStoreManager(String userName, String storeName, String newManager) {
        if(userController.isConnected(userName)){
            if(storesFacade.appointStoreManager(userName, storeName, newManager)) {
                //subscribe new manager
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(newManager);
                s.attach(m);
                notifyOne("You have been appointed to store Manager at " +storeName,userName,newManager);
                return new Result<>(true, null);
            }
            return new Result<>(false, "Could Not Appoint Store Manager");
        }
        return new Result<>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> removeStoreManager(String userName, String storeName, String managerToRemove) {
        if(userController.isConnected(userName) && userController.isUserNameExist(managerToRemove)){
            if(storesFacade.removeStoreManager(userName, storeName, managerToRemove)) {
                //subscribe new owner
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(managerToRemove);
                s.detach(m);
                notifyOne("You are no longer Manager at " + storeName,userName,managerToRemove);
                return new Result<>(true, null);
            }
            return new Result<>(false, "Could Not Appoint Store Owner");
        }
        return new Result<>(false, "User Is Not Connected");
    }

    @Override
    public Result<Boolean> changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        if(storesFacade.changeStoreManagersPermission(userName, storeName, managerName, newPermission) && userController.isConnected(userName)){
            return new Result<>(true, null);
        }
        return new Result<>(false , "Cannot Close Store");
    }

    @Override
    public Result<Boolean> closeStore(String userName, String storeName) {
        if(storesFacade.closeStore(storeName, userName) && userController.isConnected(userName)){
            return new Result<Boolean>(true, null);
        }
        return new Result<Boolean>(false , "Cannot Close Store");
    }

    @Override
    public Result<String> getStoresManagement(String userName, String storeName) {
        if(userController.isConnected(userName)) {
            String result = storesFacade.getStoresManagement(userName, storeName).toString();
            if(result != null) {
                return new Result<String>(result, null);
            }
            return new Result<String>("", "Could Not Get Stores Management");
        }
        return new Result<String>("", "User is Not Connected");
    }

    @Override
    public Result<String> getStoresPurchaseHistory(String userName, String storeName) {
        if(userController.isConnected(userName)) {
            String result = storesFacade.getStorePurchaseHistory(userName, storeName);
            if(result != null) {
                return new Result<String>(result, null);
            }
            return new Result<String>("", "Cannot Get Stores Purchase History");
        }
        return new Result<String>("", "User Is Not Connected");
    }

    @Override
    public Result<Boolean> adminCloseStorePermanently(String adminName, String storeName) {
        if(storesFacade.adminCloseStore(storeName) && userController.isUserSysManager(adminName)){
            return new Result<Boolean>(true, null);
        }
        return new Result<Boolean>(false , "Cannot Close Store Permanently");
    }

//    @Override
//    public newResult<Boolean> adminTerminateUser(String adminName, String userToTerminate) {
//        String ret = userController.removeMember(adminName, userToTerminate);
//        if(ret == null) {
//            return new newResult<Boolean>(true, null);
//        }
//        return new newResult<Boolean>(false, ret);
//    }

    @Override
    public Result<String> adminGetStoresPurchaseHistory(String adminName, String storeName) {
        if(userController.isUserSysManager(adminName)) {
            String result = storesFacade.adminGetStorePurchaseHistory(storeName);
            if(result != null) {
                return new Result<String>(result, null);
            }
            return new Result<String>("", "Could Not Get Stores Purchase History");
        }
        return new Result<String>("", "User is not admin");
    }

    @Override
    public Result<Boolean> addKeyword(String userName, String productName, String storeName, String keyWord) {
        if(userController.isConnected(userName)) {
            if(storesFacade.addKeyword(userName, storeName, productName, keyWord)) {
                return new Result<Boolean>(true, null);
            }
            return new Result<Boolean>(false, "Cannot add keyword");
        }
        return new Result<Boolean>(false, "User Is Not Connected");
    }

    @Override
    public Result<Integer> addPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, List<PredicateForm> predicateForms, String connectionType) {
        int ret = this.storesFacade.addPolicy(userName,store, policyOn, description, policyType, predicateForms, connectionType);
        if(ret >= 0) {
            return new Result<>(ret,null);
        }
        return new Result<>(null, "cannot add policy");
    }

    @Override
    public Result<Integer> addDiscount(String userName, String store, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        int ret = this.storesFacade.addDiscount(userName,store, discountOn, discountPercentage, description, discountType);
        if(ret == -1)
            return new Result<>(null,"ERROR\n");
        return new Result<>(ret,null);
    }

    @Override
    public Result<Integer> addPreDiscount(String userName, String storeName, String discountOn, int discountPercentage, String description, DiscountType discountType, List<PredicateForm> predicateForms, String connectionType) {
        int ret = this.storesFacade.addPreDiscount(userName,storeName, discountOn, discountPercentage, description, discountType, predicateForms, connectionType);
        if(ret == -1)
            return new Result<>(null,"ERROR\n");
        return new Result<>(ret,null);
    }

    @Override
    public Result<Boolean> addBid(String userName, String storeName, String productName, double bidAmount, CreditCardForm creditCard, SupplyAddressForm supplyAddress) {
        boolean added = storesFacade.addBid(userName, storeName, productName, bidAmount,
                new CreditCard(creditCard.cardNumber, creditCard.month, creditCard.year, creditCard.cvv, creditCard.id, creditCard.holderName),
                new SupplyAddress(supplyAddress.country, supplyAddress.city, supplyAddress.street, supplyAddress.streetNum, supplyAddress.apartmentNum, supplyAddress.zip));
        if(added) {
            return new Result<>(true, null);
        }
        return new Result<>(null, "Cannot add bid");
    }

    @Override
    public Result<List<BidForm>> getStoreBids(String userName, String storeName) {
        List<Bid> bids = storesFacade.getStoreBids(storeName);
        if(bids != null && userController.isConnected(userName)) {
            List<BidForm> bidForms = new LinkedList<>();
            for(Bid bid : bids) {
                bidForms.add(new BidForm(bid));
            }
            return new Result<>(bidForms, null);
        }
        return new Result<>(null, "Cannot Get Bids");
    }

    @Override
    public Result<Boolean> counterBid(String userName, String storeName, int bidId, double newOffer) {
        if(userController.isConnected(userName)) {
            return new Result<>(storesFacade.counterBid(storeName, bidId, newOffer), null);
        } else {
            return new Result<>(null, "User Is Not Connected");
        }
    }

    @Override
    public Result<List<BidForm>> userBids(String userName) {
        if(userController.isConnected(userName)) {
            List<Bid> bids = storesFacade.userBids(userName);
            List<BidForm> ans = new LinkedList<>();
            for(Bid b : bids) {
                ans.add(new BidForm(b));
            }
            return new Result<>(ans, null);
        } else {
            return new Result<>(null, "User Is Not Connected");
        }
    }

    @Override
    public Result<Boolean> counterBidReview(String userName, String storeName, int bidId, boolean approve) {
        if(userController.isConnected(userName)) {
            Bid bid = storesFacade.counterBidReview(storeName, bidId, approve);
            if(bid != null) {
                userController.purchaseBid(userName, bid);
                storesFacade.purchaseBid(storeName,bid.getProductName(), userController.getUser(bid.getBidderName()));
                return new Result<>(true, null);
            }
            return new Result<>(false, null);
        } else {
            return new Result<>(null, "User Is Not Connected");
        }
    }

    @Override
    public Result<Boolean> reviewBid(String userName, String storeName, int bidId, boolean approve) {
        if(userController.isConnected(userName)) {
            Bid approved = storesFacade.reviewBid(userName, storeName, bidId, approve);
            if(approved != null) {
                userController.purchaseBid(userName, approved);
                storesFacade.purchaseBid(storeName,approved.getProductName(), userController.getUser(approved.getBidderName()));
                notifyUser("Your bid has been approved", storeName, approved.getBidderName());
                notifySubscribers(storesFacade.getStore(storeName).getSubscribers(),"A bid for - " + approved.getProductName() + "  in " + storeName + " has been approved", approved.getBidderName());
                return new Result<>(true, null);
            }
            return new Result<>(false, null);
        }
        return new Result<>(null, "User Is Not Connected");
    }

    public Result<Boolean> supplyServiceExists() {
        if(this.externalSys.isExistSupply())
            return new Result<Boolean>(true, null);
        return new Result<Boolean>(false,"Supply not exist");
    }
    public Result<Boolean> paymentServiceExists(){
        if(this.externalSys.isExistPayment())
            return new Result<Boolean>(true, null);
        return new Result<Boolean>(false,"Payment not exist");

    }
    public Result<Boolean> securityServiceExists(){
        if(this.externalSys.isExistSecurity())
            return new Result<Boolean>(true, null);
        return new Result<Boolean>(false,"security not exist");

    }
    public Result<Boolean> hasAdmin(){
        if(this.userController.hasAdmin())
            return new Result<Boolean>(true, null);
        return new Result<Boolean>(false,"has no admins");

    }

    public String getOnline(){
        return this.myUserName;
    }

    public Result<Integer> getProductAmount(String storeName, String prodName){
        int amount = this.storesFacade.getProductAmount(storeName,prodName);
        return new Result<Integer>(amount,null);
    }


    public Result<List<String>> getAllStores(String userName) {
        if(userController.isConnected(userName)) {
            List<String> res = new LinkedList<>();
            List<Store> stores = storesFacade.getStores();
            for(Store store : stores) {
                res.add(store.getName());
            }
            return new Result<>(res, null);
        }
        return new Result<>(null, "user is not connected");
    }

    public Result<List<String>> getStoresOfUser(String userName) {
        if(userController.isConnected(userName)) {
            List<String> stores = storesFacade.getStoresOfUser(userName);
            return new Result<>(stores, null);
        }
        return new Result<>(null, "user is not connected");
    }
    public Result<Double> getProdPrice(String storeName, String prod){
        Store s = this.storesFacade.getStore(storeName);
        if (s == null){
            return new Result<>(null,"No such store\n");
        }
        double price = s.getProductPrice(prod);
        return  new Result<>(price,null);

    }
    public Result<Integer> getProdAmount(String storeName, String prod){
        int amount = this.storesFacade.getProductAmount(storeName,prod);
        if(amount == -1)
            return new Result<>(null,"no such store\n");
        return  new Result<>(amount,null);

    }


    public void notifySubscribers(List<String> subscribers,String message,String sendFrom) {
        List<Thread> threads = new ArrayList<>();
        for(String user: subscribers) {
            Member member = userController.getMember(user);
            threads.add(new NotificationThread(member, message, sendFrom));
        }
        for(Thread t : threads) {
            t.run();
        }
        for(Thread t : threads) {
            try {
                t.join();
            } catch (Exception e) {
            }
        }

    }
    public void notifyOne(String message,String sendFrom,String sendTo) {
        //
                User user = userController.getUser(sendTo);
                user.update(message, sendFrom);
        }


    public void notifyUser(String message, String sendFrom, String sendTo) {
        User user = userController.getUser(sendTo);
        user.update(message,sendFrom);
    }
}
