package Domain.Users.Users;


import java.util.HashMap;
import java.util.List;

public class Store {
//    private Member founder;

    private String name;
    private int card;

    public Store(String name, int card) {
        this.card = card;
//        this.founder = founder;
        this.name = name;
    }

    public boolean purchase(HashMap<String,Integer> prods,int price){
        for(String prodName : prods.keySet()){
            if(!canPurchase(prodName,prods.get(prodName)))
                return false;
        }
        //here need to check i got money

        for(String prodName : prods.keySet()){
            getProd(prodName).reduceQuantity(prods.get(prodName));
        }
        return true;
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

    public int getCard() {
        return card;
    }

    public void writeReviewOnProd(String prodName,String review,String userName){

    }

    public void rateStore(int rate,String userName){

    }
    public void rateProd(String prodName,int rate,String userName){

    }
    public void askAs(String message,String userName){

    }
}
