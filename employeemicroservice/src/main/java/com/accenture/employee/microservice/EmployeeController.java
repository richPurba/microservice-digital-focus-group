package com.accenture.employee.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employee")
    public String getEmployeeInformation(){
        return "Siu Hin Ng";
    }
}
