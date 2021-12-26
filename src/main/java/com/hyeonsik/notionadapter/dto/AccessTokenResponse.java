package com.hyeonsik.notionadapter.dto;

import java.util.Objects;

import com.hyeonsik.notionadapter.exception.OAuthException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenResponse {

    private final String accessToken;
    private final String scope;
    private final String tokenType;
    private final String errorDescription;

    public void validateToken() {
        if (Objects.isNull(accessToken)) {
            throw new OAuthException(errorDescription);
        }
    }
}
