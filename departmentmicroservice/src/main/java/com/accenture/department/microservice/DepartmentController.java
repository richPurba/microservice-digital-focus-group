package com.accenture.department.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @GetMapping("/department")
    public String getDepartmentInfo(){
        return "Accenture Digital";
    }
}
