package Domain.Stores;

import java.time.LocalDate;

public class Auction {
    private int price;
    private LocalDate auctionEnd;
    private Product product;
    private String currentWinner;

    public Auction(int price, LocalDate auctionEnd, Product product, String currentWinner) {
        this.price = price;
        this.auctionEnd = auctionEnd;
        this.product = product;
        this.currentWinner = currentWinner;
    }


}
