package com.example.domain;

import lombok.Data;

@Data
public class Member {
    private int membershipId;
    private String membershipName;
    private String membershipPhone;
    private String membershipLevel;
    private int membershipScore;
    private double membershipBalance;
}
