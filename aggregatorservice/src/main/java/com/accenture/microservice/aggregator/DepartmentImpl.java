package com.accenture.microservice.aggregator;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DepartmentImpl implements Department {

    @Override
    public String getDepartmentInfo() throws IOException{
        String result = "";
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet httpget = new HttpGet("http://localhost:51515/department");
            CloseableHttpResponse response = httpClient.execute(httpget);
            result = EntityUtils.toString(response.getEntity());
        } catch(IOException e){
            throw new IOException("Exception is thrown");
        }
        return result;
    }
}
