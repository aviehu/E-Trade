package com.workshop.ETrade.Service.InitExecuter;

import com.workshop.ETrade.Domain.Users.ExternalService.Payment.IPay;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.ISupply;

public class Configuration {
    Class<? extends IPay> PaymentSystem;
    Class<? extends ISupply> SupplyingSystem;
    String persistence_unit;
    //Boolean shouldPersist;

    public Configuration() {
    }



    public String getPersistence_unit() {
        return persistence_unit;
    }

    public void setPersistence_unit(String persistence_unit) {
        this.persistence_unit = persistence_unit;
    }

//    public Boolean getShouldPersist() {
//        return shouldPersist;
//    }
//
//    public void setShouldPersist(Boolean shouldPersist) {
//        this.shouldPersist = shouldPersist;
//    }

    public Class<? extends IPay> getPaymentSystem() {
        return PaymentSystem;
    }

    public void setPaymentSystem(Class<? extends IPay> paymentSystem) {
        PaymentSystem = paymentSystem;
    }

    public Class<? extends ISupply> getSupplyingSystem() {
        return SupplyingSystem;
    }

    public void setSupplyingSystem(Class<? extends ISupply> supplyingSystem) {
        SupplyingSystem = supplyingSystem;
    }

}
