package com.example.domain;

import lombok.Data;

@Data
public class EmployeeAuth {
    private int authId; //用户_角色id
    private int employeeId; //用户名（t_user外键）
    private String employeeAuthoritie;  //角色
}
