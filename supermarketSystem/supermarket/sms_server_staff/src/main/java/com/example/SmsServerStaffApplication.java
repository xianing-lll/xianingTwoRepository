package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.example.domain") //实体类包扫描
@MapperScan("com.example.dao")  //dao层包扫描
@ComponentScan("com.example.service.service_impl")  //service包扫描
@SpringBootApplication
@EnableFeignClients  //启动feign声明式客户端
@EnableHystrix  //启动断路器
@EnableCaching
public class SmsServerStaffApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsServerStaffApplication.class, args);
	}

}
