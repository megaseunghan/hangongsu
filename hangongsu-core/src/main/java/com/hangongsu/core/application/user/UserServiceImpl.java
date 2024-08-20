package com.hangongsu.core.application.user;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.repository.UserRepository;
import com.hangongsu.core.dto.auth.response.LoginResponse;
import com.hangongsu.core.dto.auth.response.TokenResponse;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.auth.out.TokenClient;
import com.hangongsu.core.port.user.in.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TokenClient tokenClient;

    @Override
    @Transactional
    public LoginResponse registerIfAbsent(UserInfoResponse userInfoResponse) {
        String email = userInfoResponse.email();

        User user = userRepository.findByEmail(email)
                .orElse(userInfoResponse.toEntity());

        userRepository.save(user);
        TokenResponse token = tokenClient.createAccessToken(userInfoResponse);

        return LoginResponse.from(user.getUserId(), token);
    }
}
