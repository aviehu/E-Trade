package com.workshop.ETrade.Domain.Users;

public class TotalTraffic {
    int guests;
    int simpleMembers;
    int storeManagers;
    int storeOwners;
    int sysManagers;

    public TotalTraffic() {
        guests = 0;
        simpleMembers = 0;
        storeManagers = 0;
        storeOwners = 0;
        sysManagers = 0;
    }
    public TotalTraffic addTraffic(TrafficInfo trafficInfo){
        guests+= trafficInfo.getGuests().size();
        simpleMembers+= trafficInfo.getSimpleMembers().size();
        storeManagers += trafficInfo.getManagersMembers().size();
        storeOwners += trafficInfo.getOwnersMembers().size();
        sysManagers += trafficInfo.getSysManagers().size();
        return this;
    }

    public int getGuests() {
        return guests;
    }

    public int getSimpleMembers() {
        return simpleMembers;
    }

    public int getStoreManagers() {
        return storeManagers;
    }

    public int getStoreOwners() {
        return storeOwners;
    }

    public int getSysManagers() {
        return sysManagers;
    }
}
