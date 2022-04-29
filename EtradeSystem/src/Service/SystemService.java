package Service;

import Domain.Stores.managersPermission;
import Domain.purchaseOption;
import Service.ResultPackge.ResultBool;
import Service.ResultPackge.ResultMsg;

public class SystemService implements ServiceInterface{
    @Override
    public ResultBool addExternalPaymentService() {
        return null;
    }

    @Override
    public ResultBool changeExternalPaymentService() {
        return null;
    }

    @Override
    public ResultBool editExternalPaymentService() {
        return null;
    }

    @Override
    public ResultBool addExternalSupplyService() {
        return null;
    }

    @Override
    public ResultBool changeExternalSupplyService() {
        return null;
    }

    @Override
    public ResultBool editExternalSupplyService() {
        return null;
    }

    @Override
    public ResultBool exitSystem(String name) {
        return null;
    }

    @Override
    public ResultBool exitSystemAsGuest(String name) {
        return null;
    }

    @Override
    public ResultBool signUp(String userName, String password) {
        return null;
    }

    @Override
    public ResultBool login(String userName, String password) {
        return null;
    }

    @Override
    public ResultMsg getStoreInfo(String userName, String storeName) {
        return null;
    }

    @Override
    public ResultMsg searchByKeyword(String userName, String keyword) {
        return null;
    }

    @Override
    public ResultMsg searchByCategory(String userName, String category) {
        return null;
    }

    @Override
    public ResultMsg searchByName(String userName, String productName) {
        return null;
    }

    @Override
    public ResultBool addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        return null;
    }

    @Override
    public ResultMsg displayShoppingCart(String userName) {
        return null;
    }

    @Override
    public ResultMsg addProductToShoppingCart(String userName) {
        return null;
    }

    @Override
    public ResultMsg removeProductFromShoppingCart(String userName) {
        return null;
    }

    @Override
    public ResultBool purchase(String userName) {
        return null;
    }

    @Override
    public ResultBool logOut(String userName) {
        return null;
    }

    @Override
    public ResultBool openStore(String founderName, String storeName, int card) {
        return null;
    }

    @Override
    public ResultBool addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        return null;
    }

    @Override
    public ResultBool removeProductFromStore(String userName, String storeName, String productName) {
        return null;
    }

    @Override
    public ResultBool editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        return null;
    }

    @Override
    public ResultBool editProductPrice(String userName, String storeName, String productName, double newPrice) {
        return null;
    }

    @Override
    public ResultBool editProductQuantity(String userName, String storeName, String productName, int newQuantity) {
        return null;
    }

    @Override
    public ResultBool changePurchaseOption(String userName, String storeName, String productName, purchaseOption newOption) {
        return null;
    }

    @Override
    public ResultBool appointStoreOwner(String userName, String storeName, String newOwner) {
        return null;
    }

    @Override
    public ResultBool appointStoreManager(String userName, String storeName, String newManager) {
        return null;
    }

    @Override
    public ResultBool changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        return null;
    }

    @Override
    public ResultBool closeStore(String userName, String storeName) {
        return null;
    }

    @Override
    public ResultMsg getStoresManagement(String userName, String storeName) {
        return null;
    }

    @Override
    public ResultMsg getStoresPurchaseHistory(String userName, String storeName) {
        return null;
    }

    @Override
    public ResultBool adminCloseStorePermanently(String adminName, String storeName) {
        return null;
    }

    @Override
    public ResultBool adminTerminateUser(String adminName, String userToTerminate) {
        return null;
    }

    @Override
    public ResultBool adminGetStoresPurchaseHistory(String adminName, String storeName) {
        return null;
    }

    @Override
    public ResultBool exitSystem() {
        return null;
    }
}
