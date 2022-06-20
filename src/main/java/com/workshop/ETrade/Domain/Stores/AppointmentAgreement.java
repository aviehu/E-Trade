package com.workshop.ETrade.Domain.Stores;

import java.util.Map;

public class AppointmentAgreement {

    private Map<String, Boolean> awaitingApproval;
    private String mainOwner;

    private boolean isRejected;

    private boolean isAccepted;

    public AppointmentAgreement(String mainOwner,Map<String, Boolean> awaitingApproval, boolean isRejected) {
        this.awaitingApproval = awaitingApproval;
        this.isRejected = isRejected;
        this.mainOwner = mainOwner;
    }


    public boolean isApproved() {
        for(String owner : awaitingApproval.keySet()) {
            if(!awaitingApproval.get(owner)) {
                return false;
            }
        }
        return true;
    }

    public String approve(String approvedBy, boolean approve) {
        if(!approve) {
            isRejected = true;
        } else  {
            if(!awaitingApproval.containsKey(approvedBy)) {
                return approvedBy + " is not on the waiting list";
            }
            awaitingApproval.computeIfPresent(approvedBy, (K,V) -> V = true);
            if(isApproved()) {
                return approvedBy + " is now a store owner";
            } else {
                return approvedBy + " is still waiting for more owners to approve";
            }
        }
        return "approval of " + approve + " has been rejected";
    }

    public Map<String, Boolean> getWaiting() {
        return awaitingApproval;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public String getMainOwner() {
        return mainOwner;
    }
}
