package Domain.Users.Users;

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

    public void addProd(int quantity,String prodName){
        if(store.canPurchase(prodName,quantity)) {
            if (prods.isEmpty() || !prods.containsKey(prodName))
                prods.put(prodName, quantity);
            else {
                prods.computeIfPresent(prodName, (k, v) -> v + quantity);
            }
        }
    }
    public void removeProd(int quantity,String prodName){
        if (prods.isEmpty() || !prods.containsKey(prodName))
            System.out.println("no such product " + prodName+" in your basket");
        else if(prods.get(prodName) - quantity <= 0)
            prods.remove(prodName);
        else
            prods.computeIfPresent(prodName,(k,v)->v-quantity);
    }

    public int getTotalPrice(){
        int price = 0;
        for (String prod : prods.keySet()){
            price += (store.getProd(prod).getPrice()*prods.get(prod));
        }
        return price;
    }

    public Store getStore() {
        return store;
    }

    public String displayBasket(){
        String display = "";
        display += "**"+store.getName()+"**\n\n";
        display += "\tProduct\tQuantity\tTotal Price\n";
        for(String p : prods.keySet()){
            display += "\t"+p+"\t"+prods.get(p)+"\t"+ (store.getProd(p).getPrice()*prods.get(p))+"\n";
        }
        return display;
    }
    public String getStoreName(){
        return store.getName();
    }
    public boolean purchase(){
        store.purchase(prods,getTotalPrice());
        return true;
    }

    public boolean canPurchase(){
        for (String prod : prods.keySet()){
            if (!store.canPurchase(prod,prods.get(prod)))
                return false;
        }
        return true;
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
