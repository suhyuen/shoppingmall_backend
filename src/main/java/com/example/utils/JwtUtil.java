package com.example.utils;

import java.nio.charset.StandardCharsets;
import java.sql.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.signing.key}")
    private String signingKey;

    public String createToken(int userUid, String role, Long expirationMs){
        SecretKey key = Keys.hmacShaKeyFor((signingKey.getBytes(StandardCharsets.UTF_8)));
        return Jwts.builder()
                .claim("userUid", userUid)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // token 에서 role 꺼내오기
    public String getRole(String token){
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.substring("Bearer ".length()))
                .getBody()
                .get("role", String.class);

    }

    //token 에서 userUid 가지고 오기
    public int getUserUid (String token){
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.substring("Bearer ".length()))
                .getBody()
                .get("userUid", Integer.class);
    }

    public boolean isExpired(String token){
        SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.substring("Bearer ".length()))
                .getBody()
                .getExpiration()
                .before(new java.util.Date());
    }
}
