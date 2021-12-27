package com.hyeonsik.notionadapter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("메인 페이지 출력 테스트")
    void welcomeTest() throws Exception {

        // when
        final ResultActions resultActions = mockMvc.perform(get("/"));

        // then
        resultActions.andExpect(status().isOk())
                     .andExpect(forwardedUrl("main.html"));
    }
}
