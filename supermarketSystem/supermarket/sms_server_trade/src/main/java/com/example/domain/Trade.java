package com.example.domain;

import lombok.Data;

@Data
public class Trade {
    private int tradeMember;//商品编号，（无符号整型）
    private String tradeName;//商品名称
    private String tradeCategory;//商品种类
    private double purhasePrice;//商品进价
    private double price;//商品售价
    private int tradeStock;//商品库存数量
    private String manufactureDate;//商品生产日期
    private long qualityGuaranteePeriod;//商品保质期
    private Integer whileMember;//是否会员
    private int purchaseId;//进货批次
    private int remainingDays;//距离国企剩余时间
    private String expirationTime; //商品过期时间


}
