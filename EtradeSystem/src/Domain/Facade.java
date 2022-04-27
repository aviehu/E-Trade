package Domain;

import Domain.Stores.Store;
import Domain.Stores.StoresFacade;
import Domain.Stores.managersPermission;
import Domain.Users.Users.UserController;

public class Facade implements SystemFacade {
    private StoresFacade storesFacade;
    private UserController userController;

    public Facade() {
        storesFacade = new StoresFacade();
        userController = new UserController();
    }

    @Override
    public boolean addExternalPaymentService() {
        return true;
    }

    @Override
    public boolean changeExternalPaymentService() {
        return false;
    }

    @Override
    public boolean editExternalPaymentService() {
        return false;
    }

    @Override
    public boolean addExternalSupplyService() {
        return false;
    }

    @Override
    public boolean changeExternalSupplyService() {
        return false;
    }

    @Override
    public boolean editExternalSupplyService() {
        return false;
    }

    @Override
    public boolean exitSystem(String name) {
        return false;
    }

    @Override
    public boolean exitSystemAsGuest(String name) {
        return false;
    }

    @Override
    public boolean signUp(String userName, String password) {
        return false;
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public String getStoreInfo(String userName, String storeName) {
        if(userController.isConnected(userName)){
            return storesFacade.displayStore(storeName);
        }
        return null;
    }

    @Override
    public String searchByKeyword(String userName, String keyword) {
        if(userController.isConnected(userName)){
            return storesFacade.searchByKeyword(keyword);
        }
        return null;
    }

    @Override
    public String searchByCategory(String userName, String category) {
        if(userController.isConnected(userName)){
            return storesFacade.searchByCategory(category);
        }
        return null;
    }

    @Override
    public String searchByName(String userName, String productName) {
        if(userController.isConnected(userName)){
            return storesFacade.searchByName(productName);
        }
        return null;
    }

    @Override
    public boolean addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        return false;
    }

    @Override
    public String displayShoppingCart(String userName) {
        return null;
    }

    @Override
    public String addProductToShoppingCart(String userName) {
        return null;
    }

    @Override
    public String removeProductFromShoppingCart(String userName) {
        return null;
    }

    @Override
    public boolean purchase(String userName) {
        return false;
    }

    @Override
    public boolean logOut(String userName) {
        return false;
    }

    @Override
    public boolean openStore(String founderName, String storeName, int card) {
        if(userController.isConnected(userName)){
            return storesFacade.addStore(storeName, founderName, card);
        }
        return false;
    }

    @Override
    public boolean addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        if(userController.isConnected(userName)){
            return storesFacade.addProductToStore(userName, storeName, productName, amount, price, category);
        }
        return false;
    }

    @Override
    public boolean removeProductFromStore(String userName, String storeName, String productName) {
        if(userController.isConnected(userName)){
            return storesFacade.removeProductFromStore(userName, storeName, productName);
        }
        return false;
    }

    @Override
    public boolean editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        if(userController.isConnected(userName)){
            return storesFacade.editProductName(userName, storeName, oldProductName, newProductName);
        }
        return false;
    }

    @Override
    public boolean editProductPrice(String userName, String storeName, String productName, double newPrice) {
        if(userController.isConnected(userName)){
            return storesFacade.editProductPrice(userName, storeName, productName, newPrice);
        }
        return false;
    }

    @Override
    public boolean editProductQuantity(String userName, String storeName, String ProductName, int newQuantity) {
        return false;
    }

    @Override
    public boolean changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption) {
        return false;
    }

    @Override
    public boolean appointStoreOwner(String userName, String storeName, String newOwner) {
        return false;
    }

    @Override
    public boolean appointStoreManager(String userName, String storeName, String newManager) {
        return false;
    }

    @Override
    public boolean changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        return false;
    }

    @Override
    public boolean closeStore(String userName, String storeName) {
        return false;
    }

    @Override
    public String getStoresManagement(String userName, String storeName) {
        return null;
    }

    @Override
    public String getStoresPurchaseHistory(String userName, String storeName) {
        return null;
    }

    @Override
    public boolean adminCloseStorePermanently(String adminName, String storeName) {
        return false;
    }

    @Override
    public boolean adminTerminateUser(String adminName, String userToTerminate) {
        return false;
    }

    @Override
    public boolean adminGetStoresPurchaseHistory(String adminName, String storeName) {
        return false;
    }

    public boolean exitSystem() {
        return true;
    }
}
