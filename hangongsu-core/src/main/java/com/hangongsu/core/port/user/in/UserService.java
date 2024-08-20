package com.hangongsu.core.port.user.in;

import com.hangongsu.core.dto.auth.response.LoginResponse;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;

public interface UserService {
    LoginResponse registerIfAbsent(UserInfoResponse userInfoResponse);
}
