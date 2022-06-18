package com.workshop.ETrade.Controller.Forms;

import java.util.Map;

public class OwnerWaitingForApproveForm {
    public Map<String, Boolean> waiting;
    public boolean isRejected;
    public boolean isAccepted;

    public OwnerWaitingForApproveForm(Map<String, Boolean> waiting, boolean isRejected, boolean isAccepted) {
        this.waiting = waiting;
        this.isAccepted = isAccepted;
        this.isRejected = isRejected;
    }

}
