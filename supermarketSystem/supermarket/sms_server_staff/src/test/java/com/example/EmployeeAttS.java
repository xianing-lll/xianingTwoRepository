package com.example;

import com.example.domain.EmployeeAttendance;
import com.example.domain.EmployeeInformation;
import com.example.service.service_impl.EmployeeAttendanceServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class EmployeeAttS {
    @Autowired
    EmployeeAttendanceServiceImpl employeeAttendanceService;

    @Test
    public void findAllEmployeeSalary(){
        ArrayList<EmployeeInformation> employeeInformations=employeeAttendanceService.findAllEmployeeSalary("2019-11");
        System.out.println("测试1：");
        for (EmployeeInformation employeeInformation:employeeInformations
             ) {
            System.out.println(employeeInformation);
        }
    }

    @Test
    public void findEmployeeSalaryByEmployeeId(){
        EmployeeInformation employeeInformations=employeeAttendanceService.findEmployeeSalaryByEmployeeId(1005,"2019-11");
        System.out.println("测试1：");
        System.out.println(employeeInformations);
    }
    @Test
    public void findAllEmployeeSalaryByEmployee(){
        ArrayList<EmployeeInformation> employeeInformations=employeeAttendanceService.findAllEmployeeSalary("2019-11");
        System.out.println("测试1：");
        for (EmployeeInformation e:employeeInformations
             ) {
            System.out.println(e);
        }

    }

}
