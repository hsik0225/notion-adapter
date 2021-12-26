package com.hyeonsik.notionadapter.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import org.assertj.core.api.ThrowableAssert;

import static com.hyeonsik.notionadapter.service.JwtProvider.KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtProviderTest {

    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider(10000, 10000);
    }

    @Nested
    class TokenValidationTest {

        @Test
        @DisplayName("만료 시간이 지날 경우 예외 발생")
        void expirationTest() {

            // given
            jwtProvider = new JwtProvider(-10000, -10000);
            final String accessToken = jwtProvider.createAccessToken("test");

            // when
            ThrowableAssert.ThrowingCallable callable = () -> jwtProvider.validateToken(accessToken);

            // then
            assertThatThrownBy(callable).isExactlyInstanceOf(ExpiredJwtException.class);
        }

        @Test
        @DisplayName("키가 다를 경우 예외 발생")
        void differentKeyTest() {

            // given
            final String jws = Jwts.builder()
                                   .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                                   .compact();

            // when
            ThrowableAssert.ThrowingCallable callable = () -> jwtProvider.validateToken(jws);

            // then
            assertThatThrownBy(callable).isExactlyInstanceOf(SignatureException.class);
        }
    }

    private static <T> T parseClaim(String jws, String claim, Class<T> requiredType) {
        return Jwts.parserBuilder()
                   .setSigningKey(KEY)
                   .build()
                   .parseClaimsJws(jws)
                   .getBody()
                   .get(claim, requiredType);
    }

    @Test
    @DisplayName("AccessToken이 유효한 JWS로 생성되는지 확인")
    void createAccessTokenTest() {

        // given
        final String token = "test";

        // when
        final String accessToken = jwtProvider.createAccessToken(token);

        // then
        assertThat(parseClaim(accessToken, "token", String.class)).isEqualTo("test");
    }

    @Test
    @DisplayName("RefreshToken이 유효한 JWS로 생성되는지 확인")
    void createRefreshTokenTest() {

        // when
        final String accessToken = jwtProvider.createRefreshToken();

        // then
        assertThat(parseClaim(accessToken, "token", String.class)).isNotNull();
    }
}
