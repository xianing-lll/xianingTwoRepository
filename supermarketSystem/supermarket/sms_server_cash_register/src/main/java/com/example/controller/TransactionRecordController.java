package com.example.controller;

import com.example.domain.TransactionDetails;
import com.example.domain.TransactionRecord;
import com.example.service.serviceImpl.TransactionRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TransactionRecordController {

    @Autowired
    TransactionRecordServiceImpl transactionRecordService;

    /**
     * 支付失败，删除一条总记录和详细记录，由外键维护
     * @param transactionId
     * @return
     */
    @RequestMapping("/deleteTransactionRecordByTransactionId")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public Boolean deleteTransactionRecordByTransactionId(String transactionId){
        Boolean b=transactionRecordService.deleteTransactionRecordByTransactionId(transactionId);
        return b;
    }
    /**
     * 插入一条顾客购买详细记录
     * @param transactionDetailsArrayList
     * @param membershipId
     * @return
     */
    @RequestMapping("/insertTransactionRecord")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")
    public TransactionRecord insertTransactionRecord( @RequestBody TransactionRecord transactionRecord){
        ArrayList<TransactionDetails> transactionDetailsArrayList= (ArrayList<TransactionDetails>) transactionRecord.getTransactionDetails();
        TransactionRecord transactionRecord2=transactionRecordService.insertTransactionRecord(transactionDetailsArrayList,transactionRecord.getMembershipId());
        return transactionRecord2;
    }

    /**
     * 每日销售单数量
     * @return
     */
    @RequestMapping("/findTransactionRecordNumber")
    public int findTransactionRecordNumber() {
        int transactionRecordNumber=transactionRecordService.findTransactionRecordNumber();
        return transactionRecordNumber;
    }

    /**
     * 计算每日销售额
     * @return
     */
    @RequestMapping("/findTotalPriceEveryDay")
    public Double findTotalPriceEveryDay() {
       Double totalPriceEveryDay=transactionRecordService.findTotalPriceEveryDay();
       return totalPriceEveryDay;
    }

    /**
     * 根据当天日期计算当天利润
     * @return
     */
    @RequestMapping("/findTotalProfitEveryDay")
    public Double findTotalProfitEveryDay() {
        Double totalProfit=transactionRecordService.findTotalProfitEveryDay();
        return totalProfit;
    }
}
