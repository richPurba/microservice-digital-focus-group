package com.accenture.microservice.aggregator;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class ProductInventoryImpl implements ProductInventory {

    public int getInventory() throws IOException{
        String response = "0";
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpGet = new HttpGet("http://localhost:51516/inventories");
            try(CloseableHttpResponse httpResponse = httpClient.execute(httpGet)){
                response = EntityUtils.toString(httpResponse.getEntity());
            }
        }catch(IOException e){
            throw new IOException("unhdandled httpResponse "+ e);
        }
        return Integer.parseInt(response);
    }
}
