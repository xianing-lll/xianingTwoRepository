package com.example.domain;

import lombok.Data;

@Data
public class TransactionDetails {
    private int id;
    private int tradeMember; //商品编号
    private String tradeName;
    private double price;//商品售价
    private Double transactionRealPrice;  //商品实际支付价格
    private int tradeNumber; //商品数量
    private String transcationId;  //交易编号
}
