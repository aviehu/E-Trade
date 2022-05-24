package com.workshop.ETrade.Domain;

import com.workshop.ETrade.Domain.Users.Users.Member;

import java.lang.management.ManagementPermission;

public interface Observable {
    public void attach(Member userName);
    public void detach(Member userName);
    public void notifySubscribers(String message,String from);
}
