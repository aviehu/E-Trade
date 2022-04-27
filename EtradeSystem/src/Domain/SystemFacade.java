package Domain;

import Domain.Stores.managersPermission;

import java.time.LocalTime;

public interface SystemFacade {

    public String initSystem();

    public boolean addExternalPaymentService();

    public boolean changeExternalPaymentService();

    public boolean editExternalPaymentService();

    public boolean addExternalSupplyService();

    public boolean changeExternalSupplyService();

    public boolean editExternalSupplyService();

    public boolean exitSystem(String name);

    public boolean exitSystemAsGuest(String name);

    public boolean signUp(String userName, String password);

    public boolean login(String userName, String password);

    public String getStoreInfo(String userName, String storeName);

    public String searchByKeyword(String userName, String keyword);

    public String searchByCategory(String userName, String category);

    public String searchByName(String userName, String productName);

    public boolean addProductToShoppingCart(String userName, String productName, String storeName, int quantity);

    public String displayShoppingCart(String userName);

    public String addProductToShoppingCart(String userName);

    public String removeProductFromShoppingCart(String userName);

    public boolean purchase(String userName);

    public boolean logOut(String userName);

    public boolean openStore(String founderName, String storeName, int card);

    public boolean addProductToStore(String userName, String storeName, String productName, int amount, int price, String category);

    public boolean removeProductFromStore(String userName, String storeName, String productName);

    public boolean editProductName(String userName, String storeName, String oldProductName, String newProductName);

    public boolean editProductPrice(String userName, String storeName, String ProductName, int newPrice);

    public boolean editProductQuantity(String userName, String storeName, String ProductName, int newQuantity);

    public boolean changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption);

    public boolean changePurchasePolicy();

    public boolean changeDiscountPolicy();

    public boolean appointStoreOwner(String userName, String storeName, String newOwner);

    public boolean appointStoreManager(String userName, String storeName, String newManager);

    public boolean changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission);

    public boolean closeStore(String userName, String storeName);

    public String getStoresManagement(String userName, String storeName);

    public String getStoresPurchaseHistory(String userName, String storeName);

    public boolean adminCloseStorePermanently(String adminName, String storeName);

    public boolean adminTerminateUser(String adminName, String userToTerminate);

    public boolean adminGetStoresPurchaseHistory(String adminName, String storeName);

}
