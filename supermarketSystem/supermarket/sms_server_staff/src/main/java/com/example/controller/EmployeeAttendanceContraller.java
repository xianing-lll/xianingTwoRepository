package com.example.controller;

import com.example.domain.EmployeeAttendance;
import com.example.domain.EmployeeInformation;
import com.example.domain.RolePay;
import com.example.service.service_impl.EmployeeAttendanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class EmployeeAttendanceContraller {
    @Autowired
    EmployeeAttendanceServiceImpl employeeAttendanceService;

    /**
     * 通过月份查询员工工资
     * @param workTime
     * @return
     */
    @RequestMapping("/findAllEmployeeSalary")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public ArrayList<EmployeeInformation> findAllEmployeeSalary(@RequestParam("workTime") String workTime){
        ArrayList<EmployeeInformation> employeeInformations=employeeAttendanceService.findAllEmployeeSalary(workTime);
        return employeeInformations;
    }

    /**
     * 通过员工号和月份查询员工工资
     * @param employeeId
     * @param workTime
     * @return
     */
    @RequestMapping("/findEmployeeSalaryByEmployeeId")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public EmployeeInformation findEmployeeSalaryByEmployeeId(@RequestParam("employeeId") int employeeId,
                                                              @RequestParam("workTime") String workTime) {
        EmployeeInformation employeeInformation=employeeAttendanceService.findEmployeeSalaryByEmployeeId(employeeId,workTime);
        return employeeInformation;
    }

    /**
     * 添加一条员工考勤记录
     * @param employeeId
     * @param leaveDays
     * @return
     */
    @RequestMapping("/addEmployeeAttendance")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean addEmployeeAttendance(@RequestParam("employeeId") int employeeId, @RequestParam("leaveDays") Double leaveDays) {
        Boolean b=employeeAttendanceService.addEmployeeAttendance(employeeId,leaveDays);
        return b;
    }

    /**
     * 通过员工id更新员工考勤记录
     * @param employeeId
     * @param leaveDays
     * @return
     */
    @RequestMapping("/updateEmployeeAttendanceByEmployeeId")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean updateEmployeeAttendanceByEmployeeId(@RequestParam("employeeId") int employeeId,@RequestParam("leaveDays") Double leaveDays) {
        Boolean b=employeeAttendanceService.updateEmployeeAttendanceByEmployeeId(employeeId,leaveDays);
        return b;
    }

    /**
     * 通过月份查询所有员工考勤记录
     * @param workTime
     * @return
     */
    @RequestMapping("/findAllEmployeeAttendance")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public ArrayList<EmployeeAttendance> findAllEmployeeAttendance(@RequestParam("workTime") String workTime) {
        ArrayList<EmployeeAttendance> employeeAttendances=employeeAttendanceService.findAllEmployeeAttendance(workTime);
        return employeeAttendances;
    }

    /**
     * 查询所有角色对应工资
     * @return
     */
    @RequestMapping("/findAllRolePay")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public ArrayList<RolePay> findAllRolePay() {
        ArrayList<RolePay> rolePays=employeeAttendanceService.findAllRolePay();
        return rolePays;
    }

    /**
     * 通过id更新个种角色工资·
     * @param id
     * @param pay
     * @return
     */
    @RequestMapping("/updateRolePay")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean updateRolePay(@RequestParam("id") int id,@RequestParam("pay") double pay) {
        Boolean b=employeeAttendanceService.updateRolePay(id,pay);
        return b;
    }

    /**
     * 修改某个角色请假每天扣除费用
     * @param id
     * @param oneDayDeduction
     * @return
     */
    @RequestMapping("/updateOneDayDeductionById")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean updateOneDayDeductionById(@RequestParam("id") int id,@RequestParam("oneDayDeduction") Double oneDayDeduction) {
        Boolean b=employeeAttendanceService.updateOneDayDeductionById(id, oneDayDeduction);
        return b;
    }
}
