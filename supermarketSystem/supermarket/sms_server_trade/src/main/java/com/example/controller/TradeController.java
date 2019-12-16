package com.example.controller;

import com.example.domain.Trade;
import com.example.service.service_impl.TradeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;

@RestController
public class TradeController {

    @Autowired
    TradeServiceImpl tradeService;

    /**
     * 查找所有商品
     * @return
     */
    @RequestMapping("/findAllTrades")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public ArrayList<Trade> findAllTrades() {
        ArrayList<Trade> trades=tradeService.findAllTrades();
        return trades;
    }

    /**
     * 通过商品种类查找商品
     * @param tradeCategory
     * @return
     */
    @RequestMapping("/findTradeByTradeCategory")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public ArrayList<Trade> findTradeByTradeCategory(@RequestParam("tradeCategory") String tradeCategory) {
        ArrayList<Trade> trades=tradeService.findTradeByTradeCategory(tradeCategory);
        return trades;
    }

    /**
     * 通过商品预警级数查找商品
     * @param Level
     * @return
     * @throws ParseException
     */
    @RequestMapping("/findOverdueTradeByLevel")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public ArrayList<Trade> findOverdueTradeByLevel(@RequestParam("Level") String Level) throws ParseException {
        ArrayList<Trade> trades=tradeService.findOverdueTradeByLevel(Level);
        return trades;
    }

    /**
     * 显示一级预警商品数量
     * @return
     * @throws ParseException
     */
    @RequestMapping("/findOverTradeCount")
    public int findOverTradeCount() throws ParseException {
        int overCount=tradeService.findOverTradeCount();
        return overCount;
    }

    /**
     * 添加一个商品
     * @param trade
     * @return
     */
    @RequestMapping("/addtrade")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean addtrade(Trade trade) {
        Boolean b=tradeService.addtrade(trade);
        return b;
    }

    /**
     * 通过商品编号更改商品为会员商品
     * @param tradeMember
     * @return
     */
    @RequestMapping("/updateWhileMemberByTradeMember")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean updateWhileMemberByTradeMember(@RequestParam("tradeMember") int tradeMember) {
        Boolean b=tradeService.updateWhileMemberByTradeMember(tradeMember);
        return b;
    }

    /**
     * 查找所有非会员商品
     * @return
     */
    @RequestMapping("/findAllTradesByNoMember")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public ArrayList<Trade> findAllTradesByNoMember() {
        ArrayList<Trade> trades=tradeService.findAllTradesByNoMember();
        return trades;
    }

    /**
     * 商品出库功能
     * @param tradeMember
     * @param outTradeStock
     * @return
     */
    @RequestMapping("/updateTradeStockByTradeMember")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean updateTradeStockByTradeMember(int tradeMember, int outTradeStock) {
        Boolean b=tradeService.updateTradeStockByTradeMember(tradeMember,outTradeStock);
        return b;
    }

    @RequestMapping("/deleteTradeByTradeMember")
    @PreAuthorize("hasAnyAuthority('库存经理','总经理','库存管理员')")
    public Boolean deleteTradeByTradeMember(int tradeMember){
        Boolean b=tradeService.deleteTradeByTradeMember(tradeMember);
        return b;
    }
}
