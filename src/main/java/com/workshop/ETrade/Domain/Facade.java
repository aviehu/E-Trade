package com.workshop.ETrade.Domain;

import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Predicates.OperatorComponent;
import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Domain.Stores.StoresFacade;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.Users.Users.Member;
import com.workshop.ETrade.Domain.Users.Users.UserController;
import com.workshop.ETrade.Service.ResultPackge.ResultBool;
import com.workshop.ETrade.Service.ResultPackge.ResultMsg;
import com.workshop.ETrade.Service.ResultPackge.ResultNum;
import com.workshop.ETrade.Service.ResultPackge.newResult;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Facade implements SystemFacade {
    private StoresFacade storesFacade;
    private UserController userController;
    private ExtSysController externalSys;
    private String myUserName;

    public Facade() {
        storesFacade = new StoresFacade();
        userController = new UserController();
        externalSys = ExtSysController.getInstance();
    }


    @Override
    public newResult<Double> getCartPrice(String userName) {
        Double p = this.userController.getCartPrice(userName);
        if(p == -1)
            return new newResult<>(null,"no such user\n");
        return new newResult<>(p,null);
    }

    public ResultMsg getOnlineMembers(String userName){
        String ret =  this.userController.getOnlineMembers(userName);
        if(ret == null){
            return  new ResultMsg(null,"PERMISSION DENIED\n");
        }
        if (ret.equals("")){
            return  new ResultMsg("There are no connected members in the market\n",null);
        }
        return  new ResultMsg(ret,null);

    }
    public ResultMsg getOfflineMembers(String userName){
        String ret =  this.userController.getOfflineMembers(userName);
        if(ret == null){
            return  new ResultMsg(null,"PERMISSION DENIED\n");
        }
        if (ret.equals("")){
            return  new ResultMsg("There are no members in the market\n",null);
        }
        return  new ResultMsg(ret,null);

    }
    @Override
    public ResultBool removeMember(String userName, String memberToRemove) {
        if(userController.isConnected(userName)){
            if(!userController.isUserSysManager(userName)){
                return new ResultBool(false,"PERMISSION DENIED\n");
            }
            if(isInManagment(userName,memberToRemove)){
                return new ResultBool(false,"Can't remove " + memberToRemove+ ", "+ memberToRemove+" is in Management\n");
            }
            String ret = userController.removeMember(userName, memberToRemove);
            if(ret == null)
                return new ResultBool(true,null);
            return new ResultBool(false,ret);

        }
        return new ResultBool(false, "User is not connected");
    }
    public boolean isInManagment(String admin,String memberToRemove){
        for(Store s : this.storesFacade.getStores()){
            if(this.storesFacade.getStoresManagement(admin,s.getName()).contains(memberToRemove))
                return true;
        }
        return false;
    }

    @Override
    public ResultMsg enterSystem() {
        String userName = userController.enterSystem();
        if(userName != null) {
            this.myUserName = userName;
            return new ResultMsg(userName, null);
        }
        return new ResultMsg(null,"Can't enter System\n");
    }

    @Override
    public ResultBool addSystemManager(String userName, String managerToAdd) {
        if(userController.isConnected(userName)){
            String ret = userController.addSystemManager(userName,managerToAdd);
            if(ret == null)
                return new ResultBool(true,null);
            return new ResultBool(false,ret);
        }
        return new ResultBool(false, "User is not connected\n");
    }

    @Override
    public ResultBool removeSystemManager(String userName, String managerToRemove) {
        if(userController.isConnected(userName)){
            if(userController.removeSystemManager(userName,managerToRemove))
                return new ResultBool(true,null);
            return new ResultBool(false,"cant remove "+managerToRemove+"\n");
        }
        return new ResultBool(false, "User is not connected");
    }

    @Override
    public ResultBool addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        return new ResultBool(true, null);
    }


    @Override
    public ResultBool changeExternalPaymentService(String userName,PaymentAdaptee paymentAdaptee) {
        if(userController.isConnected(userName)) {
            if(userController.isUserSysManager(userName)) {
                if (this.externalSys.changePayment(paymentAdaptee))
                    return new ResultBool(true, null);
                return new ResultBool(false, "Failed to change payment service\n");
            }
            return new ResultBool(false,"PERMISSION DENIED\n");
        }
        return new ResultBool(false, "User is not connected\n");
    }

    @Override
    public ResultBool editExternalPaymentService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool changeExternalSupplyService(String userName,SupplyAdaptee supplyAdaptee) {
        if(userController.isConnected(userName)) {
            if(userController.isUserSysManager(userName)) {
                if (this.externalSys.changeSupply(supplyAdaptee))
                    return new ResultBool(true, null);
                return new ResultBool(false,"Failed to change supply Service");
            }
            return new ResultBool(false,"PERMISSION DENIED\n");
        }
        return new ResultBool(false, "User is not connected\n");
    }

    @Override
    public ResultBool editExternalSupplyService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool exitSystem(String name) {
        if(userController.isConnected(name)) {
            if (this.userController.exitSystem(name))
                return new ResultBool(true, null);
            return new ResultBool(false, "user is not in the system\n");
        }
        return new ResultBool(false, "User is not connected\n");
    }

