package com.example.tchedule.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "MySecretKeyForJwtAuthenticationMySecretKey"; // ✅ 32바이트 이상
    private static final long EXPIRATION_TIME = 86400000; // 1일 (밀리초)

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 🔹 JWT 생성 메서드
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 JWT 검증 메서드
    public String validateToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (JwtException e) {
            return null; // 유효하지 않은 토큰
        }
    }
}
