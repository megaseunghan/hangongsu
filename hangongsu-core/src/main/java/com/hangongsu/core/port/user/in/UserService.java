package com.hangongsu.core.port.user.in;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;

public interface UserService {
    User registerIfAbsent(UserInfoResponse userInfoResponse);
}
