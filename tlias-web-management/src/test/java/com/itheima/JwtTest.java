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
                .signWith(SignatureAlgorithm.HS256, "ZmVlc2gudG9wNjY2dGxpYXNpdGhlaW1hZmVlc2gudG9wNjY2dGxpYXM=") // 指定加密算法，秘钥
                .addClaims(dataMap) // 添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .compact(); // 生成JWT令牌
        System.out.println(jwt);
    }

    // 解析JWT令牌
    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc0Nzg0MjUzNX0.KARECQD8l56aavWJXWFP4llxRj3JKI4CvMspSG0kHqI";
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey("ZmVlc2gudG9wNjY2dGxpYXNpdGhlaW1hZmVlc2gudG9wNjY2dGxpYXM=")
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(claims);
        } catch (Exception e) {
            System.out.println("解析JWT令牌出错: " + e.getMessage());
        }
    }
}