package com.hyeonsik.notionadapter.service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "jwt")
@ConstructorBinding
public class JwtProvider {

    private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final SecureRandom RANDOM = new SecureRandom();
    static final JwtParser JWT_PARSER = Jwts.parserBuilder()
                                            .setSigningKey(KEY)
                                            .build();

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
        RANDOM.nextBytes(array);
        String refreshToken = new String(array, StandardCharsets.UTF_8);
        return createToken(refreshToken, refreshTokenExpiration);
    }

    public <T> T parseClaims(String jws, String claimName, Class<T> requiredType) {
        return JWT_PARSER.parseClaimsJws(jws)
                         .getBody()
                         .get(claimName, requiredType);
    }
}
