package com.example.domain;

import lombok.Data;

@Data
public class RolePay {
    private int id;
    private String employeeAuthoritie;
    private double pay;
    private Double oneDayDeduction;
}
