package com.example.service.serviceImpl;

import com.example.dao.MemberDao;
import com.example.dao.MemberDiscountDao;
import com.example.dao.TransactionRecordDao;
import com.example.domain.*;
import com.example.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionRecordServiceImpl implements TransactionRecordService {

    @Autowired(required = false)
    TransactionRecordDao transactionRecordDao;

    @Autowired(required = false)
    MemberDao memberDao;
    @Autowired(required = false)
    MemberDiscountDao memberDiscountDao;


    @Override
    public Boolean deleteTransactionRecordByTransactionId(String transactionId) {

        Boolean b=transactionRecordDao.deleteTransactionRecordByTransactionId(transactionId);
        return b;
    }

    /**
     * transactionDetails：商品id，商品数量
     * membershipId:会员id
     * @param transactionDetails
     * @param membershipId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public TransactionRecord insertTransactionRecord(ArrayList<TransactionDetails> transactionDetails, int membershipId) {
        //产生交易编号
        String transactionId=String.valueOf(UUID.randomUUID());


        //计算该顾客折扣等级
        Member member=memberDao.findMemberByMemberId(membershipId);
        String membershipLevel=member.getMembershipLevel(); //顾客会员等级
        Double membershipDiscount=1.0; //游客，顾客折扣值
        if (!membershipLevel.equals("游客")){
            membershipDiscount=memberDiscountDao.findMemberDiscountByMembershipLevel(membershipLevel);
        }


        //计算商品总额
        //总额=商品单价*商品数量*会员折扣值
        Double transactionTotalPrice=0.0;
        //便利商品列表，计算总额
        for (int i = 0; i < transactionDetails.size(); i++) {
            int tradeMember=transactionDetails.get(i).getTradeMember(); //商品id
            int tradeNumber=transactionDetails.get(i).getTradeNumber(); //商品数量
            Trade trade=transactionRecordDao.findTradeByTradeId(tradeMember); //获取该商品信息
            Double price=trade.getPrice(); //商品单价
            transactionDetails.get(i).setPrice(price); //设置商品原价
            transactionDetails.get(i).setTradeName(trade.getTradeName());
            int whileMember=trade.getWhileMember(); //商品是否会员
            //商品实付单价
            Double transactionRealPrice;
            if (whileMember!=1){
                transactionRealPrice=price;
                transactionDetails.get(i).setTransactionRealPrice(transactionRealPrice); //设置商品真实价格
                transactionTotalPrice+=tradeNumber*transactionRealPrice;
            }else if (whileMember==1){
                transactionRealPrice=price*membershipDiscount;
                transactionDetails.get(i).setTransactionRealPrice(transactionRealPrice); //设置商品真实价格
                transactionTotalPrice+=tradeNumber*transactionRealPrice;
            }
        }

        //订单产生时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String transactionTime=String.valueOf(simpleDateFormat.format(date));

        //数据库插入一条总的记录
        Boolean b=transactionRecordDao.insertTransactionRecord(transactionId,transactionTime,transactionTotalPrice);


        //数据库插入详细记录
        for (int i = 0; i < transactionDetails.size(); i++) {
            transactionDetails.get(i).setTranscationId(transactionId);
            Boolean b2=transactionRecordDao.insertTransactionDetails(transactionDetails.get(i));

        }

        //像前端返回账单详细和总额
        TransactionRecord transactionRecord=new TransactionRecord();
        transactionRecord.setTransactionId(transactionId);
        transactionRecord.setTransactionTime(transactionTime);
        transactionRecord.setTransactionTotalPrice(transactionTotalPrice);
        transactionRecord.setTransactionDetails(transactionDetails);

        return transactionRecord;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    @Cacheable(value = "redisCache",key = "'redis_transactionRecordNumber'")
    public int findTransactionRecordNumber() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String transactionTime=String.valueOf(simpleDateFormat.format(date));
        int transactionRecordNumber=transactionRecordDao.findTransactionRecordNumber(transactionTime);
        return transactionRecordNumber;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    @Cacheable(value = "redisCache",key = "'redis_totalPriceEveryDay'")
    public Double findTotalPriceEveryDay() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String transactionTime=String.valueOf(simpleDateFormat.format(date));
        Double totalPriceEveryDay=0.0;
        ArrayList<TransactionRecord> transactionRecords= (ArrayList<TransactionRecord>) transactionRecordDao.findAllTransactionRecord(transactionTime);
        for (int i = 0; i < transactionRecords.size(); i++) {
            totalPriceEveryDay+=transactionRecords.get(i).getTransactionTotalPrice();
        }
        return totalPriceEveryDay;
    }

    /**
     * 查找每日利润
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    @Cacheable(value = "redisCache",key = "'redis_totalProfit'") //springRedis缓存注解
    public Double findTotalProfitEveryDay() {
        //今天时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String transactionTime=String.valueOf(simpleDateFormat.format(date));
        ArrayList<TransactionRecord> transactionRecords= (ArrayList<TransactionRecord>) transactionRecordDao.findAllTransactionRecord(transactionTime);

        Double totalProfit=0.0;
        //计算今日所有商品成本价
        for (int i = 0; i < transactionRecords.size(); i++) {
            ArrayList<TransactionDetails> transactionDetails= (ArrayList<TransactionDetails>) transactionRecords.get(i).getTransactionDetails();
            for (int j = 0; j < transactionDetails.size(); j++) {
                //商品id
                int tradeMember=transactionDetails.get(j).getTradeMember();
                //商品数量
                int tradeNumber=transactionDetails.get(j).getTradeNumber();
                //商品真实价格
                Double transactionRealPrice=transactionDetails.get(j).getTransactionRealPrice();
                //商品进价
                Trade trade=transactionRecordDao.findTradeByTradeId(tradeMember);
                Double purhasePrice=trade.getPurhasePrice();
                //商品利润
                totalProfit+=((transactionRealPrice-purhasePrice)*tradeNumber);
               // System.out.println(tradeMember+"|"+tradeNumber+"|"+(transactionRealPrice-purhasePrice)+"|"+totalProfit);
            }
        }

        return totalProfit;
    }


}
