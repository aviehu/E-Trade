package Domain.Stores;

import java.time.LocalDate;

public class Purchase {
    private double price;
    private String productName;
    private int quantity;
    private String buyer;
    private LocalDate purchaseTime;
    private int purchaseId;

    public Purchase(double price, String productName, int quantity, String buyer, int purchaseId) {
        this.price = price;
        this.productName = productName;
        this.quantity = quantity;
        this.buyer = buyer;
        this.purchaseTime = LocalDate.now();
        this.purchaseId = purchaseId;
    }

    public String toString() {
        String result = "Purchase " + purchaseId + ":\n";
        result +=       "Buyer: " + buyer + "\n";
        result +=       "Product: " + productName + "\n";
        result +=       "Quantity: " + quantity + "\n";
        result +=       "Price: " + price + "\n";
        result +=       "Purchase Time: " + purchaseTime.toString() + "\n\n";
        return result;
    }
}
