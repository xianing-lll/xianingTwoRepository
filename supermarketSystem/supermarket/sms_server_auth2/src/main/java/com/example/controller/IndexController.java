package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.domain.EmployeeInformation;
import com.example.domain.Jwt;
import com.example.service.EmployeeInformationService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;

/**
 * 该类没用，仅为测试用
 */
@Controller
public class IndexController {


    @Autowired
    EmployeeInformationService employeeInformationService;

    @Autowired
    RestTemplate restTemplate=null;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        HttpServletRequest request,
                        HttpServletResponse httpServletResponse,Model model) throws IOException {
 //       System.out.println(username+"  "+password);
        String url = "http://localhost:9001/oauth/token?client_id=c1&client_secret=123456&grant_type=password&username="+username+"&password="+password;
        //请求内容{'username':'1003','password':'123456'}
        String JSONBody ="";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(JSONBody));
        CloseableHttpResponse response = httpClient.execute(httpPost);
//        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String jwtSuccess = EntityUtils.toString(entity, "UTF-8");
//        System.out.println(jwtSuccess);
        Jwt jwt= JSON.parseObject(jwtSuccess,Jwt.class);
//        System.out.println(jwt.getAccess_token());
//        System.out.println(jwt.getRefresh_token());
        response.close();
        httpClient.close();
        //将令牌和刷新令牌储存进cookie
        Cookie access_token_cookie=new Cookie("Authorization", jwt.getAccess_token());
        access_token_cookie.setMaxAge(10800);
        access_token_cookie.setHttpOnly(false); //防止js读取cookie信息
        access_token_cookie.setPath("/");
        access_token_cookie.setDomain("xianing");
//        Cookie refresh_token_cookie=new Cookie("refresh_token", jwt.getRefresh_token());
//        refresh_token_cookie.setMaxAge(259000);
//        refresh_token_cookie.setHttpOnly(true);
//        refresh_token_cookie.setPath("/");
//        refresh_token_cookie.setDomain("xianing");
        httpServletResponse.addCookie(access_token_cookie);
//        httpServletResponse.addHeader("Authorization", jwt.getAccess_token());

 //       httpServletResponse.addCookie(refresh_token_cookie);

        //获取用户信息
        EmployeeInformation employeeInformation=new EmployeeInformation();
        employeeInformation=employeeInformationService.findEmployeeById(Integer.parseInt(username));
        System.out.println(employeeInformation);

        model.addAttribute("employee",employeeInformation);

        return "index";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie access_token_cookie=new Cookie("access_token","");
        access_token_cookie.setPath("/");
        access_token_cookie.setDomain("xianing");
//        Cookie refresh_token_cookie=new Cookie("refresh_token", "");
//        refresh_token_cookie.setPath("/");
//        refresh_token_cookie.setDomain("xianing");
        response.addCookie(access_token_cookie);
  //      response.addCookie(refresh_token_cookie);
        return "login";
    }
    @RequestMapping("/hello")
    @ResponseBody
    public String gethello(){
        return "hello";
    }
}
