package com.zhangjian;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

// 测试内容与 spring无关时，为了更快的执行测试，可以注释掉 spring默认的测试注解
//@SpringBootTest
class OaBackendApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void generateJwt(){
        // payload
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("name", "jack");
        payload.put("province", "dekesasi");

        String jwt = Jwts.builder()
                .addClaims(payload) // 添加载荷
                .signWith(SignatureAlgorithm.HS256, "secretKey") // 设置 加密算法 和 秘钥
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间为 1 小时
                .compact();

        System.out.println(jwt); // eyJhbGciOiJIUzI1NiJ9.eyJwcm92aW5jZSI6ImRla2VzYXNpIiwibmFtZSI6ImphY2siLCJleHAiOjE3MDAxNTM0NjB9.02D-A7QBUJtd6C9lSR3mo7MEyiRINutdSCfNKPnwwh8

    }

    @Test
    void parseJwt(){
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJwcm92aW5jZSI6ImRla2VzYXNpIiwibmFtZSI6ImphY2siLCJleHAiOjE3MDAxNTM0NjB9.02D-A7QBUJtd6C9lSR3mo7MEyiRINutdSCfNKPnwwh8";
        Claims claims = Jwts.parser()
                .setSigningKey("secretKey")
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println(claims);
    }

}
