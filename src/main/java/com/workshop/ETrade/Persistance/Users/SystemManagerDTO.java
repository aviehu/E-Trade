package com.workshop.ETrade.Persistance.Users;

import org.springframework.data.annotation.Id;

import java.util.List;

public class SystemManagerDTO {
    @Id
    public String systemManager;
    public String getSystemManager() {
        return systemManager;
    }

    public SystemManagerDTO(String systemManager) {
        this.systemManager = systemManager;
    }



    public void setSystemManager(String systemManager) {
        this.systemManager = systemManager;
    }
}
