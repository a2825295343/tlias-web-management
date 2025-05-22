package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    // 生成JWT令牌
    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, System.getenv("JWT_SECRET_KEY")) // 指定加密算法，秘钥
                .addClaims(dataMap) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .compact(); // 生成JWT令牌
        System.out.println(jwt);
    }

    // 解析JWT令牌
    @Test
    public void testParseJwt() {
        String token = System.getenv("JWT_TOKEN");
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey("JWT_SECRET_KEY")
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(claims);
        } catch (Exception e) {
            System.out.println("解析JWT令牌出错: " + e.getMessage());
        }
    }
}