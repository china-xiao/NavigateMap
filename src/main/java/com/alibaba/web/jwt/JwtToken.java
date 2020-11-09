package com.alibaba.web.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/9 17:09
 * @Description：JwtToken:实现shiro的AuthenticationToken接口的类JwtToken
 * @Version: 0.0.1
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
