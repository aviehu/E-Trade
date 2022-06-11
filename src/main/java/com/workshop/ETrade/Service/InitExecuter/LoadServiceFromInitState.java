package com.workshop.ETrade.Service.InitExecuter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdapter;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdapter;
import com.workshop.ETrade.Service.InitExecuter.Configuration;
import com.workshop.ETrade.Service.ResultPackge.Result;
import com.workshop.ETrade.Service.SystemService;

import java.io.File;
import java.nio.file.Paths;

public class LoadServiceFromInitState {

    public static SystemService loadFromFile(String path, SystemService service ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Invoker<?>[] ins = objectMapper.readValue(new File(path), Invoker[].class);
            int command_num = 0;
            for(Invoker<?> inv : ins) {
                Result<?> res = inv.Invoke(service);
                if (!res.isSuccess())
                    throw new IllegalArgumentException(String.format("Illegal commands. failed at command %d in file %s", command_num, path));
                command_num++;
            }
        }catch (Exception ex){
            System.out.println(ex.getCause());
        }
        return service;
    }

    public static void main(String[] args) throws Exception {
//        ObjectMapper objectMapper = new ObjectMapper();
        SystemService s = new SystemService();
//        c.setPersistence_unit("Market");
//        //c.setShouldPersist(true);
//        c.setPaymentSystem(PaymentAdapter.class);
//        c.setSupplyingSystem(SupplyAdapter.class);
//        objectMapper.writeValue(new File("initState.json"), c);
        loadFromFile("./initState.json", s);
    }

}
