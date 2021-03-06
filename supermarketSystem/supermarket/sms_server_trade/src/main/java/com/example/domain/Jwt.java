package com.example.domain;

import lombok.Data;

@Data
public class Jwt {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    private String jti;
}
