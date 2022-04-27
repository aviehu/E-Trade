package Domain;

import Domain.Stores.StoresFacade;
import Domain.Stores.managersPermission;
import Domain.Users.Users.UserController;
import Service.ResultPackge.ResultBool;
import Service.ResultPackge.ResultMsg;

public class Facade implements SystemFacade {
    private StoresFacade storesFacade;
    private UserController userController;

    public Facade() {
        storesFacade = new StoresFacade();
        userController = new UserController();
    }

    @Override
    public ResultBool addExternalPaymentService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool changeExternalPaymentService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool editExternalPaymentService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool addExternalSupplyService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool changeExternalSupplyService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool editExternalSupplyService() {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool exitSystem(String name) {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool exitSystemAsGuest(String name) {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool signUp(String userName, String password) {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool login(String userName, String password) {
        return new ResultBool(true, null);
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
    public ResultBool addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        return new ResultBool(true, null);
    }

    @Override
    public ResultMsg displayShoppingCart(String userName) {
        return new ResultMsg("", null);
    }

    @Override
    public ResultMsg addProductToShoppingCart(String userName) {
        return new ResultMsg("", null);
    }

    @Override
    public ResultMsg removeProductFromShoppingCart(String userName) {
        return new ResultMsg("", null);
    }

    @Override
    public ResultBool purchase(String userName) {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool logOut(String userName) {
        return new ResultBool(true, null);
    }

    @Override
    public ResultBool openStore(String founderName, String storeName, int card) {
        if(userController.isConnected(userName)){
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
        return new ResultMsg("", null);
    }

    @Override
    public ResultBool adminCloseStorePermanently(String adminName, String storeName) {
        return new ResultBool(false, null);
    }

    @Override
    public ResultBool adminTerminateUser(String adminName, String userToTerminate) {
        return new ResultBool(false, null);
    }

    @Override
    public ResultBool adminGetStoresPurchaseHistory(String adminName, String storeName) {
        return new ResultBool(false, null);
    }

    public ResultBool exitSystem() {
        return new ResultBool(false, null);
    }

}
