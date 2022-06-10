package com.workshop.ETrade.Controller.Forms;

import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.purchaseOption;

public class ProductForm {
    public String productName;
    public purchaseOption purchaseOption;
    public int amount;
    public double price;
    public String storeName;
    public ProductForm(String productName, int amount,String storeName) {
        this.productName = productName;
        this.amount = amount;
        this.purchaseOption = null;
        this.price = 0;
        this.storeName = storeName;
    }

    public ProductForm(Product product, String storeName) {
        this.productName = product.getName();
        this.purchaseOption = product.getSelectedOption();
        this.amount = product.getAmount();
        this.price = product.getPrice();
        this.storeName = storeName;
    }
}
