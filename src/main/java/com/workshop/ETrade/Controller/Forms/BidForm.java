package com.workshop.ETrade.Controller.Forms;

import com.workshop.ETrade.Domain.Stores.Bid;

import java.util.HashMap;
import java.util.Map;

public class BidForm {
    public String productName;
    public String storeName;
    public double amount;
    public String biddersName;
    public int bidId;
    public Map<String, Boolean> awaitingApprove;
    public boolean rejected;
    public boolean approvedByBidder;

    public BidForm(Bid bid) {
        this.productName = bid.getProductName();
        this.amount = bid.getPrice();
        this.biddersName = bid.getBidderName();
        this.bidId = bid.getId();
        this.awaitingApprove = bid.getAwaitingApprove();
        this.rejected = bid.getRejected();
        this.storeName = bid.getStoreName();
        this.approvedByBidder = bid.getApprovedByBidder();
    }


}
