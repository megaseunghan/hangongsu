package com.hangongsu.api.provider.auth.kakao;

import com.hangongsu.api.provider.auth.OAuthRedirectUriProvider;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.support.property.auth.KakaoOAuthProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class KakaoOAuthRedirectUriProvider implements OAuthRedirectUriProvider {

    private final KakaoOAuthProperty kakaoOAuthProperty;
    @Override
    public URI generateRedirectUri(Platform platform) {
        return URI.create(new StringBuilder(kakaoOAuthProperty.getAuthBaseUri())
                .append("/oauth/authorize")
                .append("?client_id=").append(kakaoOAuthProperty.getClientId())
                .append("&client_secret=").append(kakaoOAuthProperty.getClientSecret())
                .append("&redirect_uri=").append(kakaoOAuthProperty.getRedirectUri())
                .append("&response_type=").append("code")
                .toString()
        );
    }
}
