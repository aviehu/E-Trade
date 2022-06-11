package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.Product;
import com.workshop.ETrade.Domain.purchaseOption;

import java.util.List;

public class ProductDTO {

    public String name;



    public int amount;

    public String category;

    public double price;

    public List<String> keywords;

    public purchaseOption selectedOption;

    public ProductDTO(String name, int amount, String category, double price, List<String> keywords, purchaseOption selectedOption) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.price = price;
        this.keywords = keywords;
        this.selectedOption = selectedOption;
    }

    public ProductDTO(Product product) {
        name = product.getName();
        amount = product.getAmount();
        category = product.getCategory();
        price = product.getPrice();
        keywords = product.getKeywords();
        selectedOption = product.getSelectedOption();
    }
}