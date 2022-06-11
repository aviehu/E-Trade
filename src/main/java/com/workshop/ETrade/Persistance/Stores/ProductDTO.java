package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.Product;
import org.springframework.data.annotation.Id;
import com.workshop.ETrade.Domain.purchaseOption;

import java.util.List;

public class ProductDTO {
    @Id
    public String name;

    public int amount;

    public String category;

    public double price;

    public List<String> keywords;

    public purchaseOption selectedOption;

    public ProductDTO() {

    }

    public ProductDTO(String name, int amount, String category, double price, List<String> keywords, String selectedOption) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.price = price;
        this.keywords = keywords;
        purchaseOption p;
        switch (selectedOption) {
            case "IMMEDIATE": {
                p = purchaseOption.IMMEDIATE;
                break;
            }
            default: {
                p = purchaseOption.BID;
            }
        }
        this.selectedOption = p;
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