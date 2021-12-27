package com.hyeonsik.notionadapter.dto;


import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Getter;

@Getter
public class AccessTokenRequest {

    private final MultiValueMap<String, String> bodyValue;

    public AccessTokenRequest(String code) {
        bodyValue = new LinkedMultiValueMap<>();
        bodyValue.add("code", code);
        bodyValue.add("grant_type", "authorization_code");
    }
}
