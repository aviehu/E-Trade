package Domain.Users.Users;

import Domain.Stores.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//is paying and supply is known to store or the market responsible to supply?
public class StoreBasket {
    private HashMap<String,Integer> prods; // <ProdName,quantity>
    private Store store;

    public StoreBasket(Store store) {
        this.prods = new HashMap<>();
        this.store = store;
    }

    public String addProd(int quantity,String prodName){
        if(store.canPurchase(prodName,quantity)) {
            if (prods.isEmpty() || !prods.containsKey(prodName))
                prods.put(prodName, quantity);
            else {
                prods.computeIfPresent(prodName, (k, v) -> v + quantity);
            }
            return quantity +" " + prodName + " successfully added to your shopping cart\n";
        }
        else
            return "Can't add "+quantity+ " "+ prodName+ " from " + getStoreName();
    }
    public String removeProd(int quantity,String prodName){
        if (prods.isEmpty())
            return "Can't remove " + prodName + ", your basket is empty\n";
        else if (!prods.containsKey(prodName))
            return "Can't remove " + prodName + ", your basket does not contain it\n";
        else if(prods.get(prodName) - quantity == 0) {
            prods.remove(prodName);
            return null;
        }else if(prods.get(prodName) - quantity < 0) {
            int currAmount = getProds().get(prodName);
            return "Can't remove " + quantity + " " + prodName + ", you currently have " +currAmount+ " "+ prodName+"\n";
        }
        else{
                prods.computeIfPresent(prodName, (k, v) -> v - quantity);
                return null;
            }
    }

    public double getTotalPrice(){
        double price = 0;
//        for (String prod : prods.keySet()){
//            price += (s
//        }
        price = store.getPrice(prods);
        return price;
    }

    public Store getStore() {
        return store;
    }

    public String displayBasket(){
        String display = "";
        display += "**"+store.getName()+"**\n\n";
        display += "\tProduct\tQuantity\t\n";
        for(String p : prods.keySet()){
            display += "\t"+p+"\t"+prods.get(p)+"\t"+"\n";
        }
        return display;
    }
    public String getStoreName(){
        return store.getName();
    }
    public boolean purchase(String userName){
        store.purchase(prods,userName);
        return true;
    }

    public String canPurchase(){
        for (String prod : prods.keySet()){
            if (!store.canPurchase(prod,prods.get(prod)))
                return store.getName()+" refused to deliver " + prods.get(prod)+ " "+prod+"\n" ;
        }
        return null;
    }
    public void finishPurchase(){
        prods.clear();
    }
    public boolean isProdExisit(String prodName){
        return prods.containsKey(prodName);
    }

    public HashMap<String, Integer> getProds() {
        return prods;
    }
}
