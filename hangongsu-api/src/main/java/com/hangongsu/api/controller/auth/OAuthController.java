package com.hangongsu.api.controller.auth;

import com.hangongsu.api.provider.auth.OAuthRedirectUriProvider;
import com.hangongsu.core.application.auth.OAuthLoginService;
import com.hangongsu.core.domain.user.vo.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/oauth")
public class OAuthController {

    private final OAuthRedirectUriProvider redirectUriProvider;
    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/{platform}/login")
    public ResponseEntity<Void> redirectLoginPage(@PathVariable String platform) {
        URI redirectUri = redirectUriProvider.generateRedirectUri(Platform.from(platform));
        return ResponseEntity.created(redirectUri).build();
    }

    @GetMapping("/{platform}/callback")
    public OAuthLoginResponse callback(@RequestParam(name = "code") String authorizationCode, @PathVariable String platform) {
        return oAuthLoginService.login(authorizationCode, platform);
    }
}

