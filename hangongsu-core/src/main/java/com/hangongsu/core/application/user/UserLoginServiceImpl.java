package com.hangongsu.core.application.user;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.repository.UserRepository;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.user.in.UserLoginService;
import com.hangongsu.core.port.user.in.UserSignupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserSignupService userSignupService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User login(UserInfoResponse userInfoResponse) {
        String email = userInfoResponse.email();

        return userRepository.findByEmail(email)
                .orElseGet(() -> userSignupService.signup(userInfoResponse));
    }
}
