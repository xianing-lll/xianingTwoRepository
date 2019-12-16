package com.example.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class EmployeeInformation {
    private int employeeId; //员工id（登陆账号）
    private String employeePassWord; //员工密码
    private String employeeName;  //用户姓名
    private String employeeMobile;  //手机号
    private String employeeIdCard; //员工省份证号
    private String employeeEntryTime; //员工入职时间
    private Integer employeeRegular; //是否正式员工
    private List<EmployeeAuth> employeeAuths; //角色信息
    private EmployeeAttendance employeeAttendance; //员工考勤表
    private Salary salary;
}
