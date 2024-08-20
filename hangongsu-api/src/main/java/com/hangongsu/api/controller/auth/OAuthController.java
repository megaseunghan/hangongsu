package com.hangongsu.api.controller.auth;

import com.hangongsu.api.provider.auth.OAuthRedirectUriProviderComposite;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.LoginResponse;
import com.hangongsu.core.port.auth.in.OAuthLoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/oauth")
public class OAuthController {

    private final OAuthRedirectUriProviderComposite redirectUriProvider;
    private final OAuthLoginService oAuthLoginService;

    @GetMapping("/{platform}/login")
    public ResponseEntity<Void> redirectLoginPage(@PathVariable String platform, HttpServletResponse response) throws IOException, IllegalArgumentException {
        URI redirectUri = redirectUriProvider.generateRedirectUri(Platform.from(platform));
        response.sendRedirect(redirectUri.toString());

        return ResponseEntity.created(redirectUri).build();
    }

    @GetMapping("/{platform}/callback")
    public ResponseEntity<LoginResponse> callback(@RequestParam(name = "code") String authorizationCode, @PathVariable String platform) {
        LoginResponse response = oAuthLoginService.login(authorizationCode, Platform.from(platform));
        return ResponseEntity.ok(response);
    }
}

