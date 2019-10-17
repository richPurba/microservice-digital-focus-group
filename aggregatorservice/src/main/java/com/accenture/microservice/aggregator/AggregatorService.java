package com.accenture.microservice.aggregator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class AggregatorService {

    @Autowired
    private DepartmentService departmentService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private EuroService euroService;

    @GetMapping("/salary")
    public Salary aggregating(){
        Salary salary = new Salary();
        try{
            salary.setDepartment(departmentService.getDepartmentInfo());
            salary.setEmployee(employeeService.getEmployee());
            salary.setEuro(euroService.getEuro());
        }catch (IOException e){
            e.printStackTrace();
        }

        return salary;
    }
}
