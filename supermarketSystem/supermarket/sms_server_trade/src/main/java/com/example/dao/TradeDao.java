package com.example.dao;

import com.example.domain.Trade;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
public interface TradeDao {

    /**
     * 添加商品
     * @param trade
     * @return
     */
    @Insert("INSERT INTO trade ( trade_name, trade_category, purhase_price, price, trade_stock, manufacture_date, quality_guarantee_period, while_member, purchase_id )\n" +
            "VALUES\n" +
            "\t( #{tradeName}, #{tradeCategory}, #{purhasePrice}, #{price}, #{tradeStock}, #{manufactureDate}, #{qualityGuaranteePeriod}, #{whileMember}, #{purchaseId} )")
    public Boolean addTrade(Trade trade);

    /**
     * 查询所有商品
     * @return
     */
    @Select("SELECT * FROM trade")
    public List<Trade> findAllTrades();

    /**
     * 查找所有非会员商品
     * @return
     */
    @Select("SELECT * FROM trade WHERE while_member=0")
    public List<Trade> findAllTradesByNoMember();

    /**
     * 通过商品种类查询商品
     * @param tradeCategory
     * @return
     */
    @Select("SELECT * FROM trade WHERE trade_category=#{tradeCategory}")
    public List<Trade> findTradeByTradeCategory(String tradeCategory);

    /**
     * 通过商品id查询商品价格、商品库存和是否会员
     * @param tradeMember
     * @return
     */
    @Select("SELECT price ,trade_stock,while_member FROM trade WHERE trade_member=#{tradeMember}")
    public Trade findTradeByTradeId(int tradeMember);
    /**
     * 会员商品标注
     * @return
     */
    @Update("UPDATE trade SET while_member=1 WHERE trade_member=#{tradeMember}")
    public Boolean updateWhileMemberByTradeMember(int tradeMember);

    /**
     * 商品出库
     * @param tradeMember
     * @param tradeStock
     * @return
     */
    @Update("UPDATE trade SET trade_stock=#{tradeStock} WHERE trade_member=#{tradeMember}")
    public Boolean updateTradeStockByTradeMember(int tradeMember,int tradeStock);

    /**
     * 通过商品编号删除商品
     * @param tradeMember
     * @return
     */
    @Delete("DELETE FROM trade WHERE trade_member=#{tradeMember}")
    public Boolean deleteTradeByTradeMember(int tradeMember);
}
