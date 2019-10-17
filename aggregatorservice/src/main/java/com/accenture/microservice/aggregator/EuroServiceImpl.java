package com.accenture.microservice.aggregator;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EuroServiceImpl implements EuroService {

    @Override
    public String getEuro() {
        String result="";
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet get = new HttpGet("http://localhost:3321/euro");
            CloseableHttpResponse response = client.execute(get);
            result = EntityUtils.toString(response.getEntity());
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
