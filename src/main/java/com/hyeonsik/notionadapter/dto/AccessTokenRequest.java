package com.hyeonsik.notionadapter.dto;


import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class AccessTokenRequest {

    private final Map<String, String> bodyValue;

    public AccessTokenRequest(String code) {
        bodyValue = new HashMap<>();
        bodyValue.put("code", code);
        bodyValue.put("grant_type", "authorization_code");
    }
}
