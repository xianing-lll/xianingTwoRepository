package com.example.domain;

import lombok.Data;

@Data
public class Trade {
    private int tradeMember;
    private String tradeName;
    private String tradeCategory;
    private double purhasePrice;
    private double price;
    private int tradeStock;
    private String manufactureDate;
    private int qualityGuaranteePeriod;
    private Integer whileMember;

}
