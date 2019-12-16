package com.example.domain;

import lombok.Data;

@Data
public class TransactionDetails {
    private int id;
    private int tradeMember;
    private Double transactionRealPrice;
    private int tradeNumber;
    private String transcationId;
}
