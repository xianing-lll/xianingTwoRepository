package com.example.domain;

import lombok.Data;

@Data
public class TransactionRecord {
    private String transactionId;
    private String transactionTime;
    private double transactionTotalPrice;
}
