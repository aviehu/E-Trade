package com.workshop.ETrade.Persistance;

import com.workshop.ETrade.Domain.Stores.Bid;

import java.util.Map;

public class BidDTO {
    public String productName;
    public String bidderName;
    public double price;
    public Map<String, Boolean> awaitingApprove;
    public int bidId;
    public boolean approvedByBidder;
    public boolean rejected;

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


    public BidDTO(Bid bid) {
        productName = bid.getProductName();
        bidderName = bid.getBidderName();
        rejected = bid.getRejected();

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
