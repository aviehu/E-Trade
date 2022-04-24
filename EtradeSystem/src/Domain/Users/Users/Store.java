package Domain.Users.Users;


public class Store {
//    private Member founder;

    private String name;

    public Store( String name) {
//        this.founder = founder;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@Override
    public String getInfo() {
        return name;
    }

    //@Override
    public Product getProd(String prodName) {

        return null;
    }

    //@Override
    public void getAllProdsByCategory(String categoryName) {

    }

    //@Override
    public void getAllProdsByKeyword(String keyword) {

    }

    //@Override
    public boolean canPurchase(String prodName, int quantity) {
        return false;
    }
}
