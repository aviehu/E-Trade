package com.workshop.ETrade.Service.InitExecuter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.workshop.ETrade.AllRepos;
import com.workshop.ETrade.Domain.Users.ExternalService.ExtSysController;
import com.workshop.ETrade.Domain.Users.ExternalService.Payment.PaymentAdapter;
import com.workshop.ETrade.Domain.Users.ExternalService.Supply.SupplyAdapter;
import com.workshop.ETrade.Service.InitExecuter.Configuration;
import com.workshop.ETrade.Service.ResultPackge.Result;
import com.workshop.ETrade.Service.SystemService;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class LoadServiceFromInitState {

    public static SystemService loadFromFile(String path, SystemService service ) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Invoker<?>[] ins = objectMapper.readValue(new File(path), Invoker[].class);
            int command_num = 0;
            for (Invoker<?> inv : ins) {
                try {
                    Result<?> res = inv.Invoke(service);
                    command_num++;
                }catch (Exception e){
                    command_num++;
                }
            }

        } catch (Exception e) {
            System.out.println("e");
        }
        return service;
    }

    public static void loadConfig(String path) throws Exception {

        // File f = new File(path);
        try {
            String json = new String(Files.readAllBytes(Paths.get(path)));
            JSONObject jsonObject = new JSONObject(json);
            String test = jsonObject.getString("isTest");
            if(test.equals("true")){
                AllRepos.setIsTest(true);
                ExtSysController.getInstance().setPayment(false);
                ExtSysController.getInstance().setSupply(false);
            }else{
                AllRepos.setIsTest(false);
            }



        } catch (Exception e) {
            System.out.println("e");
        }
    }

//        public static void main(String[] args) throws Exception {
////        ObjectMapper objectMapper = new ObjectMapper();
//        SystemService s = new SystemService();
////        c.setPersistence_unit("Market");
////        //c.setShouldPersist(true);
////        c.setPaymentSystem(PaymentAdapter.class);
////        c.setSupplyingSystem(SupplyAdapter.class);
////        objectMapper.writeValue(new File("initState.json"), c);
//        loadFromFile("./initState.json", s);
//    }

}
