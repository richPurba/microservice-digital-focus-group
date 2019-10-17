package com.accenture.microservice.aggregator;

import org.springframework.stereotype.Component;

import java.io.IOException;


public interface DepartmentService {

    public String getDepartmentInfo() throws IOException;
}
