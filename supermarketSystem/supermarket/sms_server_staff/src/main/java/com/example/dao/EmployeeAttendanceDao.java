package com.example.dao;

import com.example.domain.EmployeeAttendance;
import com.example.domain.EmployeeInformation;
import com.example.domain.RolePay;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeAttendanceDao {

    /**
     * 1、插入一条考勤记录
     * @param employeeAttendance
     * @return
     */
    @Insert("INSERT INTO employee_attendance(employee_id,leave_days,work_time) VALUES(#{employeeId},#{leaveDays},#{workTime})")
    public Boolean addEmployeeAttendance(EmployeeAttendance employeeAttendance);
    /**
     * 2、修改请假天数
     * @param employeeId
     * @param leaveDays
     * @return
     */
    @Update("UPDATE employee_attendance SET leave_days=#{leaveDays} WHERE employee_id=#{employeeId} AND work_time=#{workTime}")
    public boolean updateLeaveDaysByEmployeeId(int employeeId,double leaveDays, String workTime);


    /**
     * 通过员工id查找员工姓名
     * @param employeeId
     * @return
     */
    @Select("SELECT employee_name FROM employee_information WHERE employee_id=#{employeeId}")
    public EmployeeInformation findEmployeeNameByEmployeeId(int employeeId);

    /**
     * 3、通过时间查找所有缺勤员工信息
     * @param employeeId
     * @return
     */
    @Select("SELECT * FROM employee_attendance WHERE work_time=#{workTime}")
    @Results({
            @Result(property = "employeeId",column = "employee_id"),
            @Result(property = "employeeInformation",column = "employee_id",
            one = @One(select = "com.example.dao.EmployeeAttendanceDao.findEmployeeNameByEmployeeId"))
    })
    public List<EmployeeAttendance> findAllEmployeeAttendanceByWorkTime(String workTime);

    /**
     * 4、通过员工账号和时间查找所有缺勤员工信息
     * @param employeeId
     * @param workTime
     * @return
     */
    @Select("SELECT * FROM employee_attendance WHERE employee_id=#{employeeId} AND work_time=#{workTime}")
    @Results({
            @Result(property = "employeeId",column = "employee_id"),
            @Result(property = "employeeInformation",column = "employee_id",
                    one = @One(select = "com.example.dao.EmployeeAttendanceDao.findEmployeeNameByEmployeeId"))
    })
    public EmployeeAttendance findEmployeeAttendanceByIdAndWorkTime(int employeeId,String workTime);
    /**
     * 5、查询角色对应的工资
     * @return
     */
    @Select("SELECT * FROM role_pay")
    public List<RolePay> findAllRolePay();


    /**
     * 6、修改角色对应工资
     * @param id
     * @param pay
     * @return
     */
    @Update("UPDATE role_pay SET pay=#{pay} WHERE id=#{id}")
    public Boolean updateRolePay(int id,double pay);

    /**
     * 7、修改请假每天扣除费用
     * @param id
     * @return
     */
    @Update("UPDATE role_pay SET one_day_deduction=#{oneDayDeduction} WHERE id=#{id}")
    public Boolean updateOneDayDeductionById(int id, Double oneDayDeduction);

}
