package com.example;

import com.example.dao.TradeDao;
import com.example.domain.Trade;
import com.example.service.service_impl.TradeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class SmsServerTradeApplicationTests {

	@Autowired(required = false)
	TradeDao tradeDao;
	@Autowired(required = false)
	TradeServiceImpl tradeService;
	@Test
	void contextLoads() {
		Trade trade=new Trade();
		trade.setTradeName("康师傅方便面");
		trade.setTradeCategory("休闲食品");
		trade.setPurhasePrice(25);
		trade.setPrice(30);
		trade.setTradeStock(50);
		trade.setManufactureDate("2019-6-25");
		trade.setQualityGuaranteePeriod(180);
		trade.setWhileMember(1);
		trade.setPurchaseId(1);
		Boolean b=tradeDao.addTrade(trade);
		System.out.println(b);
	}

	/**
	 * 时间操作测试
	 */
	@Test
	public void testDate() throws ParseException {
		ArrayList<Trade> trades= (ArrayList<Trade>) tradeService.findOverdueTradeByLevel("过期商品");
		System.out.println(trades);
		ArrayList<Trade> trades2= (ArrayList<Trade>) tradeService.findOverdueTradeByLevel("二级预警");
		System.out.println(trades2);
		ArrayList<Trade> trades3= (ArrayList<Trade>) tradeService.findOverdueTradeByLevel("一级预警");
		System.out.println(trades3);
	}

	@Test
	public void findAllTrades(){
		ArrayList<Trade> trades= (ArrayList<Trade>) tradeDao.findAllTradesByNoMember();
		for (Trade t:trades
			 ) {
			System.out.println(t);
		}
	}

	@Test
	void test3(){
		Boolean b=tradeService.updateWhileMemberByTradeMember(6);
		System.out.println(b);
	}

}
