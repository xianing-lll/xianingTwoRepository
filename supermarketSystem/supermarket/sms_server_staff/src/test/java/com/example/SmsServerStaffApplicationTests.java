package com.example;

import com.example.dao.EmployeeAttendanceDao;
import com.example.dao.EmployeeInformationDao;
import com.example.domain.EmployeeAttendance;
import com.example.domain.EmployeeAuth;
import com.example.domain.EmployeeInformation;
import com.example.service.service_impl.EmployeeInformationServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class SmsServerStaffApplicationTests {

	@Autowired(required = false)
	EmployeeInformationDao employeeInformationDao;
	@Autowired(required = false)
	EmployeeAttendanceDao employeeAttendanceDao;
	@Autowired(required = false)
	EmployeeInformationServiceImpl employeeInformationService;
	@Test
	void testregister(){
		EmployeeInformation employeeInformation=new EmployeeInformation();
		EmployeeAuth employeeAuth=new EmployeeAuth();
		employeeInformation.setEmployeePassWord("123456");
		employeeInformation.setEmployeeName("和晨阳");
		employeeInformation.setEmployeeIdCard("410224199712109899");
		employeeInformation.setEmployeeMobile("15981856274");
		employeeAuth.setEmployeeAuthoritie("库存经理");

		int b=employeeInformationService.registerEmployeeInformation(employeeInformation,employeeAuth);
		System.out.println(b);

	}
	@Test
	public void testdata(){
		Date date=new Date();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
		System.out.println(String.valueOf(simpleDateFormat.format(date)));
	}
	@Test
	void contextLoads() {
		ArrayList<EmployeeInformation> employeeInformation=employeeInformationDao.findEmployeeInformationByAnyone("10");
		//System.out.println(employeeInformation);
		for (EmployeeInformation e:employeeInformation
			 ) {
			System.out.println(e);
		}
	}
	@Test
	void contextLoads2() {
		EmployeeInformation employeeInformation=employeeInformationDao.findEmployeeInformationByEmployeeId(1003);
		System.out.println(employeeInformation);
//		for (EmployeeInformation e:employeeInformation
//		) {
//			System.out.println(e);
//		}
	}
	@Test
	void contextLoads3() {
		ArrayList<EmployeeInformation> employeeInformation= (ArrayList<EmployeeInformation>) employeeInformationDao.findAllEmployeeInformation();
		//System.out.println(employeeInformation);
		for (EmployeeInformation e:employeeInformation
		) {
			System.out.println(e);
		}
	}
	@Test
	void contextLoads4() {

		ArrayList<EmployeeAttendance> employeeAttendance= (ArrayList<EmployeeAttendance>) employeeAttendanceDao.findAllEmployeeAttendanceByWorkTime("2019-11");
		for (EmployeeAttendance e: employeeAttendance
			 ) {
			System.out.println(e);
		}
	}



}
