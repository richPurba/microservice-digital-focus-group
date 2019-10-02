package com.accenture.microservice.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class Aggregator {

    @Autowired
    private Department department;

    @Resource
    private Employee employee;

    @Resource
    private Euro euro;

    @GetMapping("/salary")
    public Salary aggregating(){
        Salary salary = new Salary();
        try{
            salary.setDepartment(department.getDepartmentInfo());
            salary.setEmployee(employee .getEmployee());
            salary.setEmployee(euro.getEuro());
        }catch (IOException e){
            e.printStackTrace();
        }

        return salary;
    }
}
