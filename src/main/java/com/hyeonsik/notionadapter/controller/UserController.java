package com.hyeonsik.notionadapter.controller;

import javax.servlet.http.HttpServletResponse;

import com.hyeonsik.notionadapter.core.ServerConfiguration;
import com.hyeonsik.notionadapter.dto.AccessTokenRequest;
import com.hyeonsik.notionadapter.service.OAuthService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final OAuthService oAuthService;
    private final ServerConfiguration serverConfiguration;

    @GetMapping("/login")
    public ResponseEntity<Void> login(final AccessTokenRequest accessTokenRequest, final HttpServletResponse response) {
        final String jwsOfAccessToken = oAuthService.login(accessTokenRequest);
        final String accessTokenCookie = ResponseCookie.from("accessToken", jwsOfAccessToken)
                                                       .domain(serverConfiguration.getDomain())
                                                       .maxAge(60)
                                                       .build()
                                                       .toString();
        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie);

        return ResponseEntity.status(302)
                             .location(serverConfiguration.getUri())
                             .build();
    }
}
