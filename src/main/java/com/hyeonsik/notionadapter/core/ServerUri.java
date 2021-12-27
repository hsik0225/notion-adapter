package com.hyeonsik.notionadapter.core;

import java.net.URI;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ServerUri {

    private final ServerProperties serverProperties;

    public URI getUri() {
        return UriComponentsBuilder.newInstance()
                                   .scheme("http")
                                   .host(serverProperties.getAddress().getHostName())
                                   .port(serverProperties.getPort())
                                   .build()
                                   .toUri();
    }

    public String getDomain() {
        return serverProperties.getAddress().getHostName();
    }
}
