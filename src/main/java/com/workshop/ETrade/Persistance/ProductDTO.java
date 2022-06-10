package com.workshop.ETrade.Persistance;

import com.workshop.ETrade.Domain.Stores.Product;

import java.util.List;

public class ProductDTO {

    public String name;

    public int amount;

    public double price;

    public List<String> keywords;

    public String purchaseOption;

    public ProductDTO(Product product) {
        name = product.getName();
        amount = product.getAmount();
        price = product.getPrice();
        keywords = product.getKeywords();
        purchaseOption = product.getSelectedOption().toString();
    }
}