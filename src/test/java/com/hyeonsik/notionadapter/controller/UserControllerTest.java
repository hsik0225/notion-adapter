package com.hyeonsik.notionadapter.controller;

import com.hyeonsik.notionadapter.service.OAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OAuthService oAuthService;

    @Test
    @DisplayName("로그인 요청 및 응답 테스트")
    void loginTest() throws Exception {

        // given
        final String jws = "a.b.c";
        given(oAuthService.login(any())).willReturn(jws);

        // when
        final ResultActions result = mockMvc.perform(
                post("/login")
                        .param("code", "test-code"));

        // then
        result.andExpect(status().isOk())
              .andExpect(content().string(jws));
    }
}
