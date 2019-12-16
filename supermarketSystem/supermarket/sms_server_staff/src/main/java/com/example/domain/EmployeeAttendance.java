package com.example.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeAttendance implements Serializable {
    private int attendanceId;
    private int employeeId;
    private double leaveDays;
//    private  double salary;
    private String workTime;
    private EmployeeInformation employeeInformation;
}
