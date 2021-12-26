package com.hyeonsik.notionadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class NotionAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotionAdapterApplication.class, args);
    }

}
