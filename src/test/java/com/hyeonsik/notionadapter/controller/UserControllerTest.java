package com.hyeonsik.notionadapter.controller;

import java.net.URI;

import com.hyeonsik.notionadapter.core.ServerConfiguration;
import com.hyeonsik.notionadapter.service.OAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(ServerConfiguration.class)
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

        final String domain = "localhost";
        final URI redirectUrl = URI.create("http://localhost:8081");

        // when
        final ResultActions result = mockMvc.perform(
                get("/login")
                        .param("code", "test-code"));

        // then
        String cookieName = "accessToken";
        result.andExpect(status().isFound())
              .andExpect(redirectedUrl(redirectUrl.toString()))
              .andExpect(cookie().value(cookieName, jws))
              .andExpect(cookie().domain(cookieName, domain))
              .andExpect(cookie().maxAge(cookieName, 60));
    }
}
