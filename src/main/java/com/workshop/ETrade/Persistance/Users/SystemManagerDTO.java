package com.workshop.ETrade.Persistance.Users;

import org.springframework.data.annotation.Id;

import java.util.List;

public class SystemManagerDTO {
    @Id
    private String systemManager;

    public SystemManagerDTO(String systemManagers) {
        this.systemManager = systemManagers;
    }

    public String getSystemManager() {
        return systemManager;
    }

    public void setSystemManager(String systemManagers) {
        this.systemManager = systemManagers;
    }
}
