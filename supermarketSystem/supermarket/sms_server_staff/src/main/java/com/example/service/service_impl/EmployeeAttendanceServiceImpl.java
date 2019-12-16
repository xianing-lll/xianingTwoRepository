package com.example.service.service_impl;

import com.example.dao.EmployeeAttendanceDao;
import com.example.dao.EmployeeInformationDao;
import com.example.domain.*;
import com.example.service.service_interface.EmployeeAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

    @Autowired(required = false)
    EmployeeInformationDao employeeInformationDao;
    @Autowired(required = false)
    EmployeeAttendanceDao employeeAttendanceDao;
    @Autowired
    EmployeeAttendanceServiceImpl employeeAttendanceService;

    @Override
    public ArrayList<EmployeeInformation> findAllEmployeeSalary(String workTime) {
        //查询所有员工基本信息及角色
        ArrayList<EmployeeInformation> employeeInformations= (ArrayList<EmployeeInformation>) employeeInformationDao.findAllEmployeeInformation();
        //定义储存员工信息和工资集合
        ArrayList<EmployeeInformation> employeeInformationArrayList=new ArrayList<>();
        for (int i = 0; i < employeeInformations.size(); i++) {
            EmployeeInformation employeeInformation=employeeAttendanceService.findEmployeeSalaryByEmployeeId(employeeInformations.get(i).getEmployeeId(),workTime);
            employeeInformationArrayList.add(employeeInformation);
        }
        return employeeInformationArrayList;
    }


    @Override
    public EmployeeInformation findEmployeeSalaryByEmployeeId(int employeeId, String workTime) {
        //查询员工基本信息及角色
        EmployeeInformation employeeInformation=employeeInformationDao.findEmployeeInformationByEmployeeId(employeeId);
        //员工角色信息
        ArrayList<EmployeeAuth> employeeAuths= (ArrayList<EmployeeAuth>) employeeInformation.getEmployeeAuths();
        //查找所有的角色工资
        List<RolePay> rolePays=employeeAttendanceDao.findAllRolePay();
        //查找员工缺勤时间
        EmployeeAttendance employeeAttendance=employeeAttendanceDao.findEmployeeAttendanceByIdAndWorkTime(employeeId,workTime);
        //员工缺勤时间
        Double leaveDays=0.0;
        if (employeeAttendance!=null&&!employeeAttendance.equals("")){
            leaveDays=employeeAttendance.getLeaveDays();
        }

        //用于封装角色对应每月工资和罚金
        ArrayList<RolePay> employeeRolePay=new ArrayList<>();
        for (int i = 0; i < employeeAuths.size(); i++) {
            for (int j = 0; j < rolePays.size(); j++) {
                if (employeeAuths.get(i).getEmployeeAuthoritie().equals(rolePays.get(j).getEmployeeAuthoritie())){
                    employeeRolePay.add(rolePays.get(j));
                }
            }
        }

        Double salary=0.0;
        //进行工资计算
        for (int i = 0; i < employeeRolePay.size(); i++) {
            salary+=employeeRolePay.get(i).getPay()-leaveDays*employeeRolePay.get(i).getOneDayDeduction();
        }

        //封装离职天数

        EmployeeAttendance employeeAttendance2=new EmployeeAttendance();
        employeeAttendance2.setLeaveDays(leaveDays);

        //封装员工工资
        Salary salary1=new Salary();
        salary1.setEmployeeId(employeeId);
        salary1.setEmployeeSalary(salary);

        //把员工工资类加入员工信息类
        employeeInformation.setEmployeeAttendance(employeeAttendance2); //封装员工请假天数
        employeeInformation.setSalary(salary1);
        return employeeInformation;
    }

    @Override
    public Boolean addEmployeeAttendance(int employeeId, Double leaveDays) {
        EmployeeAttendance employeeAttendance=new EmployeeAttendance();
        employeeAttendance.setLeaveDays(leaveDays);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM");
        String workTime= String.valueOf(simpleDateFormat.format(date));
        employeeAttendance.setWorkTime(workTime);
        employeeAttendance.setEmployeeId(employeeId);
        return employeeAttendanceDao.addEmployeeAttendance(employeeAttendance);
    }

    @Override
    public Boolean updateEmployeeAttendanceByEmployeeId(int employeeId, Double leaveDays) {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM");
        String workTime= String.valueOf(simpleDateFormat.format(date));

        return employeeAttendanceDao.updateLeaveDaysByEmployeeId(employeeId,leaveDays,workTime) ;
    }

    @Override
    public ArrayList<EmployeeAttendance> findAllEmployeeAttendance(String workTime) {
        return (ArrayList<EmployeeAttendance>) employeeAttendanceDao.findAllEmployeeAttendanceByWorkTime(workTime);
    }

    @Override
    public ArrayList<RolePay> findAllRolePay() {
        return (ArrayList<RolePay>) employeeAttendanceDao.findAllRolePay();
    }

    @Override
    public Boolean updateRolePay(int id, double pay) {
        return employeeAttendanceDao.updateRolePay(id,pay);
    }

    @Override
    public Boolean updateOneDayDeductionById(int id, Double oneDayDeduction) {
        return employeeAttendanceDao.updateOneDayDeductionById(id, oneDayDeduction);
    }
}
