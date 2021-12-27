package com.hyeonsik.notionadapter.core;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ServerConfigurationTest extends CoreTest {

    @Autowired
    private ServerConfiguration serverConfiguration;

    @Test
    @DisplayName("URI 정상 반환 테스트")
    void getUriTest() {

        // when
        final String uri = serverConfiguration.getUri().toString();

        // then
        assertThat(uri).isEqualTo("http://localhost:8081");
    }

    @Test
    @DisplayName("URI 정상 반환 테스트")
    void getDomainTest() {

        // when
        final String domain = serverConfiguration.getDomain();

        // then
        assertThat(domain).isEqualTo("localhost");
    }
}
