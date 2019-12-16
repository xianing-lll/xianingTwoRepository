package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenConfig {

    //密钥串
    private String SIGNING_KEY="auth123";//signing_key
    @Bean
    public TokenStore tokenStore(){
        //jwt令牌存储方案
        return new JwtTokenStore(accessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter=new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);//对称密钥，资源服务器使用该密钥来验证
        return converter;
    }


//    //令牌储存策略,使用令牌
//    @Bean
//    public TokenStore tokenStore(){
//        //内存方式，生成普通令牌
//        return new InMemoryTokenStore();
//    }
}
