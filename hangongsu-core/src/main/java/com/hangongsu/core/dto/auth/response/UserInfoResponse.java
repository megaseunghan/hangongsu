package com.hangongsu.core.dto.auth.response;


import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.vo.Platform;

public record UserInfoResponse(
        Platform platform,
        String email,
        String name,
        String nickname,
        Long birthYear
) {

    public User toEntity() {
        return new User(null, platform, email, name, nickname, birthYear);
    }
}
