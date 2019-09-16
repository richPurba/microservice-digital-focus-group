package com.accenture.microservice.aggregator;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class ProductInformationImpl implements ProductInformation{

    @Override
    public String getProductInformation() throws IOException{
        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpGet = new HttpGet("http://localhost:51515/information");
            try(CloseableHttpResponse response = httpClient.execute(httpGet)){
                result = EntityUtils.toString(response.getEntity());
            }

        }catch (IOException e){
            throw new IOException("IOException is caught "+e);
        }
        return result;
    }
}
