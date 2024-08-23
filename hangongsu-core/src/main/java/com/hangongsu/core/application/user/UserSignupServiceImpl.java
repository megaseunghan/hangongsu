package com.hangongsu.core.application.user;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.repository.UserRepository;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.user.in.UserSignupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSignupServiceImpl implements UserSignupService {

    private final UserRepository userRepository;

    @Override
    public User signup(UserInfoResponse userInfoResponse) {
        return userRepository.save(userInfoResponse.toEntity());
    }
}
