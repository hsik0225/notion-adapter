package com.hyeonsik.notionadapter.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("로그인 통합 테스트")
    void loginIntegrationTest() throws Exception {

        // given
        String code = "temporary code of github";

        // when
        final ResultActions resultActions = mockMvc.perform(
                get("/login")
                        .param("code", code));

        // then
        resultActions.andExpect(status().isFound())
                     .andExpect(cookie().domain("accessToken", "localhost"))
                     .andExpect(cookie().maxAge("accessToken", 60));
    }
}
