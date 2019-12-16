package com.example.service;

import com.example.dao.EmployeeInfoDao;

import com.example.domain.EmployeeAuth;
import com.example.domain.EmployeeInformation;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired(required = false)
    EmployeeInfoDao employeeInfoDao;
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    //根据账号返回用户信息
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
        EmployeeInformation employeeInformation =employeeInfoDao.getUserBYUsername(employeeId);
        ArrayList<EmployeeAuth> employeeAuths=employeeInfoDao.findRolesByUsername(employeeId);
        if (employeeInformation==null){
            throw new Exception();
        }
        //这哪是采用模拟
        //UserDetails userDetails= User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities("p1").build();
        logger.info("员工id"+employeeInfoDao);
        return changeToUser(employeeInformation,employeeAuths);
}
    private UserDetails changeToUser(EmployeeInformation employeeInformation, ArrayList<EmployeeAuth> employeeAuths){
        //权限列表
        ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        //赋予查询到的角色
        for (EmployeeAuth role:employeeAuths
        ) {
            GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(role.getEmployeeAuthoritie());
            grantedAuthorities.add(grantedAuthority);
            logger.info(String.valueOf(grantedAuthority));
        }

        UserDetails userDetails= User.withUsername(String.valueOf(employeeInformation.getEmployeeId())).password(employeeInformation.getEmployeePassWord()).authorities(grantedAuthorities).build();
        return userDetails;
    }
}
