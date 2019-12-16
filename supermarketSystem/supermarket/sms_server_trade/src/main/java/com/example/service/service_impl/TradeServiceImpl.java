package com.example.service.service_impl;

import com.example.dao.TradeDao;
import com.example.domain.Trade;
import com.example.service.service_interface.TradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired(required = false)
    TradeDao tradeDao;
    @Autowired
    TradeServiceImpl tradeService;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public ArrayList<Trade> findAllTrades() {
        ArrayList<Trade> trades= (ArrayList<Trade>) tradeDao.findAllTrades();
        return trades;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public ArrayList<Trade> findTradeByTradeCategory(String tradeCategory) {
        ArrayList<Trade> trades= (ArrayList<Trade>) tradeDao.findTradeByTradeCategory(tradeCategory);
        return trades;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 2)
    public ArrayList<Trade> findOverdueTradeByLevel(String Level) throws ParseException {
        ArrayList<Trade> trades= (ArrayList<Trade>) tradeDao.findAllTrades();//查询所有商品
        ArrayList<Trade> overdueTrades=new ArrayList<>(); //定义过期商品
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < trades.size(); i++) {
            String manufactureDate=trades.get(i).getManufactureDate();  //商品生产日期
            Date date=simpleDateFormat.parse(manufactureDate);
            Long manufactureDatetime=date.getTime();//得到生产日期毫秒数

            long qualityGuaranteePeriod=trades.get(i).getQualityGuaranteePeriod(); //获得保质期

            Date newdate=new Date();//现在时间


            //过期时间
            long qualityGuaranteePeriodTimeThree=qualityGuaranteePeriod*24*60*60*1000; //保质期毫秒数
            long newDateTimeThree=manufactureDatetime+qualityGuaranteePeriodTimeThree;//过期时间毫秒数
            Date overdueDateThree=new Date(newDateTimeThree);  //过期时间


            String expirationTime=simpleDateFormat.format(overdueDateThree);//过期年月日时间
            //（过期时间毫秒数-现在时间毫秒数）/24*60*60*1000=还剩多少天过期
            long mmNewDate=newdate.getTime();//现在时间毫秒数
            int remainingDays=0;
            if (mmNewDate<newDateTimeThree){
                remainingDays= (int) ((newDateTimeThree-mmNewDate)/(24*60*60*1000)); //剩余天数
            }

            //封装过期时间，和剩过期天数
            trades.get(i).setExpirationTime(expirationTime);
            trades.get(i).setRemainingDays(remainingDays);

            //二级预警时间
            long qualityGuaranteePeriodTimeTwo=qualityGuaranteePeriod*4/5*24*60*60*1000;
            long newDateTimeTwo=manufactureDatetime+qualityGuaranteePeriodTimeTwo;
            Date overdueDateTwo=new Date(newDateTimeTwo);  //过期时间
            //一级预警时间
            long qualityGuaranteePeriodTimeOne=qualityGuaranteePeriod*2/3*24*60*60*1000;
            long newDateTimeOne=manufactureDatetime+qualityGuaranteePeriodTimeOne;
            Date overdueDateOne=new Date(newDateTimeOne);  //过期时间


            if (Level.equals("一级预警")){
                if (newdate.after(overdueDateOne)&&newdate.before(overdueDateTwo)){
                    overdueTrades.add(trades.get(i));
                }
            }else if (Level.equals("二级预警")){
                if (newdate.after(overdueDateTwo)&&newdate.before(overdueDateThree)){
                    overdueTrades.add(trades.get(i));
                }
            }else if (Level.equals("过期商品")){
                if (newdate.after(overdueDateThree)){
                    overdueTrades.add(trades.get(i));
                }
            }
        }
        return overdueTrades;
    }

    @Override
    @Cacheable(value = "redisCache",key = "'redis_overCount'")
    public int findOverTradeCount() throws ParseException {
        int overCount=0;
        ArrayList<Trade> trades=tradeService.findOverdueTradeByLevel("一级预警");
        ArrayList<Trade> trades2=tradeService.findOverdueTradeByLevel("二级预警");
        overCount=trades.size()+trades2.size();
        return overCount;
    }

    @Override
    public Boolean addtrade(Trade trade) {
        return tradeDao.addTrade(trade);
    }

    @Override
    public Boolean updateWhileMemberByTradeMember(int tradeMember) {
        return tradeDao.updateWhileMemberByTradeMember(tradeMember);
    }

    @Override
    public ArrayList<Trade> findAllTradesByNoMember() {

        ArrayList<Trade> trades= (ArrayList<Trade>) tradeDao.findAllTradesByNoMember();
        return trades;
    }

    @Override
    public Boolean updateTradeStockByTradeMember(int tradeMember, int outTradeStock) {
        //库存数量
        int tradeStock=tradeDao.findTradeByTradeId(tradeMember).getTradeStock();
        int newtradeStrock=tradeStock-outTradeStock;//出库后数量
        Boolean b=false;
        if (newtradeStrock>=0){
            b=tradeDao.updateTradeStockByTradeMember(tradeMember,newtradeStrock);
        }
        return b;
    }

    @Override
    public Boolean deleteTradeByTradeMember(int tradeMember) {
        Boolean b=tradeDao.deleteTradeByTradeMember(tradeMember);
        return b;
    }
}
