package com.hyeonsik.notionadapter.core;

import java.net.URI;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ConfigurationProperties(prefix = "server")
public class ServerConfiguration {

    private final ServerProperties serverProperties;
    private final String domain = "localhost";

    public URI getUri() {
        return UriComponentsBuilder.newInstance()
                                   .scheme("http")
                                   .host(serverProperties.getAddress().getHostName())
                                   .port(serverProperties.getPort())
                                   .build()
                                   .toUri();
    }

    public String getDomain() {
        return domain;
    }
}
