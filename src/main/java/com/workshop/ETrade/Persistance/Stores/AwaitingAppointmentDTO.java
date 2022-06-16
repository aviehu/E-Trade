package com.workshop.ETrade.Persistance.Stores;

import java.util.Map;

public class AwaitingAppointmentDTO {
    public String awaitingUser;
    public Map<String, Boolean> approvedBy;

    public AwaitingAppointmentDTO(String awaitingUser, Map<String, Boolean> approvedBy) {
        this.awaitingUser = awaitingUser;
        this.approvedBy = approvedBy;
    }


}
