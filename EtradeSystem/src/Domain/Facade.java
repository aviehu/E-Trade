package Domain;

import Domain.Stores.Store;
import Domain.Stores.StoresFacade;
import Domain.Stores.managersPermission;
import Domain.Users.ExternalService.ExtSysController;
import Domain.Users.ExternalService.Payment.PaymentAdaptee;
import Domain.Users.ExternalService.Supply.SupplyAdaptee;
import Domain.Users.Users.SystemManager;
import Domain.Users.Users.UserController;
import Service.ResultPackge.Result;
import Service.ResultPackge.ResultBool;
import Service.ResultPackge.ResultMsg;
import Service.ResultPackge.ResultNum;

import java.time.LocalTime;

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
    public ResultBool removeMember(String userName, String memberToRemove) {
        if(userController.isConnected(userName)){
            if(userController.removeMember(userName, memberToRemove))
                return new ResultBool(true,null);
            return new ResultBool(false,"Cant remove "+memberToRemove+"\n");

        }
        return new ResultBool(false, "User is not connected");
    }

    @Override
    public ResultMsg enterSystem() {
        String userName = userController.enterSystem();
        if(userName != null) {
            this.myUserName = userName;
            return new ResultMsg(userName, null);
        }
        return new ResultMsg(null,"Cant enter System");
    }

    @Override
    public ResultBool addSystemManager(String userName, String managerToAdd) {
        if(userController.isConnected(userName)){
            if(userController.addSystemManager(userName,managerToAdd))
                return new ResultBool(true,null);
            return new ResultBool(false,"cant add "+managerToAdd+" as System Manager\n");
        }
        return new ResultBool(false, "User is not connected");
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
    public ResultBool signUp(String userName,String newUserName, String password) {
        if(userController.isConnected(userName)) {
            if (!this.userController.isValidPassword(password))
                return new ResultBool(false, "Invalid password\n password must be at least 8 characters and contain uppercase character\n");
            if (this.userController.isUserNameExist(newUserName))
                return new ResultBool(false, "User name not available\n");
            userController.signUp(newUserName, password);
            return new ResultBool(true, null);
        }
        return new ResultBool(false, "User is not connected\n");
    }

    @Override
    public ResultBool login(String userName,String memberUserName, String password) {
        if(userController.isConnected(userName)) {
            if (!userController.isUserNameExist(memberUserName))
                return new ResultBool(false, "Wrong user name");
            if (userController.logIn(memberUserName, password)){
                this.myUserName = memberUserName;
                return new ResultBool(true, null);
            }

            return new ResultBool(false, "Wrong password");
        }
        return new ResultBool(false, "User is not connected\n");
    }

    @Override
    public ResultMsg getStoreInfo(String userName, String storeName) {
        if(userController.isConnected(userName)){
            String ans = storesFacade.displayStore(storeName);
            if(ans != null) {
                return new ResultMsg(ans, null);
            }
            return new ResultMsg("", "No Such Store" + storeName);
        }
        return new ResultMsg("", "User Is Not Connected");
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
                    return new ResultMsg(null, "Could not remove product from your shopping cart\n");
                else
                    return new ResultMsg(result, null);
            }
        }
        return new ResultMsg(null, "User is not connected");
    }

    @Override
    public ResultMsg displayShoppingCart(String userName) {
        if(userController.isConnected(userName))
            return new ResultMsg(userController.displayShoppingCart(userName),null);
        return new ResultMsg(null,"User is not connected");
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
            if (userController.purchase(userName, card, expDate, cvv, city, street, stNum, apartmentNum))
                return new ResultBool(true, null);
            else
                return new ResultBool(false,"Could not purchase your shopping cart");
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
        if(userController.isConnected(userName)){
            if(storesFacade.changePurchaseOption(userName, storeName, productName, newOption)) {
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Change Product Purchase Option");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner) {
        if(userController.isConnected(userName)){
            if(storesFacade.appointStoreOwner(userName, storeName, newOwner)) {
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
                return new ResultBool(true, null);
            }
            return new ResultBool(false, "Could Not Appoint Store Manager");
        }
        return new ResultBool(false, "User Is Not Connected");
    }

    @Override
    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        return new ResultBool(false, null);
    }

    @Override
    public ResultBool closeStore(String userName, String storeName) {
        return new ResultBool(false, null);
    }

    @Override
    public ResultMsg getStoresManagement(String userName, String storeName) {
        return new ResultMsg("", null);
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

    @Override
    public ResultBool adminTerminateUser(String adminName, String userToTerminate) {
        return new ResultBool(false, null);
    }

    @Override
    public ResultBool adminGetStoresPurchaseHistory(String adminName, String storeName) {
        return new ResultBool(false, null);
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


}
