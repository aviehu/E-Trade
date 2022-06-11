package com.workshop.ETrade.Service.InitExecuter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.ETrade.Service.InitExecuter.Functions.Function;
import com.workshop.ETrade.Service.ResultPackge.Result;
import com.workshop.ETrade.Service.SystemService;

public class Invoker<T> {
    public Class<? extends Function<T>> function;
    public String params;  //JSON

    public Invoker(){}

    public Invoker(Class<? extends Function<T>> function, String params) {
        this.function = function;
        this.params = params;
    }

    public Result<T> Invoke(SystemService service) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Function<T> func1 = mapper.readValue(params, function);
        func1.setService(service);
        return func1.execute();
    }
}
