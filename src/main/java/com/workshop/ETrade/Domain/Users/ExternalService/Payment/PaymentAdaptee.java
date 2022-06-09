package com.workshop.ETrade.Domain.Users.ExternalService.Payment;


import com.workshop.ETrade.Domain.Users.ExternalService.HttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;

import java.net.HttpURLConnection;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentAdaptee {
    private HttpClient httpClient;

    public PaymentAdaptee(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public int payment(String cardNum, int month, int year, String holder, int cvv, int id){
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("action_type", "pay"));
        urlParameters.add(new BasicNameValuePair("card_number", cardNum));
        urlParameters.add(new BasicNameValuePair("month", String.valueOf(month)));
        urlParameters.add(new BasicNameValuePair("year", String.valueOf(year)));
        urlParameters.add(new BasicNameValuePair("holder", holder));
        urlParameters.add(new BasicNameValuePair("ccv", String.valueOf(cvv)));
        urlParameters.add(new BasicNameValuePair("id",String.valueOf(id)));
        String result = this.httpClient.start(urlParameters);

        return Integer.parseInt(result);
    }
    public boolean canPay(int cardFrom, LocalTime expDate, int cvv,double price){
        return false;
    }
    public int getBalance(int card, LocalTime exp, int cvv) {
        return 0;
    }

    public int cancelPayment(int transId) {
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("action_type", "cancel_payment"));
        urlParameters.add(new BasicNameValuePair("transaction_id", String.valueOf(transId)));
        String result = this.httpClient.start(urlParameters);

        return Integer.parseInt(result);
    }
    public boolean handShake(){
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("action_type", "handshake"));
        String result = this.httpClient.start(urlParameters);
        return result.equals("OK");
    }
}
