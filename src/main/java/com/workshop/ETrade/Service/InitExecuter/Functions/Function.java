package com.workshop.ETrade.Service.InitExecuter.Functions;

import com.workshop.ETrade.Service.ResultPackge.Result;
import com.workshop.ETrade.Service.ServiceInterface;

public abstract class Function<T> {
    protected ServiceInterface service;

    public Function() {
    }

    public void setService(ServiceInterface service) {
        this.service = service;
    }

    public abstract Result<T> execute();

}