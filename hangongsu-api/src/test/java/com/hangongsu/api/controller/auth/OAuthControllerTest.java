package com.hangongsu.api.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangongsu.api.provider.auth.OAuthRedirectUriProviderComposite;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.LoginResponse;
import com.hangongsu.core.port.auth.in.OAuthLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(OAuthController.class)
class OAuthControllerTest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    protected OAuthRedirectUriProviderComposite redirectUriProvider;
    @MockBean
    protected OAuthLoginService oAuthLoginService;

    @BeforeEach
    void setup(WebApplicationContext context) {
        MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @Test
    void 플랫폼에_맞는_AuthorizationCode를_받기위한_요청_URI를_반환한다() throws Exception {

        // given
        // 나중에 Fixture 변경
        Platform platform = Platform.KAKAO;

        URI expectUri = UriComponentsBuilder.fromUri(URI.create("www.provider.com" + "/oauth/authorize"))
                .queryParam("client_id", "test_id")
                .queryParam("client_secret", "test_secret")
                .queryParam("redirect_uri", "test_front_redirect_uri")
                .queryParam("response_type", "code")
                .build().toUri();

        given(redirectUriProvider.generateRedirectUri(platform)).willReturn(expectUri);

        // when
        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/oauth/{platform}/login", platform.name().toLowerCase())
        );

        // then
        resultActions.andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(expectUri.toString()));
    }

    @Test
    void 콜백을_호출하여_로그인하고_응답값을_반환한다() throws Exception {
        // given

        // 나중에 Fixture 변경
        Platform platform = Platform.KAKAO;

        String authorizationCode = "authCode";
        LoginResponse expectLoginResponse = new LoginResponse(1L, "AccessToken!");

        given(oAuthLoginService.login(authorizationCode, platform)).willReturn(expectLoginResponse);

        // when
        ResultActions resultActions = mockMvc.perform(
                get("/api/v1/oauth/{platform}/callback", platform.name().toLowerCase())
                        .param("code", authorizationCode)
        );

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expectLoginResponse)))
                .andDo(print());
    }
}