package com.hyeonsik.notionadapter.integration;

import com.hyeonsik.notionadapter.dto.AccessTokenResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeGitHubOAuthController {

    @PostMapping("/fake/login/oauth/access_token")
    public AccessTokenResponse fakeLogin() {
        return AccessTokenResponse.builder()
                                  .tokenType("Bearer")
                                  .accessToken("test-access-token")
                                  .build();
    }
}
