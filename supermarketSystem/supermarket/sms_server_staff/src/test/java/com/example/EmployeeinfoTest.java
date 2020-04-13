package com.example;

import com.example.domain.EmployeeInformation;
import com.example.service.service_impl.EmployeeInformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class EmployeeinfoTest {

//    @Autowired
//    EmployeeInformationServiceImpl employeeInformationService;
//
//    @Test
//    public void findEmployeeInformationByAnyone(){
//        ArrayList<EmployeeInformation> employeeInformations=employeeInformationService.findEmployeeInformationByAnyone("100");
//        System.out.println("测试1：");
//        for (EmployeeInformation e:employeeInformations
//             ) {
//            System.out.println(e);
//        }
//    }
//
//    @Test
//    public void findAllEmployeeInformation(){
//        ArrayList<EmployeeInformation> employeeInformations=employeeInformationService.findAllEmployeeInformation();
//        System.out.println("测试2：");
//        for (EmployeeInformation e:employeeInformations
//        ) {
//            System.out.println(e);
//        }
//    }
//    @Test
//    public void updateEmployeePassWord(){
//        System.out.println("测试4：");
//        Boolean b=employeeInformationService.updateEmployeePassWord(1003,"123456");
//        System.out.println(b);
//    }
}
