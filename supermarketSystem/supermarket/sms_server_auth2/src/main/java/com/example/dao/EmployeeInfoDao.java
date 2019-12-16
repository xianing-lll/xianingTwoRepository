package com.example.dao;

import com.example.domain.EmployeeAuth;
import com.example.domain.EmployeeInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.ArrayList;

@Mapper
public interface EmployeeInfoDao {
    /**
     * 查询员工信息
     * @param employeeId  员工id
     * @return
     */
    @Select("select * from employee_information where employee_id=#{employeeId}")
    public EmployeeInformation getUserBYUsername(String employeeId);

    /**
     * 查询员工权限信息
     * @param employeeId  员工id
     * @return
     */
    @Select("SELECT * FROM employee_auth WHERE employee_id=#{employeeId}")
    public ArrayList<EmployeeAuth> findRolesByUsername(String employeeId);
}
