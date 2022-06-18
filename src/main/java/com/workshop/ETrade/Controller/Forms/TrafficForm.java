package com.workshop.ETrade.Controller.Forms;

import com.workshop.ETrade.Domain.Users.TrafficInfo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TrafficForm  {
    private LocalDate date;
    private Set<String> guests;
    private Set<String> simpleMembers;
    private Set<String> managersMembers;
    private Set<String> ownersMembers;
    private Set<String> sysManagers;
    public TrafficForm() {
        this.guests = new HashSet<>();
        this.simpleMembers =new HashSet<>();
        this.managersMembers = new HashSet<>();
        this.ownersMembers = new HashSet<>();
        this.sysManagers = new HashSet<>();
    }

    public TrafficForm(Set<String> guests, Set<String> simpleMembers, Set<String> managersMembers, Set<String> ownersMembers, Set<String> sysManagers) {
        this.guests = guests;
        this.simpleMembers = simpleMembers;
        this.managersMembers = managersMembers;
        this.ownersMembers = ownersMembers;
        this.sysManagers = sysManagers;
    }
    public TrafficForm(TrafficInfo trafficInfo){
        this.guests = trafficInfo.getGuests();
        this.simpleMembers = trafficInfo.getSimpleMembers();
        this.managersMembers = trafficInfo.getManagersMembers();
        this.ownersMembers = trafficInfo.getOwnersMembers();
        this.sysManagers = trafficInfo.getSysManagers();
    }

    public Set<String> getGuests() {
        return guests;
    }

    public void incGuests(String guests) {
        this.guests.add(guests);
    }

    public Set<String> getSimpleMembers() {
        return simpleMembers;
    }

    public void incSimpleMembers(String simpleMembers) {
        this.simpleMembers.add(simpleMembers);
    }

    public Set<String> getManagersMembers() {
        return managersMembers;
    }

    public void incStoreManagersMembers(String managersMembers) {
        this.managersMembers.add(managersMembers);
    }

    public Set<String> getOwnersMembers() {
        return ownersMembers;
    }

    public void incOwnersMembers(String ownersMembers) {
        this.ownersMembers.add(ownersMembers);
    }

    public Set<String> getSysManagers() {
        return sysManagers;
    }

    public void incSysManagers(String sysManagers) {
        this.sysManagers.add(sysManagers);
    }

}

