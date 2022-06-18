package com.workshop.ETrade.Persistance.Users;

import com.workshop.ETrade.Domain.Users.Date;
import com.workshop.ETrade.Domain.Users.TrafficInfo;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TrafficDTO {
    @Id
    private Date date;
    private Set<String> guests;
    private Set<String> simpleMembers;
    private Set<String> managersMembers;
    private Set<String> ownersMembers;
    private Set<String> sysManagers;

    public TrafficDTO() {
    }

    public TrafficDTO(Set<String> guests, Set<String> simpleMembers, Set<String> managersMembers, Set<String> ownersMembers, Set<String> sysManagers, Date date) {
        this.guests = guests;
        this.simpleMembers = simpleMembers;
        this.managersMembers = managersMembers;
        this.ownersMembers = ownersMembers;
        this.sysManagers = sysManagers;
        this.date = date;
    }
    public TrafficDTO(TrafficInfo trafficInfo){
        LocalDate curDate = trafficInfo.getDate();
        this.date = new Date(curDate.getDayOfMonth(),curDate.getMonthValue(),curDate.getYear());
        this.guests = trafficInfo.getGuests();
        this.simpleMembers = trafficInfo.getSimpleMembers();
        this.managersMembers = trafficInfo.getManagersMembers();
        this.ownersMembers = trafficInfo.getOwnersMembers();
        this.sysManagers = trafficInfo.getSysManagers();
    }

    public Date getDate() {
        return date;
    }

    public Set<String> getGuests() {
        return guests;
    }

    public Set<String> getSimpleMembers() {
        return simpleMembers;
    }

    public Set<String> getManagersMembers() {
        return managersMembers;
    }

    public Set<String> getOwnersMembers() {
        return ownersMembers;
    }

    public Set<String> getSysManagers() {
        return sysManagers;
    }
}
