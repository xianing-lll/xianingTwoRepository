package com.example.service.service_impl;

import com.example.dao.EmployeeInformationDao;
import com.example.domain.EmployeeAuth;
import com.example.domain.EmployeeInformation;
import com.example.service.service_interface.EmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
@Service
public class EmployeeInformationServiceImpl implements EmployeeInformationService {
    @Autowired(required = false)
    EmployeeInformationDao employeeInformationDao;
    @Override
    public ArrayList<EmployeeInformation> findEmployeeInformationByAnyone(String any) {
        return employeeInformationDao.findEmployeeInformationByAnyone(any);
    }

    @Override
    public ArrayList<EmployeeInformation> findAllEmployeeInformation() {
        return (ArrayList<EmployeeInformation>) employeeInformationDao.findAllEmployeeInformation();
    }

    @Override
    public int registerEmployeeInformation(EmployeeInformation employeeInformation, EmployeeAuth employeeAuth) {
        //对密码编码
        String password=employeeInformation.getEmployeePassWord();
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String bcrPassword=bCryptPasswordEncoder.encode(password);
        employeeInformation.setEmployeePassWord(bcrPassword);
        //设置员工入职时间
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        employeeInformation.setEmployeeEntryTime(String.valueOf(simpleDateFormat.format(date)));

        Boolean boolen=employeeInformationDao.addEmployeeInformaton(employeeInformation);
        System.out.println(boolen);
        if (boolen==true){
            employeeAuth.setEmployeeId(employeeInformation.getEmployeeId());
            Boolean b=employeeInformationDao.addEmployeeAuth(employeeAuth);
            if (b==true){
                return employeeInformation.getEmployeeId();
            }else {
                return 0;
            }

        }else {
            return 0;
        }

    }

    @Override
    public Boolean updateEmployeePassWord(int employeeId, String employeePassWord) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bcrPassword = bCryptPasswordEncoder.encode(employeePassWord);
        employeePassWord = bcrPassword;

        return employeeInformationDao.updatEemployeePassWordByEmployeeId(employeeId, employeePassWord);
    }

    @Override
    public ArrayList<EmployeeInformation> findEmployeeInformationByEmployeeRegular() {
        ArrayList<EmployeeInformation> employeeInformations= (ArrayList<EmployeeInformation>) employeeInformationDao.findEmployeeInformationByEmployeeRegular();

        return employeeInformations;
    }

    @Override
    public Boolean updateemployeeRegularByEmployeeId(int employeeId) {
        return employeeInformationDao.updateemployeeRegularByEmployeeId(employeeId);
    }

    @Override
    public Boolean updateEmployeeAuthoritie(String employeeAuthoritie, int authId) {
        return employeeInformationDao.updateEmployeeAuthoritie(employeeAuthoritie, authId);
    }

    @Override
    public Boolean addEmployeeAuthByEmployeeId(EmployeeAuth employeeAuth) {
        return employeeInformationDao.addEmployeeAuth(employeeAuth);
    }

    @Override
    public Boolean deleteEmployeeAuthByEmployeeId(int employeeId, String employeeAuthoritie) {
        return employeeInformationDao.deleteEmployeeAuth(employeeId, employeeAuthoritie);
    }

    @Override
    public Boolean deleteEmployeeAuthByAuthId(int authId) {
        Boolean b=employeeInformationDao.deleteEmployeeAuthByAuthId(authId);
        return b;
    }

    @Override
    public Boolean deleteEmployeeInformation(int employeeId) {
        return employeeInformationDao.deleteEmployeeInformationByEmployeeId(employeeId);
    }

    @Override
    @Cacheable(value = "redisCache",key = "'redis_employeeInformation_'+#employeeId")
    public EmployeeInformation findEmployeeInformationById(int employeeId) {
        EmployeeInformation employeeInformation=new EmployeeInformation();
        employeeInformation=employeeInformationDao.findEmployeeInformationByEmployeeId(employeeId);
        return employeeInformation;
    }
}
