package com.workshop.ETrade.Domain.Users.ExternalService.Supply;

import com.workshop.ETrade.Domain.Users.ExternalService.HttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class SupplyAdaptee {
    HttpClient httpClient;

    public SupplyAdaptee(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public int supply(String name, String street, String city, String country, int zip){
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("action_type", "supply"));
        urlParameters.add(new BasicNameValuePair("name", name));
        urlParameters.add(new BasicNameValuePair("address", street));
        urlParameters.add(new BasicNameValuePair("city", city));
        urlParameters.add(new BasicNameValuePair("country", country));
        urlParameters.add(new BasicNameValuePair("zip", String.valueOf(zip)));
        String result = this.httpClient.start(urlParameters);



        return Integer.parseInt(result);

    }

    public int cancelSupply(int transId) {
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("action_type", "cancel_supply"));
        urlParameters.add(new BasicNameValuePair("transaction_id",String.valueOf(transId)));
        String result = httpClient.start(urlParameters);
        return Integer.parseInt(result);
    }
    public boolean handShake(){
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("action_type", "handshake"));
        String result = this.httpClient.start(urlParameters);
        return result.equals("OK");
    }
}
