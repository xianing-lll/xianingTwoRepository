package com.example.domain;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class OauthCode {
    private Timestamp createTime ;
    private  String code;
    private Byte[]  authentication;
}
