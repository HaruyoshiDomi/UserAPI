package com.example.UserAPI.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    //秘密鍵
    private static final String SECRET = "my-super-secret-key-my-super-secret-key";
    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    //トークン生成
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000))) // 1時間有効
                .signWith(key)
                .compact();
    }

    //トークンからメールを抽出
    private static String extractEmail(String token) // アクセス修飾子をprivateに変更
    {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null; // トークンが無効な場合
        }
    }

    public static String validateToken(String token) {
    Claims claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();

    return claims.getSubject();
}
}
