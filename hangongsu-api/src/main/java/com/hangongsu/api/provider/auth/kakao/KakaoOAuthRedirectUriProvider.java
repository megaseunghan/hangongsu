package com.hangongsu.api.provider.auth.kakao;

import com.hangongsu.api.provider.auth.OAuthRedirectUriProvider;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.support.property.auth.KakaoOAuthProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class KakaoOAuthRedirectUriProvider implements OAuthRedirectUriProvider {

    private final KakaoOAuthProperty kakaoOAuthProperty;

    @Override
    public Platform getPlatform() {
        return Platform.KAKAO;
    }

    @Override
    public URI generateRedirectUri() {
        return UriComponentsBuilder.fromUri(URI.create(kakaoOAuthProperty.getAuthBaseUri() + "/oauth/authorize"))
                .queryParam("client_id", kakaoOAuthProperty.getClientId())
                .queryParam("client_secret", kakaoOAuthProperty.getClientSecret())
                .queryParam("redirect_uri", kakaoOAuthProperty.getRedirectUri())
                .queryParam("response_type", "code")
                .build().toUri();
    }
}
