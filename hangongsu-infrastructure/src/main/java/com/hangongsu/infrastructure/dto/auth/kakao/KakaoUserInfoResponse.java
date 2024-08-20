package com.hangongsu.infrastructure.dto.auth.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoUserInfoResponse(
        Long id,
        String connectedAt,
        Properties properties,
        KakaoAccount kakaoAccount
) {
    public UserInfoResponse toDomain() {
        return new UserInfoResponse(
                Platform.KAKAO,
                kakaoAccount().email(),
                kakaoAccount().name(),
                properties().nickname(),
                kakaoAccount().birthyear()
        );
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Properties(
            String nickname
    ) {

    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record KakaoAccount(
            boolean profileNicknameNeedsAgreement,
            Profile profile,
            boolean nameNeedsAgreement,
            String name,
            boolean hasEmail,
            boolean emailNeedsAgreement,
            boolean isEmailValid,
            boolean isEmailVerified,
            String email,
            boolean hasBirthyear,
            boolean birthyearNeedsAgreement,
            long birthyear
    ) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record Profile(
            String nickname,
            boolean isDefaultNickname
    ) {
    }
}
