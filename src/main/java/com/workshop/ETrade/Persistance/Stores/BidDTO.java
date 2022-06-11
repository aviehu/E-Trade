package com.workshop.ETrade.Persistance.Stores;

import com.workshop.ETrade.Domain.Stores.Bid;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class BidDTO {

    public String productName;
    public String bidderName;
    public double price;
    public Map<String, Boolean> awaitingApprove;
    @Id
    public String bidId;
    public boolean approvedByBidder;
    public boolean rejected;
    public String storeName;

    public String holderName;
    public String cardNumber;
    public int month;
    public int year;
    public int cvv;
    public int id;

    public String city;
    public String street;
    public int streetNum;
    public int apartmentNum;
    public int zip;
    public String country;

    public BidDTO() {

    }

    public BidDTO(String productName, String bidderName, double price, Map<String, Boolean> awaitingApprove, int bidId, boolean approvedByBidder, boolean rejected, String storeName, String holderName, String cardNumber, int month, int year, int cvv, int id, String city, String street, int streetNum, int apartmentNum, int zip, String country) {
        this.productName = productName;
        this.bidderName = bidderName;
        this.price = price;
        this.awaitingApprove = awaitingApprove;
        this.bidId = Integer.toString(bidId);
        this.approvedByBidder = approvedByBidder;
        this.rejected = rejected;
        this.storeName = storeName;
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.id = id;
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.apartmentNum = apartmentNum;
        this.zip = zip;
        this.country = country;
    }

    public BidDTO(Bid bid) {
        awaitingApprove = bid.getAwaitingApprove();
        productName = bid.getProductName();
        bidderName = bid.getBidderName();
        rejected = bid.getRejected();
        storeName = bid.getStoreName();
        price = bid.getPrice();
        bidId = Integer.toString(bid.getId());

        holderName = bid.getCard().getHolderName();
        cardNumber = bid.getCard().getCardNumber();
        month = bid.getCard().getMonth();
        year = bid.getCard().getYear();
        cvv = bid.getCard().getCvv();
        id = bid.getCard().getId();

        city = bid.getSupplyAddress().getCity();
        street = bid.getSupplyAddress().getStreet();
        streetNum = bid.getSupplyAddress().getStreetNum();
        apartmentNum = bid.getSupplyAddress().getApartmentNum();
        zip = bid.getSupplyAddress().getZip();
        country = bid.getSupplyAddress().getCountry();
    }
}
