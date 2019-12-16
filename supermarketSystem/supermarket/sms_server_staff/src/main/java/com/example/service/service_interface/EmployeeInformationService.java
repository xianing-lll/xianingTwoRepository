package com.example.service.service_interface;

import com.example.domain.EmployeeAuth;
import com.example.domain.EmployeeInformation;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeInformationService {

    /**
     * 实现员工信息搜索引擎
     * @param any 用户输入关键字
     * @return
     */
    public ArrayList<EmployeeInformation> findEmployeeInformationByAnyone(String any);

    /**
     * 查询所有员工信息
     * @return
     */
    public ArrayList<EmployeeInformation> findAllEmployeeInformation();
    /**
     * 员工注册,包括角色
     * @param employeeInformation
     * @return
     */
    public int registerEmployeeInformation(EmployeeInformation employeeInformation, EmployeeAuth employeeAuth);

    /**
     * 修改员工密码
     * @param employeeId
     * @param employeePassWord
     * @return
     */
    public Boolean updateEmployeePassWord(int employeeId,String employeePassWord);

    /**
     * 未注册员工
     * @return
     */
    public ArrayList<EmployeeInformation> findEmployeeInformationByEmployeeRegular();
    /**
     * 注册审批
     * @return
     */
    public Boolean updateemployeeRegularByEmployeeId(int employeeId);

    /**
     * 调整员工职位
     * @param employeeAuthoritie
     * @param authId
     * @return
     */
    public Boolean updateEmployeeAuthoritie(String employeeAuthoritie, int authId);

    /**
     * 通过员工id给改员工添加一个角色
     * @param employeeAuth
     * @return
     */
    public Boolean addEmployeeAuthByEmployeeId(EmployeeAuth employeeAuth);

    /**
     * 通过员工id给改员工删除一个角色
     * @param employeeId
     * @param employeeAuthoritie
     * @return
     */
    public Boolean deleteEmployeeAuthByEmployeeId(int employeeId,String employeeAuthoritie);

    /**
     * 通过角色id删除某个角色
     * @param authId
     * @return
     */
    public Boolean deleteEmployeeAuthByAuthId(int authId);
    /**
     * 删除某个员工
     * @param employeeId
     * @return
     */
    public Boolean deleteEmployeeInformation(int employeeId);

    /**
     * 通过员工id查询员工信息
     * @param employeeId
     * @return
     */
    public EmployeeInformation findEmployeeInformationById(int employeeId);
}
