package com.hyeonsik.notionadapter.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hyeonsik.notionadapter.exception.OAuthException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    public void validateToken() {
        if (Objects.isNull(accessToken)) {
            throw new OAuthException(errorDescription);
        }
    }
}
