package com.example.domain;

import lombok.Data;

@Data
public class MemberDiscount {
    private  int discountId;
    private  String membershipLevel;
    private  double membershipDiscount;
}
