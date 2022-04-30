package Tests.Bridge;

import Domain.Stores.managersPermission;
import Domain.Users.ExternalService.Payment.PaymentAdaptee;
import Domain.Users.ExternalService.Supply.SupplyAdaptee;
import Domain.purchaseOption;
import Service.ResultPackge.ResultBool;
import Service.ResultPackge.ResultMsg;
import Service.ServiceInterface;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalTime;

public class SystemServiceProxy implements ServiceInterface {

    private ServiceInterface real;

    public void setReal(ServiceInterface real){
        this.real = real;
        //real.init();
    }

    @Override
    public void init() {
        if (real == null)
            throw new NotImplementedException();
        real.init();
    }

    @Override
    public ResultBool supplyServiceExists() {
        if (real == null)
            throw new NotImplementedException();
        return real.supplyServiceExists();
    }

    @Override
    public ResultBool paymentServiceExists() {
        if (real == null)
            throw new NotImplementedException();
        return real.paymentServiceExists();
    }

    @Override
    public ResultBool hasAdmin() {
        if (real == null)
            throw new NotImplementedException();
        return real.hasAdmin();
    }

    @Override
    public ResultBool removeMember(String userName, String memberToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeMember(userName, memberToRemove);
    }

    @Override
    public ResultMsg enterSystem() {
        if (real == null)
            throw new NotImplementedException();
        return real.enterSystem();
    }

    @Override
    public ResultBool addSystemManager(String userName, String managerToAdd) {
        if (real == null)
            throw new NotImplementedException();
        return real.addSystemManager(userName, managerToAdd);
    }

    @Override
    public ResultBool removeSystemManager(String userName, String managerToRemove) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeSystemManager(userName, managerToRemove);
    }

    @Override
    public ResultBool addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.addExternalPaymentService(paymentAdaptee);
    }

    @Override
    public ResultBool changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeExternalPaymentService(userName, paymentAdaptee);
    }

    @Override
    public ResultBool editExternalPaymentService() {
        if (real == null)
            throw new NotImplementedException();
        return real.editExternalPaymentService();
    }

    @Override
    public ResultBool addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.addExternalSupplyService(supplyAdaptee);
    }

    @Override
    public ResultBool changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeExternalSupplyService(userName, supplyAdaptee);
    }

    @Override
    public ResultBool editExternalSupplyService() {
        if (real == null)
            throw new NotImplementedException();
        return real.editExternalSupplyService();
    }

    @Override
    public ResultBool exitSystem(String name) {
        if (real == null)
            throw new NotImplementedException();
        return real.exitSystem(name);
    }

    @Override
    public ResultBool signUp(String userName, String newUserName, String password) {
        if (real == null)
            throw new NotImplementedException();
        return real.signUp(userName, newUserName, password);
    }

    @Override
    public ResultBool login(String userName, String memberUserName, String password) {
        if (real == null)
            throw new NotImplementedException();
        return real.login(userName, memberUserName, password);
    }

    @Override
    public ResultMsg getStoreInfo(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoreInfo(userName, storeName);
    }

    @Override
    public ResultMsg searchByKeyword(String userName, String keyword) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByKeyword(userName, keyword);
    }

    @Override
    public ResultMsg searchByCategory(String userName, String category) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByCategory(userName, category);
    }

    @Override
    public ResultMsg searchByName(String userName, String productName) {
        if (real == null)
            throw new NotImplementedException();
        return real.searchByName(userName, productName);
    }

    @Override
    public ResultMsg addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        if (real == null)
            throw new NotImplementedException();
        return real.addProductToShoppingCart(userName, productName, storeName, quantity);
    }

    @Override
    public ResultMsg displayShoppingCart(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.displayShoppingCart(userName);
    }

    @Override
    public ResultMsg removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeProductFromShoppingCart(userName, storeName, quantity, prodName);
    }

    @Override
    public ResultBool purchase(String userName, int card, LocalTime expDate, int cvv, String city, String street, int stNum, int apartmentNum) {
        if (real == null)
            throw new NotImplementedException();
        return real.purchase(userName, card, expDate, cvv, city, street, stNum, apartmentNum);
    }

    @Override
    public ResultMsg logOut(String userName) {
        if (real == null)
            throw new NotImplementedException();
        return real.logOut(userName);
    }

    @Override
    public ResultBool openStore(String founderName, String storeName, int card) {
        if (real == null)
            throw new NotImplementedException();
        return real.openStore(founderName, storeName, card);
    }

    @Override
    public ResultBool addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        if (real == null)
            throw new NotImplementedException();
        return real.addProductToStore(userName, storeName, productName, amount, price, category);
    }

    @Override
    public ResultBool removeProductFromStore(String userName, String storeName, String productName) {
        if (real == null)
            throw new NotImplementedException();
        return real.removeProductFromStore(userName, storeName, productName);
    }

    @Override
    public ResultBool editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductName(userName, storeName, oldProductName, newProductName);
    }

    @Override
    public ResultBool editProductPrice(String userName, String storeName, String ProductName, double newPrice) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductPrice(userName, storeName, ProductName, newPrice);
    }

    @Override
    public ResultBool editProductQuantity(String userName, String storeName, String ProductName, int newQuantity) {
        if (real == null)
            throw new NotImplementedException();
        return real.editProductQuantity(userName, storeName, ProductName, newQuantity);
    }

    @Override
    public ResultBool changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption) {
        if (real == null)
            throw new NotImplementedException();
        return real.changePurchaseOption(userName, storeName, ProductName, newOption);
    }

    @Override
    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner) {
        if (real == null)
            throw new NotImplementedException();
        return real.appointStoreOwner(userName, storeName, newOwner);
    }

    @Override
    public ResultBool appointStoreManager(String userName, String storeName, String newManager) {
        if (real == null)
            throw new NotImplementedException();
        return real.appointStoreManager(userName, storeName, newManager);
    }

    @Override
    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        if (real == null)
            throw new NotImplementedException();
        return real.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
    }

    @Override
    public ResultBool closeStore(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.closeStore(userName, storeName);
    }

    @Override
    public ResultMsg getStoresManagement(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresManagement(userName, storeName);
    }

    @Override
    public ResultMsg getStoresPurchaseHistory(String userName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.getStoresPurchaseHistory(userName, storeName);
    }

    @Override
    public ResultBool adminCloseStorePermanently(String adminName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.adminCloseStorePermanently(adminName, storeName);
    }

    @Override
    public ResultBool adminTerminateUser(String adminName, String userToTerminate) {
        if (real == null)
            throw new NotImplementedException();
        return real.adminTerminateUser(adminName, userToTerminate);
    }

    @Override
    public ResultMsg adminGetStoresPurchaseHistory(String adminName, String storeName) {
        if (real == null)
            throw new NotImplementedException();
        return real.adminGetStoresPurchaseHistory(adminName, storeName);
    }

    @Override
    public ResultBool addKeyword(String userName, String productName, String storeName, String keyWord) {
        if (real == null)
            throw new NotImplementedException();
        return real.addKeyword(userName, productName, storeName, keyWord);
    }
}
