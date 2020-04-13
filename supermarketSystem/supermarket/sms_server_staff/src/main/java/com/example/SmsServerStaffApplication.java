package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.PostConstruct;

@ComponentScan("com.example.domain") //实体类包扫描
@MapperScan("com.example.dao")  //dao层包扫描
@ComponentScan("com.example.service.service_impl")  //service包扫描
@SpringBootApplication
@EnableFeignClients  //启动feign声明式客户端
@EnableHystrix  //启动断路器
@EnableCaching
public class SmsServerStaffApplication {


	@Autowired
	private RedisTemplate redisTemplate=null;

	//使用自定义后初始化方法
	@PostConstruct
	public void init(){
		initRedisTemplate();
	}
	//自定义序列化方法
	private void initRedisTemplate(){
		RedisSerializer stringSerializer=redisTemplate.getStringSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
	}



	public static void main(String[] args) {
		SpringApplication.run(SmsServerStaffApplication.class, args);
	}

}
