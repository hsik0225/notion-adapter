package com.hyeonsik.notionadapter.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "oauth")
@ConstructorBinding
public class OAuthProperty {

    private final String clientId;
    private final String clientSecrets;
    private final String tokenUri;
}
