package com.example.controller;

import com.example.domain.EmployeeAuth;
import com.example.domain.EmployeeInformation;
import com.example.service.service_interface.EmployeeInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class EmployeeInformationController {

    @Autowired
    EmployeeInformationService employeeInformationService;

    /**
     * 通过id查询员工信息
     * @param employeeId
     * @return
     */
    @RequestMapping("/findEmployeeById")
    public EmployeeInformation findEmployeeById(@RequestParam("employeeId") int employeeId){
        EmployeeInformation employeeInformation=new EmployeeInformation();
        employeeInformation=employeeInformationService.findEmployeeInformationById(employeeId);
        return employeeInformation;
    }

    /**
     * 查询所有员工信息
     * @return
     */
    @RequestMapping("/findAllEmployee")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public ArrayList<EmployeeInformation> findAllEmployee(){
        ArrayList<EmployeeInformation>  employeeInformations=new ArrayList<>();
        employeeInformations=employeeInformationService.findAllEmployeeInformation();
        return employeeInformations;
    }

    /**
     * 员工搜索引擎
     * @return
     */
    @RequestMapping("/findEmployeeInformationByAnyone")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public ArrayList<EmployeeInformation> findEmployeeInformationByAnyone(@RequestParam("any") String any, Model model){
        ArrayList<EmployeeInformation> employeeInformations=employeeInformationService.findEmployeeInformationByAnyone(any);
        return employeeInformations;
    }


    /**
     * 注册
     * @param employeeInformation
     * @param employeeAuth
     * @return
     */
    @RequestMapping("/registerEmployeeInformation")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public int registerEmployeeInformation(EmployeeInformation employeeInformation, EmployeeAuth employeeAuth){
        int employeeId=employeeInformationService.registerEmployeeInformation(employeeInformation,employeeAuth);
        return employeeId;
    }

    /**
     * 更新密码
     * @param employeeId
     * @param employeePassWord
     * @return
     */
    @RequestMapping("/updateEmployeePassWord")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean updateEmployeePassWord(@RequestParam("employeeId") int employeeId,
                                         @RequestParam("employeePassWord") String employeePassWord){
        Boolean b=employeeInformationService.updateEmployeePassWord(employeeId, employeePassWord);
        return b;
    }

    /**
     * 查找所有未注册的员工
     * @param model
     * @return
     */
    @RequestMapping("/findEmployeeInformationByEmployeeRegular")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public ArrayList<EmployeeInformation> findEmployeeInformationByEmployeeRegular(){
        ArrayList<EmployeeInformation> employeeInformations=employeeInformationService.findEmployeeInformationByEmployeeRegular();
        return employeeInformations;
    }
    /**
     * 注册审批
     * @param employeeId
     * @return
     */
    @RequestMapping("/updateemployeeRegularByEmployeeId")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean updateemployeeRegularByEmployeeId(@RequestParam("employeeId") int employeeId){
        Boolean b=employeeInformationService.updateemployeeRegularByEmployeeId(employeeId);
        return b;
    }

    /**
     * 更新员工角色信息
     * @param employeeAuthoritie
     * @param authId
     * @return
     */
    @RequestMapping("/updateEmployeeAuthoritie")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean updateEmployeeAuthoritie(@RequestParam("employeeAuthoritie") String employeeAuthoritie, @RequestParam("authId") int authId){
        Boolean b=employeeInformationService.updateEmployeeAuthoritie(employeeAuthoritie,authId);

        return b;
    }

    /**
     * 通过角色id,给某个员工添加角色
     * @param employeeAuth
     * @return
     */
    @RequestMapping("/addEmployeeAuthByEmployeeId")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean addEmployeeAuthByEmployeeId(EmployeeAuth employeeAuth){
        Boolean b=employeeInformationService.addEmployeeAuthByEmployeeId(employeeAuth);
        return b;
    }

    /**
     * 通过id删除某个职位
     * @param employeeId
     * @param employeeAuthoritie
     * @return
     */
    @RequestMapping("/deleteEmployeeAuthByEmployeeId")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean deleteEmployeeAuthByEmployeeId(@RequestParam("employeeId") int employeeId,
                                                 @RequestParam("employeeAuthoritie") String employeeAuthoritie){
        Boolean b=employeeInformationService.deleteEmployeeAuthByEmployeeId(employeeId,employeeAuthoritie);
        return b;
    }

    /**
     * 通过角色id删除角色
     * @param authId
     * @return
     */
    @RequestMapping("/deleteEmployeeAuthByAuthId")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean deleteEmployeeAuthByAuthId(int authId) {
        Boolean b=employeeInformationService.deleteEmployeeAuthByAuthId(authId);
        return b;
    }
    /**
     * 删除某个员工
     * @param employeeId
     * @return
     */
    @RequestMapping("/deleteEmployeeInformation/{employeeId}")
    @PreAuthorize("hasAnyAuthority('人事经理','总经理')")
    public Boolean deleteEmployeeInformation(@PathVariable("employeeId") int employeeId){
        Boolean b=employeeInformationService.deleteEmployeeInformation(employeeId);
        return b;
    }
}
