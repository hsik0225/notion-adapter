package com.hyeonsik.notionadapter.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@ConfigurationProperties(prefix = "oauth")
@ConstructorBinding
public class OAuthProperties {

    private final String clientId;
    private final String clientSecrets;
    private final String tokenUri;
}
