package com.hyeonsik.notionadapter.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;
import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public class JwtProvider {

    static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public String createAccessToken(String accessToken) {
        return createToken(accessToken, accessTokenExpiration);
    }

    private String createToken(String token, long expiration) {
        return Jwts.builder()
                   .claim("token", token)
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + expiration))
                   .signWith(KEY)
                   .compact();
    }

    public String createRefreshToken() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String refreshToken = new String(array, StandardCharsets.UTF_8);
        return createToken(refreshToken, refreshTokenExpiration);
    }

    public void validateToken(String jws) {
        Jwts.parserBuilder()
            .setSigningKey(KEY)
            .build()
            .parseClaimsJws(jws);
    }
}
