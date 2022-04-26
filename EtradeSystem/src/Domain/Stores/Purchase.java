package Domain.Stores;

import java.time.LocalDate;

public class Purchase {
    private int price;
    private String productName;
    private int quantity;
    private String buyer;
    private LocalDate purchaseTime;

    public Purchase(int price, String productName, int quantity, String buyer, LocalDate purchaseTime) {
        this.price = price;
        this.productName = productName;
        this.quantity = quantity;
        this.buyer = buyer;
        this.purchaseTime = purchaseTime;
    }
}
