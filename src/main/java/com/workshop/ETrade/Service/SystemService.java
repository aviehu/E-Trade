package com.workshop.ETrade.Service;

import com.workshop.ETrade.Controller.Forms.*;
import com.workshop.ETrade.Domain.Facade;
import com.workshop.ETrade.Domain.Notifications.Notification;
import com.workshop.ETrade.Domain.Stores.Discounts.DiscountType;
import com.workshop.ETrade.Domain.Stores.Policies.PolicyType;
import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.Stores.managersPermission;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdaptee;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdaptee;
import com.workshop.ETrade.Domain.purchaseOption;
import com.workshop.ETrade.Persistance.Stores.StoreDTO;
import com.workshop.ETrade.Repository.*;
import com.workshop.ETrade.Service.ResultPackge.Result;
import com.workshop.ETrade.AllRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SystemService implements ServiceInterface {
    private boolean initialize;
    private Facade facade;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StoreBasketRepository storeBasketRepository;

    @Autowired
    private SystemManagerRepository systemManagerRepository;

    public SystemService() {
        initialize = false;
        init();
    }
    private static SystemService myInstance = null;

    public void init(){
        this.facade = new Facade();
//        File file = new File("src\\main\\java\\com\\workshop\\ETrade\\Service\\InitExecuter\\initState.json");
//        String path = file.getAbsolutePath();
//        LoadServiceFromInitState.loadFromFile(path,this);
    }

    @Override
    public Result<Double> getCartPrice(String userName) {
        return facade.getCartPrice(userName);
    }

    @Override
    public Result<Integer> addPolicy(String userName, String store, String policyOn, String description, PolicyType policyType, List<PredicateForm> predicateForms, String connectionType) {
        return facade.addPolicy(userName, store, policyOn, description, policyType, predicateForms, connectionType);
    }


    @Override
    public Result<List<String>> getOnlineMembers(String userName) {
        return facade.getOnlineMembers(userName);
    }

    @Override
    public Result<List<String>> getOfflineMembers(String userName) {
        return facade.getOfflineMembers(userName);
    }

    public Result<Boolean> supplyServiceExists(){
        return facade.supplyServiceExists();
    }

    public Result<Boolean> paymentServiceExists(){
        return facade.paymentServiceExists();
    }

    public Result<Boolean> hasAdmin(){
        return facade.hasAdmin();
    }

    @Override
    public Result<List<String>> getAllStores(String userName) {
        return facade.getAllStores(userName);
    }

    @Override
    public Result<Boolean> removeMember(String userName, String memberToRemove) {
        return facade.removeMember(userName, memberToRemove);
    }

    @Override
    public Result<String> enterSystem() {
        if(!initialize) {
            AllRepos.setStoreRepo(storeRepository);
            AllRepos.setProductRepo(productRepository);
            AllRepos.setMemberRepo(memberRepository);
            AllRepos.setStoreBasketRepo(storeBasketRepository);
            AllRepos.setSystemManagerRepo(systemManagerRepository);
            facade.init();
            initialize = true;
        }
        return facade.enterSystem();
    }

    @Override
    public Result<Boolean> addSystemManager(String userName, String managerToAdd) {
        return facade.addSystemManager(userName, managerToAdd);
    }

    @Override
    public Result<Boolean> removeSystemManager(String userName, String managerToRemove) {
        return facade.removeSystemManager(userName, managerToRemove);
    }

    @Override
    public Result<Boolean> addExternalPaymentService(PaymentAdaptee paymentAdaptee) {
        return facade.addExternalPaymentService(paymentAdaptee);
    }

    @Override
    public Result<Boolean> changeExternalPaymentService(String userName, PaymentAdaptee paymentAdaptee) {
        return facade.changeExternalPaymentService(userName, paymentAdaptee);
    }

    @Override
    public Result<Boolean> editExternalPaymentService() {
        return facade.editExternalPaymentService();
    }

    @Override
    public Result<Boolean> addExternalSupplyService(SupplyAdaptee supplyAdaptee) {
        return facade.addExternalSupplyService(supplyAdaptee);
    }

    @Override
    public Result<Boolean> changeExternalSupplyService(String userName, SupplyAdaptee supplyAdaptee) {
        return facade.changeExternalSupplyService(userName, supplyAdaptee);
    }

    @Override
    public Result<Boolean> editExternalSupplyService() {
        return facade.editExternalSupplyService();
    }

    @Override
    public Result<Boolean> exitSystem(String userName) {
        return facade.exitSystem(userName);
    }

    @Override
    public Result<Boolean> signUp(String userName, String newUserName, String password, String name, String lastName) {
        return facade.signUp(userName, newUserName, password, name, lastName);
    }

    @Override
    public Result<Boolean> login(String userName, String memberUserName, String password) {
        return facade.login(userName, memberUserName, password);
    }

    @Override
    public Result<List<ProductForm>> getStoreInfo(String userName, String storeName) {
        List<Product> products = facade.getStoreInfo(userName, storeName).getVal();
        if(products == null) {
            return new Result<>(null, "Store Doesn't Exist");
        }
        List<ProductForm> formProds = new LinkedList<>();
        for(Product p : products) {
            formProds.add(new ProductForm(p, storeName));
        }
        return new Result(formProds, null);
    }

    @Override
    public Result<List<String>> searchByKeyword(String userName, String keyword) {
        return facade.searchByKeyword(userName, keyword);
    }

    @Override
    public Result<List<String>> searchByCategory(String userName, String category) {
        return facade.searchByCategory(userName, category);
    }

    @Override
    public Result<List<String>> searchByName(String userName, String productName) {
        return facade.searchByName(userName, productName);
    }

    @Override
    public Result<String> addProductToShoppingCart(String userName, String productName, String storeName, int quantity) {
        return facade.addProductToShoppingCart(userName, productName, storeName, quantity);
    }

    @Override
    public Result<List<ProductForm>> displayShoppingCart(String userName) {
        Result<List<ProductForm>> res = facade.displayShoppingCart(userName);
        return res;
    }

    @Override
    public Result<String> removeProductFromShoppingCart(String userName, String storeName, int quantity, String prodName) {
        return facade.removeProductFromShoppingCart(userName, storeName, quantity, prodName);
    }

    @Override
    public Result<Boolean> purchase(String userName, String creditCard, int month, int year, String holderName , int cvv, int id, String country, String city, String street, int stNum, int apartmentNum, int zip) {
        return facade.purchase(userName, creditCard, month, year,holderName, cvv, id, country, city, street, stNum, apartmentNum, zip);
    }

    @Override
    public Result<String> logOut(String userName) {
        return facade.logOut(userName);
    }

    @Override
    public Result<Boolean> openStore(String founderName, String storeName, int card) {
        Result<Boolean> res = facade.openStore(founderName, storeName, card);
        return res;
    }

    @Override
    public Result<Boolean> addProductToStore(String userName, String storeName, String productName, int amount, double price, String category) {
        Result<Boolean> res = facade.addProductToStore(userName, storeName, productName, amount, price, category);
        return res;
    }

    @Override
    public Result<Boolean> removeProductFromStore(String userName, String storeName, String productName) {
        return facade.removeProductFromStore(userName, storeName, productName);
    }

    @Override
    public Result<Boolean> editProductName(String userName, String storeName, String oldProductName, String newProductName) {
        return facade.editProductName(userName, storeName, oldProductName, newProductName);
    }

    @Override
    public Result<Boolean> editProductPrice(String userName, String storeName, String ProductName, double newPrice) {
        return facade.editProductPrice(userName, storeName, ProductName, newPrice);
    }

    @Override
    public Result<Boolean> editProductQuantity(String userName, String storeName, String ProductName, int newQuantity) {
        return facade.editProductQuantity(userName, storeName, ProductName, newQuantity);
    }

    @Override
    public Result<Boolean> changePurchaseOption(String userName, String storeName, String ProductName, purchaseOption newOption) {
        return facade.changePurchaseOption(userName, storeName, ProductName, newOption);
    }

    @Override
    public Result<Boolean> appointStoreOwner(String userName, String storeName, String newOwner) {
        return facade.appointStoreOwner(userName, storeName, newOwner);
    }

    @Override
    public Result<Boolean> removeStoreOwner(String userName, String storeName, String ownerToRemove) {
        return facade.removeStoreOwner(userName, storeName, ownerToRemove);
    }

    @Override
    public Result<Boolean> appointStoreManager(String userName, String storeName, String newManager) {
        return facade.appointStoreManager(userName, storeName, newManager);
    }

    @Override
    public Result<Boolean> removeStoreManager(String userName, String storeName, String managerToRemove) {
        return facade.removeStoreManager(userName, storeName, managerToRemove);
    }

    @Override
    public Result<Boolean> changeStoreManagersPermission(String userName, String storeName, String managerName, managersPermission newPermission) {
        return facade.changeStoreManagersPermission(userName, storeName, managerName, newPermission);
    }

    @Override
    public Result<Boolean> closeStore(String userName, String storeName) {
        return facade.closeStore(userName, storeName);
    }

    @Override
    public Result<String> getStoresManagement(String userName, String storeName) {
        return facade.getStoresManagement(userName, storeName);
    }

    @Override
    public Result<String> getStoresPurchaseHistory(String userName, String storeName) {
        return facade.getStoresPurchaseHistory(userName, storeName);
    }

    @Override
    public Result<Boolean> adminCloseStorePermanently(String adminName, String storeName) {
        return facade.adminCloseStorePermanently(adminName, storeName);
    }

    @Override
    public Result<List<String>> getStoresOfUser(String userName) {
        return facade.getStoresOfUser(userName);
    }

//    @Override
//    public newResult<Boolean> adminTerminateUser(String adminName, String userToTerminate) {
//        return facade.adminTerminateUser(adminName, userToTerminate);
//    }

    @Override
    public Result<String> adminGetStoresPurchaseHistory(String adminName, String storeName) {
        return facade.adminGetStoresPurchaseHistory(adminName, storeName);
    }

    public String getOnline() {
        return facade.getOnline();
    }

    @Override
    public Result<Integer> addDiscount(String userName, String store, String discountOn, int discountPercentage, String description, DiscountType discountType) {
        return facade.addDiscount(userName,store, discountOn, discountPercentage, description, discountType);
    }

    @Override
    public Result<Double> getProdPrice(String store, String prod) {
        return facade.getProdPrice(store, prod);
    }

    @Override
    public Result<Integer> getProdAmount(String store, String prod) {
        return facade.getProdAmount(store, prod);
    }

    @Override
    public Result<List<Notification>> getMessages(String userName) {
        return facade.getMessages(userName);
    }

    @Override
    public Result<Boolean> isAdmin(String userName) {
        return facade.isAdmin(userName);
    }

    @Override
    public Result<Integer> addPreDiscount(String userName, String storeName, String discountOn, int discountPercentage, String description, DiscountType discountType, List<PredicateForm> predicateForms, String connectionType) {
        return facade.addPreDiscount(userName, storeName, discountOn, discountPercentage, description, discountType, predicateForms, connectionType);
    }

    @Override
    public Result<Boolean> addBid(String userName, String storeName, String productName, double bidAmount, CreditCardForm creditCard, SupplyAddressForm supplyAddress) {
        return facade.addBid(userName, storeName, productName, bidAmount, creditCard, supplyAddress);
    }

    @Override
    public Result<List<BidForm>> getStoreBids(String userName, String storeName) {
        return facade.getStoreBids(userName, storeName);
    }

    @Override
    public Result<Boolean> reviewBid(String userName, String storeName, int bidId, boolean approve) {
        return facade.reviewBid(userName, storeName, bidId, approve);
    }

    @Override
    public Result<Boolean> counterBid(String userName, String storeName, int bidId, double newOffer) {
        return facade.counterBid(userName, storeName, bidId, newOffer);
    }

    @Override
    public Result<List<BidForm>> userBids(String userName) {
        return facade.userBids(userName);
    }

    @Override
    public Result<Boolean> counterBidReview(String userName, String storeName, int bidId, boolean approve) {
        return facade.counterBidReview(userName, storeName, bidId, approve);
    }

    public Result<Integer> getProductAmount(String storeName, String prodName){
        return facade.getProductAmount(storeName,prodName);
    }

    public Result<Boolean> addKeyword(String userName, String productName, String storeName, String keyWord) {
        return facade.addKeyword(userName, productName, storeName, keyWord);
    }

}
