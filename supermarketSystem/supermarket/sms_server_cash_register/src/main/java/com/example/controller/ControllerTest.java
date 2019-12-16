package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/r")
public class ControllerTest {
    @RequestMapping("/r1")
    @PreAuthorize("hasAnyAuthority('收银员','总经理')")

    public String r1(){
        return "收银系统访问成功!";
    }
}
