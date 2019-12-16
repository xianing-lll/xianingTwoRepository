package com.example.service;

import com.example.domain.TransactionDetails;
import com.example.domain.TransactionRecord;

import java.util.ArrayList;
import java.util.List;

public interface TransactionRecordService {
    /**
     * 支付失败，删除一条总记录和详细记录，由外键维护
     * @param transactionId
     * @return
     */
    public Boolean deleteTransactionRecordByTransactionId(String transactionId);

    /**
     * 插入一条顾客购买详细记录
     * @param transactionDetails
     * @param membershipId
     * @return
     */
    public TransactionRecord insertTransactionRecord(ArrayList<TransactionDetails> transactionDetails, int membershipId);

    /**
     * 查找每日销售单数据量
     * @param transactionTime
     * @return
     */
    public int findTransactionRecordNumber();

    /**
     * 计算每日销售额
     * @return
     */
    public Double findTotalPriceEveryDay();

    /**
     * 计算每日利润
     * @return
     */
    public Double findTotalProfitEveryDay();
}

