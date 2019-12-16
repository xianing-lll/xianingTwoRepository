package com.example.domain;

import lombok.Data;

@Data
public class EmployeeAttendance {
    private int attendanceId;
    private int employeeId;
    private double leaveDays;
//    private  double salary;
    private String workTime;
    private EmployeeInformation employeeInformation;
}
