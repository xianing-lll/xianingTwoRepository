package com.example.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
public class EmployeeAuth implements Serializable {
    private int authId; //用户_角色id
    private int employeeId; //用户名（t_user外键）
    private String employeeAuthoritie;  //角色
}
