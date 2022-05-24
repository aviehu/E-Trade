package com.workshop.ETrade.Domain;

public interface Observer {
    public void update(String message,String from);
}
