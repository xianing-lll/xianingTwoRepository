package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID="CASH_REGISTER";

    @Autowired
    TokenStore tokenStore;
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)//资源id
                .tokenStore(tokenStore)//让资源服务自己验证token
                //.tokenServices(tokenService())//验证令牌的服务
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasAnyScope('ROLE_ADMIN,ROLE_USER,ROLE_API')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//session不记录
    }
    //资源服务令牌解析服务，远程解析
//    @Bean
//    public ResourceServerTokenServices tokenService(){
//        //使用远程服务请求授权服务器验证token，必须指定校验token的url、client_id、client_secret
//        RemoteTokenServices services=new RemoteTokenServices();
//        services.setCheckTokenEndpointUrl("http://localhost:9001/oauth/check_token");
//        services.setClientId("c1");
//        services.setClientSecret("123456");
//        return services;
//    }
}
