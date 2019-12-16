package com.example.service.service_interface;

import com.example.domain.Trade;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public interface TradeService {

    /**
     * 查询所有商品
     * @return
     */
    public ArrayList<Trade> findAllTrades();


    /**
     * 通过商品种类查找商品
     * @param tradeCategory
     * @return
     */
    public ArrayList<Trade> findTradeByTradeCategory(String tradeCategory);
    /**
     * 通过预警级数查找对应商品
     * @param Level
     * @return
     */
    public ArrayList<Trade> findOverdueTradeByLevel(String Level) throws ParseException;

    /**
     * 查找即将过期商品数量
     * @return
     */
    public int findOverTradeCount() throws ParseException;
    /**
     * 添加一个商品
     * @param trade
     * @return
     */
    public Boolean addtrade(Trade trade);

    /**
     * 通过商品id会员标注
     * @param tradeMember
     * @return
     */
    public Boolean updateWhileMemberByTradeMember(int tradeMember);

    /**
     * 查找所有非会员商品
     * @return
     */
    public ArrayList<Trade> findAllTradesByNoMember();

    /**
     * 商品出库
     * @param tradeMember
     * @param outTradeStock
     * @return
     */
    public Boolean updateTradeStockByTradeMember(int tradeMember,int outTradeStock);

    /**
     * 根据商品编号删除商品
     * @param tradeMember
     * @return
     */
    public Boolean deleteTradeByTradeMember(int tradeMember);
}