//    @Override
//    public ResultBool exitSystemAsGuest(String name) {
//        return new ResultBool(true, null);
//    }

    @Override
    public ResultBool signUp(String userName,String newUserName, String password, String name,String lastName) {
        if(userController.isConnected(userName)) {
            if (!this.userController.isValidPassword(password))
                return new ResultBool(false, "Invalid password\n password must be at least 8 characters and contain uppercase character\n");
            if (this.userController.isUserNameExist(newUserName))
                return new ResultBool(false, "User name not available\n");
            String pass = this.externalSys.encode(password);
            userController.signUp(newUserName, pass,name,lastName);
            return new ResultBool(true, null);
        }
        return new ResultBool(false, "User is not connected\n");
    }

    @Override
    public ResultBool login(String userName,String memberUserName, String password) {
        if(userController.isConnected(userName)) {
            if (!userController.isUserNameExist(memberUserName))
                return new ResultBool(false, "Wrong user name\n");
            String pass = this.externalSys.encode(password); //SECURITY
            String ret = userController.logIn(memberUserName, pass);
            if (ret == null){
                this.myUserName = memberUserName;
                return new ResultBool(true, null);
            }
            else
                return new ResultBool(false,ret);
        }
        return new ResultBool(false, "User is not connected\n");
    }

    @Override
    public newResult<List<String>> getStoreInfo(String userName, String storeName) {
        if(userController.isConnected(userName)){
            List<String> ans = storesFacade.displayStore(storeName);
            if(ans != null) {
                return new newResult<>(ans, null);
            }
            return new newResult<>(null, "No Such Store" + storeName);
        }
        return new newResult<>(null, "User Is Not Connected");
    }

    @Override
    public ResultMsg searchByKeyword(String userName, String keyword) {
        if(userController.isConnected(userName)){
            String ans = storesFacade.searchByKeyword(keyword);
            if(ans != null) {
                return new ResultMsg(ans, null);
            }
            return new ResultMsg("", "No Items Matched Your Search");
        }
        return new ResultMsg("", "User Is Not Connected");
    }

    @Override
    public ResultMsg searchByCategory(String userName, String category) {
        if(userController.isConnected(userName)){
            String ans = storesFacade.searchByCategory(category);
            if(ans != null) {
                return new ResultMsg(ans, null);
            }
            return new ResultMsg("", "No Items Matched Your Search");
        }
        return new ResultMsg("", "User Is Not Connected");
    }

    @Override
    public ResultMsg searchByName(String userName, String productName) {
        if(userController.isConnected(userName)){
            String ans = storesFacade.searchByName(productName);
            if(ans != null) {
                return new ResultMsg(ans, null);
            }
            return new ResultMsg("", "No Items Matched Your Search");
        }
        return new ResultMsg("", "User Is Not Connected");
    }

    @Override
    public ResultMsg addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        if(userController.isConnected(userName)){
            Store s = null;
            String result;
            if((s = storesFacade.getStore(storeName)) == null)
                return new ResultMsg(null, "No such store");
            else {
                result = userController.addProductToShoppingCart(userName, productName, s, quantity);

                if (result == null)
                    return new ResultMsg(null, "Could not add product from your shopping cart\n");
                else
                    return new ResultMsg(result, null);
            }
        }
        return new ResultMsg(null, "User is not connected");
    }

    @Override
    public newResult<List<String>> displayShoppingCart(String userName) {
        if(userController.isConnected(userName))
            return new newResult<>(userController.displayShoppingCart(userName),null);
        return new newResult<>(null,"User is not connected");
    }

