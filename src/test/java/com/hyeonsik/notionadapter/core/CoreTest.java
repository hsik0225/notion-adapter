package com.hyeonsik.notionadapter.core;

import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@WebMvcTest(
        includeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = {"com.hyeonsik.notionadapter.core.*"}
        ),
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Controller.class
        ),
        excludeAutoConfiguration = {
                AutoConfigureCache.class,
                AutoConfigureWebMvc.class,
                AutoConfigureMockMvc.class
        }
)
public abstract class CoreTest {

}
