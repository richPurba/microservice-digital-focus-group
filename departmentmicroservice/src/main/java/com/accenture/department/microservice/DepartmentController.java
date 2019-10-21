package com.accenture.department.microservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @HystrixCommand(fallbackMethod = "someFallBackMethod", commandKey = "DepartmentCommandKey", groupKey = "DepartmentGroup")
    @GetMapping("/department")
    public String getDepartmentInfo(){
        Boolean checkRandom = RandomUtils.nextBoolean();
        if(checkRandom){
            return "Accenture Digital";
        } else
            { throw new RuntimeException(" A RuntimeException from Department ");
        }
    }

    public String someFallBackMethod()
    {
        return "fallbackmethod!!!";
    }
}
