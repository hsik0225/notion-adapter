package com.hyeonsik.notionadapter.controller;

import com.hyeonsik.notionadapter.dto.AccessTokenRequest;
import com.hyeonsik.notionadapter.service.OAuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final OAuthService oAuthService;

    @PostMapping("/login")
    public ResponseEntity<String> login(final AccessTokenRequest accessTokenRequest) {
        return ResponseEntity.ok(oAuthService.login(accessTokenRequest));
    }
}