//    @Override
//    public ResultMsg addProductToShoppingCart(String userName, Store s, int quantity, String prodName) {
//        return new ResultMsg("", null);
//    }

    @Override
    public ResultMsg removeProductFromShoppingCart(String userName,String storeName,int quantity,String prodName) {
        if(userController.isConnected(userName)){
            Store s = null;
            String result;
            if((s = storesFacade.getStore(storeName)) == null)
                return new ResultMsg(null, "No such store");
            else {
                result = userController.removeProductFromShoppingCart(userName,s,quantity,prodName);
                if (result == null)
                    return new ResultMsg(null, "Could not remove product from your shopping cart\n");
                else
                    return new ResultMsg(result, null);
            }
        }
        return new ResultMsg(null, "User is not connected");
    }

    @Override
    public ResultBool purchase(String userName, int card, LocalTime expDate,int cvv,String city,String street,int stNum,int apartmentNum) {
        if(userController.isConnected(userName)) {
            String ret = userController.purchase(userName, card, expDate, cvv, city, street, stNum, apartmentNum);
            if (ret == null)
                return new ResultBool(true, null);
            else
                return new ResultBool(false,ret);
        }
        return new ResultBool(false, "User is not connected");
    }

    @Override
    public ResultMsg logOut(String userName) {
        if (userController.isConnected(userName)){
            String us = userController.logOut(userName);
            if(us != null){
                this.myUserName = us;
                return new ResultMsg(us,null);
            }

            return new ResultMsg(null,"Logout failed");
        }
        return new ResultMsg(null, "User is not connected");
    }

    @Override
    public ResultBool openStore(String founderName, String storeName, int card) {
        if(userController.isConnected(founderName)){
            if(storesFacade.addStore(storeName, founderName, card)) {
                //subscribe new founder
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(founderName);
                s.attach(m);
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Store With A Name - " + storeName + " exists");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        if(userController.isConnected(userName)){
            if(storesFacade.addProductToStore(userName, storeName, productName, amount, price, category)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Add Product To Store");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool removeProductFromStore(String userName, String storeName, String productName) {
        if(userController.isConnected(userName)){
            if(storesFacade.removeProductFromStore(userName, storeName, productName)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Remove Product From Store");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        if(userController.isConnected(userName)){
            if(storesFacade.editProductName(userName, storeName, oldProductName, newProductName)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "No Such Product Or Store");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool editProductPrice(String userName, String storeName, String productName, double newPrice) {
        if(userController.isConnected(userName)){
            if(storesFacade.editProductPrice(userName, storeName, productName, newPrice)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Edit Product Price");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool editProductQuantity(String userName, String storeName, String productName, int newQuantity) {
        if(userController.isConnected(userName)){
            if(storesFacade.editProductQuantity(userName, storeName, productName, newQuantity)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Edit Product Quantity");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool changePurchaseOption(String userName, String storeName, String productName, purchaseOption newOption) {
        if(userController.isConnected(userName) && userController.isUserNameExist(userName)){
            if(storesFacade.changePurchaseOption(userName, storeName, productName, newOption)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Change Product Purchase Option");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner) {
        if(userController.isConnected(userName) && userController.isUserNameExist(newOwner)){
            if(storesFacade.appointStoreOwner(userName, storeName, newOwner)) {
                //subscribe new owner
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(newOwner);
                s.attach(m);

                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Appoint Store Owner");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool appointStoreManager(String userName, String storeName, String newManager) {
        if(userController.isConnected(userName)){
            if(storesFacade.appointStoreManager(userName, storeName, newManager)) {
                //subscribe new manager
                Store s = this.storesFacade.getStore(storeName);
                Member m = this.userController.getMember(newManager);
                s.attach(m);
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Appoint Store Manager");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        if(storesFacade.changeStoreManagersPermission(userName, storeName, managerName, newPermission) && userController.isConnected(userName)){
            return new ResultBool(true, null);
        }
        return new ResultBool(false , "Cannot Close Store");
    }

    @Override
    public ResultBool closeStore(String userName, String storeName) {
        if(storesFacade.closeStore(storeName, userName) && userController.isConnected(userName)){
            return new ResultBool(true, null);
        }
        return new ResultBool(false , "Cannot Close Store");
    }

    @Override
    public ResultMsg getStoresManagement(String userName, String storeName) {
        if(userController.isConnected(userName)) {
            String result = storesFacade.getStoresManagement(userName, storeName).toString();
            if(result != null) {
                return new ResultMsg(result, null);
            }
            return new ResultMsg("", "Could Not Get Stores Management");
        }
        return new ResultMsg("", "User is Not Connected");
    }

    @Override
    public ResultMsg getStoresPurchaseHistory(String userName, String storeName) {
        if(userController.isConnected(userName)) {
            String result = storesFacade.getStorePurchaseHistory(userName, storeName);
            if(result != null) {
                return new ResultMsg(result, null);
            }
            return new ResultMsg("", "Cannot Get Stores Purchase History");
        }
        return new ResultMsg("", "User Is Not Connected");
    }

    @Override
    public ResultBool adminCloseStorePermanently(String adminName, String storeName) {
        if(storesFacade.adminCloseStore(storeName) && userController.isUserSysManager(adminName)){
            return new ResultBool(true, null);
        }
        return new ResultBool(false , "Cannot Close Store Permanently");
    }

//    @Override
//    public ResultBool adminTerminateUser(String adminName, String userToTerminate) {
//        String ret = userController.removeMember(adminName, userToTerminate);
//        if(ret == null) {
//            return new ResultBool(true, null);
//        }
//        return new ResultBool(false, ret);
//    }

    @Override
    public ResultMsg adminGetStoresPurchaseHistory(String adminName, String storeName) {
        if(userController.isUserSysManager(adminName)) {
            String result = storesFacade.adminGetStorePurchaseHistory(storeName);
            if(result != null) {
                return new ResultMsg(result, null);
            }
            return new ResultMsg("", "Could Not Get Stores Purchase History");
        }
        return new ResultMsg("", "User is not admin");
    }

    @Override
    public ResultBool addKeyword(String userName, String productName, String storeName, String keyWord) {
        if(userController.isConnected(userName)) {
            if(storesFacade.addKeyword(userName, storeName, productName, keyWord)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Cannot add keyword");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultNum addPolicy(String userName,String store, String policyOn, String description, PolicyType policyType, OperatorComponent operatorComponent) {
        int ret = this.storesFacade.addPolicy(userName,store, policyOn, description, policyType, operatorComponent);
        return new ResultNum(ret,null);
    }

    @Override
    public ResultNum addDiscount(String userName,String store, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        int ret = this.storesFacade.addDiscount(userName,store, discountOn, discountPercentage, description, discountType);
        if(ret == -1)
            return new ResultNum(-1,"ERROR\n");
        return new ResultNum(ret,null);
    }

    public ResultBool supplyServiceExists() {
        if(this.externalSys.isExistSupply())
            return new ResultBool(true, null);
        return new ResultBool(false,"Supply not exist");
    }
    public ResultBool paymentServiceExists(){
        if(this.externalSys.isExistPayment())
            return new ResultBool(true, null);
        return new ResultBool(false,"Payment not exist");

    }
    public ResultBool securityServiceExists(){
        if(this.externalSys.isExistSecurity())
            return new ResultBool(true, null);
        return new ResultBool(false,"security not exist");

    }
    public ResultBool hasAdmin(){
        if(this.userController.hasAdmin())
            return new ResultBool(true, null);
        return new ResultBool(false,"has no admins");

    }

    public String getOnline(){
        return this.myUserName;
    }

    public ResultNum getProductAmount(String storeName, String prodName){
        int amount = this.storesFacade.getProductAmount(storeName,prodName);
        return new ResultNum(amount,null);
    }


    public newResult<List<String>> getAllStores(String userName) {
        if(userController.isConnected(userName)) {
            List<String> res = new LinkedList<>();
            List<Store> stores = storesFacade.getStores();
            for(Store store : stores) {
                res.add(store.getName());
            }
            return new newResult<>(res, null);
        }
        return new newResult<>(null, "user is not connected");
    }

    public newResult<List<String>> getStoresOfUser(String userName) {
        if(userController.isConnected(userName)) {
            List<String> stores = storesFacade.getStoresOfUser(userName);
            return new newResult<>(stores, null);
        }
        return new newResult<>(null, "user is not connected");
    }
}
