package com.hangongsu.core.application.user;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.repository.UserRepository;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.user.in.UserSignupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserSignupServiceImpl.class})
class UserSignupServiceTest {

    @Autowired
    private UserSignupService userSignupService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void 소셜_유저정보를_통해서_회원가입을_진행하고_유저를_반환한다() throws Exception {
        // given
        UserInfoResponse forRegister = new UserInfoResponse(
                Platform.KAKAO,
                "hangongsu@test.com",
                "hangongsu",
                "hangongsu",
                1998L
        );

        User user = forRegister.toEntity();

        given(userRepository.save(any(User.class))).willReturn(user);

        // when
        User result = userSignupService.signup(forRegister);

        // then
        assertThat(user).isEqualTo(result);
    }
}