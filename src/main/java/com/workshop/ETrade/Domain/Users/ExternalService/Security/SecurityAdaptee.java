package com.workshop.ETrade.Domain.Users.ExternalService.Security;

public class SecurityAdaptee {
    public String encode(String password){
        return password;
    }
    public String decode(String password){
        return password;
    }
}
