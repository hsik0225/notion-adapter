package com.hyeonsik.notionadapter.core;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OAuthPropertiesTest extends CoreTest {

    @Autowired
    private OAuthProperties oAuthProperties;

    @Test
    @DisplayName("ClientId 정상 반환 테스트")
    void getClientIdTest() {

        // when
        final String clientId = oAuthProperties.getClientId();

        // then
        assertThat(clientId).isEqualTo("id");
    }

    @Test
    @DisplayName("Client Secrets 정상 반환 테스트")
    void getClientSecretsTest() {

        // when
        final String clientSecrets = oAuthProperties.getClientSecrets();

        // then
        assertThat(clientSecrets).isEqualTo("secrets");
    }

    @Test
    @DisplayName("Token URI 정상 반환 테스트")
    void getTokenUriTest() {

        // when
        final String tokenUri = oAuthProperties.getTokenUri();

        // then
        assertThat(tokenUri).isEqualTo("https://github.com/login/oauth/access_token");
    }
}
