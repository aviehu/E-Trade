package Domain.Users.Users;

import Domain.Stores.Store;

public abstract class User {
    protected ShoppingCart myShopCart;
    protected SupplyAddress address;
    protected boolean isConnected;
    protected String userName;

    public ShoppingCart getMyShopCart() {
        return myShopCart;
    }

    public User() {
        this.myShopCart = new ShoppingCart(0);
        this.isConnected = false;
        this.address = null;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    // exitSystem:
    // guest loosing his shopCart and not consider as guest(users.2)
    public abstract void exitSystem();

    public boolean addProdToCart(Store s, int quantity, String prodName){
        if(s.hasProd(prodName,quantity))
            return myShopCart.addProd(s,quantity,prodName);
        return false;
    }


    public boolean removeProd(Store s,int quantity,String prodName){
        return myShopCart.removeProd(s,quantity,prodName);
    }
    public String displayCart(){
        return myShopCart.displayCart();
    }

    public abstract boolean purchase(CreditCard card,SupplyAddress address);

    //getStoreInfo:
    //input:store -> output: store info and store's items info(2.1)
    public String getStoreInfo(Store s){
       return s.toString();
    }

    //searchProducts:
    //not in a specific store, search by product name/category/keyword
//    public void searchProducts(String keyword){
//
//    }
    //signIn:
    //create new Member object return it to controller to save it.
    public Member signIn(String userName,String password,int age,String mail,String city,String street,int streetNum,int apartementNum){
        return new Member(userName,password);
    }

    //logIn:
    //checks details with database
    public boolean logIn(String userName,String password){
        return true;
    }

    public void addAddress(String city,String street,int streetNum,int apartmentNum ){
            this.address = new SupplyAddress(city,street,streetNum,apartmentNum);
    }
    public boolean exitSys(){
        return true;
    }

    public String getUserName() {
        return userName;
    }
}
