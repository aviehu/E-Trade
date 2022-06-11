package com.workshop.ETrade.Domain.Users;

import com.workshop.ETrade.Domain.Stores.Store;
import com.workshop.ETrade.Persistance.Users.StoreBasketDTO;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


//is paying and supply is known to store or the market responsible to supply?
public class StoreBasket {
    private HashMap<String,Integer> prods; // <ProdName,quantity>
    private Store store;
    private String userName;

    public void setProds(HashMap<String, Integer> prods) {
        this.prods = prods;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StoreBasket(Store store, String userName) {
        this.prods = new HashMap<>();
        this.store = store;
        this.userName = userName;
    }

    public StoreBasket(StoreBasketDTO sbDTO, Store store) {
        this.prods = sbDTO.getProds();
        this.userName = sbDTO.getUserName();
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

    public HashMap<String,Integer> displayBasket(){
        return prods;
    }
    public String getStoreName(){
        return store.getName();
    }
    public boolean purchase(String userName){
        return store.purchase(prods,userName);
       // return true;
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
