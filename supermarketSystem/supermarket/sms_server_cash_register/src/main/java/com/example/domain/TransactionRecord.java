package com.example.domain;

import lombok.Data;

import java.util.List;

@Data
public class TransactionRecord {
    private String transactionId; //交易编号
    private String transactionTime;  //交易时间
    private Double transactionTotalPrice;  //交易总额
    private int membershipId; //顾客id,游客为1，其他则为会员id号
    List<TransactionDetails> transactionDetails;
}
