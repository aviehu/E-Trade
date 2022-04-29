package Service;

import Domain.Stores.managersPermission;
import Domain.purchaseOption;
import Service.ResultPackge.ResultBool;
import Service.ResultPackge.ResultMsg;

public interface ServiceInterface {

    public ResultBool addExternalPaymentService();

    public ResultBool changeExternalPaymentService();

    public ResultBool editExternalPaymentService();

    public ResultBool addExternalSupplyService();

    public ResultBool changeExternalSupplyService();

    public ResultBool editExternalSupplyService();

    public ResultBool exitSystem(String name);

    public ResultBool exitSystemAsGuest(String name);

    public ResultBool signUp(String userName, String password);

    public ResultBool login(String userName, String password);

    public ResultMsg getStoreInfo(String userName, String storeName);

    public ResultMsg searchByKeyword(String userName, String keyword);

    public ResultMsg searchByCategory(String userName, String category);

    public ResultMsg searchByName(String userName, String productName);

    public ResultBool addProductToShoppingCart(String userName, String productName, String storeName, int quantity);

    public ResultMsg displayShoppingCart(String userName);

    public ResultMsg addProductToShoppingCart(String userName);

    public ResultMsg removeProductFromShoppingCart(String userName);

    public ResultBool purchase(String userName);

    public ResultBool logOut(String userName);

    public ResultBool openStore(String founderName, String storeName, int card);

    public ResultBool addProductToStore(String userName, String storeName, String productName, int amount, double price, String category);

    public ResultBool removeProductFromStore(String userName, String storeName, String productName);

    public ResultBool editProductName(String userName, String storeName, String oldProductName, String newProductName);

    public ResultBool editProductPrice(String userName, String storeName, String productName, double newPrice);

    public ResultBool editProductQuantity(String userName, String storeName, String productName, int newQuantity);

    public ResultBool changePurchaseOption(String userName, String storeName, String productName, purchaseOption newOption);

    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner);

    public ResultBool appointStoreManager(String userName, String storeName, String newManager);

    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission);

    public ResultBool closeStore(String userName, String storeName);

    public ResultMsg getStoresManagement(String userName, String storeName);

    public ResultMsg getStoresPurchaseHistory(String userName, String storeName);

    public ResultBool adminCloseStorePermanently(String adminName, String storeName);

    public ResultBool adminTerminateUser(String adminName, String userToTerminate);

    public ResultBool adminGetStoresPurchaseHistory(String adminName, String storeName);

    public ResultBool exitSystem();

}