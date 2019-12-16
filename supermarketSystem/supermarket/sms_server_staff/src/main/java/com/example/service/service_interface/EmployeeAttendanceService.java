package com.example.service.service_interface;

import com.example.domain.EmployeeAttendance;
import com.example.domain.EmployeeInformation;
import com.example.domain.RolePay;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeAttendanceService {

    /**
     * 查询所有员工工资，包括属性（员工id，员工姓名，工资，工作月份）
     * 工资=不同员工基本工资-请假天数*请假每天扣除工资
     * @param workTime
     * @return
     */
    public ArrayList<EmployeeInformation> findAllEmployeeSalary(String workTime);

    /**
     * 通过员工号查询员工资
     * @param employeeId
     * @param workTime
     * @return
     */
    public EmployeeInformation findEmployeeSalaryByEmployeeId(int employeeId, String workTime);

    /**
     * 添加一位新的员工的考勤,考勤时间为当前月份，格式如：2019-11
     * @param employeeId
     * @param leaveDays
     * @return
     */
    public Boolean addEmployeeAttendance(int employeeId, Double leaveDays);

    /**
     * 更新一位新的员工的考勤,考勤时间为当前月份，格式如：2019-11
     * @param employeeId
     * @param leaveDays
     * @return
     */
    public Boolean updateEmployeeAttendanceByEmployeeId(int employeeId, Double leaveDays);

    /**
     * 通过时间查找所有缺勤员工信息
     * @param workTime
     * @return
     */
    public ArrayList<EmployeeAttendance> findAllEmployeeAttendance(String workTime);

    /**
     * 查询角色工资对应表
     * @return
     */
    public ArrayList<RolePay> findAllRolePay();

    /**
     * 修改角色对应工资
     * @param id
     * @param pay
     * @return
     */
    public Boolean updateRolePay(int id,double pay);

    /**
     * 修改请假每天扣除费用
     * @param id
     * @param oneDayDeduction
     * @return
     */
    public Boolean updateOneDayDeductionById(int id, Double oneDayDeduction);
}
