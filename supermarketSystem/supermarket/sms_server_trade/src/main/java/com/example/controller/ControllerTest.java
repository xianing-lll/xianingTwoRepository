package com.example.controller;

import com.example.domain.Trade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/r")
public class ControllerTest {
    @RequestMapping("/r1")
    @PreAuthorize("hasAnyAuthority('库存管理员','总经理')")
    public String r1(){
        return "库存管理资源访问成功！";
    }
}
