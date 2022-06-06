package com.workshop.ETrade.Controller.Forms;

import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.purchaseOption;

public class ProductForm {
    public String productName;
    public purchaseOption purchaseOption;
    public int amount;
    public double price;

    public ProductForm(Product product) {
        this.productName = product.getName();
        this.purchaseOption = product.getSelectedOption();
        this.amount = product.getAmount();
        this.price = product.getPrice();
    }
}
