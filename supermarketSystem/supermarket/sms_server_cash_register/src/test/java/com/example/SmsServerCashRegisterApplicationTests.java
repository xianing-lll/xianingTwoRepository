package com.example;

import com.example.dao.MemberDao;
import com.example.dao.TransactionRecordDao;
import com.example.domain.Member;
import com.example.domain.TransactionRecord;
import com.example.service.TransactionRecordService;
import com.example.service.serviceImpl.MemberServiceImpl;
import com.example.service.serviceImpl.TransactionRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SmsServerCashRegisterApplicationTests {

	@Autowired
	MemberServiceImpl memberService;
	@Autowired
	TransactionRecordDao transactionRecordDao;
	@Autowired
	TransactionRecordServiceImpl transactionRecordService;
	@Autowired
	MemberDao memberDao;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Test
	void contextLoads() {

		ArrayList<TransactionRecord> transactionRecords= (ArrayList<TransactionRecord>) transactionRecordDao.findAllTransactionRecord("2019-11-25");
		for (TransactionRecord t:transactionRecords
		) {
			System.out.println(t);
		}
	}

	@Test
	public void test(){
		ArrayList<Member> members=memberService.findAllMember();
		for (Member m:members
		) {
			System.out.println(m);
		}
	}
	@Test
	public void test2(){

		int d=memberService.findMemberNumber();
		System.out.println(d);
	}

	//测试redis连通性
	@Test
	public void testRedis(){
		//定义key
		String key="total";
		//定义value
		String value="123456";

		//校验key是否存在,如果不存在返回-2
		Long expire=stringRedisTemplate.getExpire(key);
		System.out.println(expire);
		//存储数据,在redis中存储30秒
		stringRedisTemplate.boundValueOps(key).set(String.valueOf(value),3000, TimeUnit.SECONDS);
		//获取数据
		String s=stringRedisTemplate.opsForValue().get(key);
		System.out.println(s);
	}



}
