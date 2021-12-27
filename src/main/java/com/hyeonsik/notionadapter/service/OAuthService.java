package com.hyeonsik.notionadapter.service;

import java.util.List;

import com.hyeonsik.notionadapter.core.OAuthProperties;
import com.hyeonsik.notionadapter.dto.AccessTokenRequest;
import com.hyeonsik.notionadapter.dto.AccessTokenResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuthService {

    private final OAuthProperties oAuthProperties;
    private final JwtProvider jwtProvider;

    public String login(final AccessTokenRequest accessTokenRequest) {
        final AccessTokenResponse accessTokenResponse = requestAccessToken(accessTokenRequest);
        accessTokenResponse.validateToken();
        return jwtProvider.createAccessToken(accessTokenResponse.getAccessToken());
    }

    private AccessTokenResponse requestAccessToken(AccessTokenRequest accessTokenRequest) {
        return WebClient.create()
                        .post()
                        .uri(oAuthProperties.getTokenUri())
                        .headers(httpHeaders -> {
                            httpHeaders.setBasicAuth(oAuthProperties.getClientId(), oAuthProperties.getClientSecrets());
                            httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                        })
                        .bodyValue(accessTokenRequest.getBodyValue())
                        .retrieve()
                        .bodyToMono(AccessTokenResponse.class)
                        .block();

    }
}
