package com.workshop.ETrade.Domain.Users.ExternalService;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {
    String url;

    public HttpClient() {
        this.url = "https://cs-bgu-wsep.herokuapp.com/";;
    }

    public String getUrl() {
        return url;
    }

    private static String sendPOST(String url,List<NameValuePair> urlParameters) throws IOException {

        String result = "";
        HttpPost post = new HttpPost(url);


        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            result = EntityUtils.toString(response.getEntity());
        }
        return result;
    }

    public String start(List<NameValuePair> urlParameters) {
        try {
            String result = sendPOST(this.url,urlParameters);
            //System.out.println(result);
            return result;
        } catch (IOException e) {
           return e.getMessage();
        }
    }
}
