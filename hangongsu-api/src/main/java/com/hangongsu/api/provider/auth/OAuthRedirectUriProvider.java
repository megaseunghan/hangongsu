package com.hangongsu.api.provider.auth;

import com.hangongsu.core.domain.user.vo.Platform;

import java.net.URI;

public interface OAuthRedirectUriProvider {
    URI generateRedirectUri(Platform platform);
}
