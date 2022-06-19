package com.workshop.ETrade.Domain.Users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrafficInfo {

    private LocalDate date;
    private Set<String> guests;
    private Set<String> simpleMembers;
    private Set<String> managersMembers;
    private Set<String> ownersMembers;
    private Set<String> sysManagers;
    public TrafficInfo() {
        this.date = LocalDate.now();
        this.guests = new HashSet<>();
        this.simpleMembers =new HashSet<>();
        this.managersMembers = new HashSet<>();
        this.ownersMembers = new HashSet<>();
        this.sysManagers = new HashSet<>();
    }

    public TrafficInfo(Set<String> guests, Set<String> simpleMembers, Set<String> managersMembers, Set<String> ownersMembers, Set<String> sysManagers, LocalDate date) {
        this.guests = guests;
        this.simpleMembers = simpleMembers;
        this.managersMembers = managersMembers;
        this.ownersMembers = ownersMembers;
        this.sysManagers = sysManagers;
        this.date = date;
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

    public LocalDate getDate() {
        return this.date;
    }

}
