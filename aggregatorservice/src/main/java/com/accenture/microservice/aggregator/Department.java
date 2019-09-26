package com.accenture.microservice.aggregator;

import org.springframework.stereotype.Component;

import java.io.IOException;


public interface Department {

    public String getDepartmentInfo() throws IOException;
}
