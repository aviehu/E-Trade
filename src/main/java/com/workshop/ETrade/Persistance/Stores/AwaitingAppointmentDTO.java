package com.workshop.ETrade.Persistance.Stores;

import java.util.Map;

public class AwaitingAppointmentDTO {
    public String awaitingUser;
    public Map<String, Boolean> approvedBy;

    public boolean isRejected;

    public AwaitingAppointmentDTO() {
    }

    public AwaitingAppointmentDTO(String awaitingUser, Map<String, Boolean> approvedBy, boolean isRejected) {
        this.awaitingUser = awaitingUser;
        this.approvedBy = approvedBy;
        this.isRejected = isRejected;
    }


}
