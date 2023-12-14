package com.zhangjian.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

public class JwtUtils {

    private static String secretKey = "your_secret";
    private static long expire = 3600 * 1000L;

    /**
     * 生成 JWT
     * @param payload
     * @return
     */
    public static String generateJwt(HashMap<String, Object> payload) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .addClaims(payload)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * 解析JWT
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
