package com.example.service;

import com.example.domain.EmployeeInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("staff-service")
public interface EmployeeInformationService {
    @RequestMapping("/findEmployeeById")
    @ResponseBody
    public EmployeeInformation findEmployeeById(@RequestParam("employeeId") int employeeId);
}
