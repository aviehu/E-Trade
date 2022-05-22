package com.workshop.ETrade.Tests.Bridge;

import com.workshop.ETrade.Service.ServiceInterface;
import com.workshop.ETrade.Service.SystemService;

public class Driver {

    public ServiceInterface getService(){
        SystemServiceProxy proxy = new SystemServiceProxy();
        proxy.setReal(new SystemService());
        return proxy;
    }
}
