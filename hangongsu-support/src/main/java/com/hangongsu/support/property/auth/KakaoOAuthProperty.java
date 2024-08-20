package com.hangongsu.support.property.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oauth.kakao")
public class KakaoOAuthProperty {
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String authBaseUri;
    private final String apiBaseUri;
}