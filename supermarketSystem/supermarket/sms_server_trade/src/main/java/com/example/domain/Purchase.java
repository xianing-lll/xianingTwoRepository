package com.example.domain;

import lombok.Data;

@Data
public class Purchase {
    private int purchaseId; //进货批次id
    private String purchaseDetailed; //进货i详细内容
    private double purchaseTotalPrice;//进货总额
    private String personInCharge; //负责人
    private String purchaseTime; //进货时间
}
