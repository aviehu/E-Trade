package Domain.Users.Users;

public abstract class User {
    protected ShoppingCart myShopCart;

    public ShoppingCart getMyShopCart() {
        return myShopCart;
    }

    public User() {
        this.myShopCart = new ShoppingCart(0);
    }

    // exitSystem:
    // guest loosing his shopCart and not consider as guest(users.2)
    public abstract void exitSystem();

    public void addProd(Store s,int quantity,String prodName){
        myShopCart.addProd(s,quantity,prodName);
    }

    public void removeProd(Store s,int quantity,String prodName){
        myShopCart.removeProd(s,quantity,prodName);
    }
    public String displayCart(){
        return myShopCart.displayCart();
    }

    public abstract boolean purchase(CreditCard card,SupplyAddress address);

    //getStoreInfo:
    //input:store -> output: store info and store's items info(2.1)
    public String getStoreInfo(Store s){
       return s.getInfo();
    }

    //searchProducts:
    //not in a specific store, search by product name/category/keyword
    public void searchProducts(String keyword){

    }
    //signIn:
    //create new Member object return it to controller to save it.
    public Member signIn(String userName,String password,int age,String mail,String city,String street,int streetNum,int apartementNum){
        return new Member(userName,password,age,mail,city,street,streetNum,apartementNum);
    }

    //logIn:
    //checks details with database
    public boolean logIn(String userName,String password){
        return true;
    }
}
