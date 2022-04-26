package Domain.Stores;

import java.time.LocalDate;

public class Auction {
    private int auctionId;
    private double price;
    private LocalDate auctionEnd;
    private Product product;
    private String currentWinner;

    public Auction(double price, LocalDate auctionEnd, Product product, String currentWinner, int auctionId) {
        this.auctionId = auctionId;
        this.price = price;
        this.auctionEnd = auctionEnd;
        this.product = product;
        this.currentWinner = currentWinner;
    }

    public int getAuctionId(){
        return auctionId;
    }

    public boolean isStillAvailable() {
        return LocalDate.now().isBefore(auctionEnd);
    }

    public boolean makeOffer(String userName, double newPrice) {
        if(isStillAvailable() && newPrice > price) {
            price = newPrice;
            currentWinner = userName;
            return true;
        }
        return false;
    }

    public String getCurrentWinner(){
        return currentWinner;
    }

    public String toString(){
        String result = "Auction " + auctionId + ":\n";
        result +=       "Product: " + product.getName() + "\n";
        result +=       "Current Price: " + price + "\n";
        result +=       "Current Winner: " + currentWinner + "\n";
        result +=       "Auction Ends At: " + auctionEnd.toString() + "\n\n";
        return result;
    }

}
