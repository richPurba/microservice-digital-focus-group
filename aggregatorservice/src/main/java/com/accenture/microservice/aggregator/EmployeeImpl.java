package com.accenture.microservice.aggregator;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmployeeImpl implements Employee {

    @Override
    public String getEmployee() {
        String result = "";
        try(CloseableHttpClient httpClient = HttpClients.createDefault()){
            HttpGet response = new HttpGet("http://localhost:51516/employee");
            CloseableHttpResponse resp = httpClient.execute(response);
            result = EntityUtils.toString(resp.getEntity());
        } catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
