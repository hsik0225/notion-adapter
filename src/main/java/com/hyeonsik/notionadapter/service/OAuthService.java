package com.hyeonsik.notionadapter.service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import com.hyeonsik.notionadapter.dto.AccessTokenRequest;
import com.hyeonsik.notionadapter.dto.AccessTokenResponse;
import com.hyeonsik.notionadapter.dto.OAuthProperty;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuthService {

    private final OAuthProperty oAuthProperty;
    private final JwtProvider jwtProvider;
    private final WebClient webClient;

    public String login(final AccessTokenRequest accessTokenRequest) {
        final AccessTokenResponse accessTokenResponse = requestAccessToken(accessTokenRequest);
        accessTokenResponse.validateToken();
        return jwtProvider.createAccessToken(accessTokenResponse.getAccessToken());
    }

    private AccessTokenResponse requestAccessToken(AccessTokenRequest accessTokenRequest) {
        return webClient.post()
                        .uri(oAuthProperty.getTokenUri())
                        .headers(httpHeaders -> {
                            httpHeaders.setBasicAuth(oAuthProperty.getClientId(), oAuthProperty.getClientSecrets());
                            httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                            httpHeaders.setAcceptCharset(List.of(StandardCharsets.UTF_8));
                        })
                        .bodyValue(accessTokenRequest.getBodyValue())
                        .retrieve()
                        .bodyToMono(AccessTokenResponse.class)
                        .block();

    }
}
