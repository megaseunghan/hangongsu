package com.hangongsu.core.application.user;

import com.hangongsu.core.domain.user.entity.User;
import com.hangongsu.core.domain.user.repository.UserRepository;
import com.hangongsu.core.domain.user.vo.Platform;
import com.hangongsu.core.dto.auth.response.UserInfoResponse;
import com.hangongsu.core.port.user.in.UserLoginService;
import com.hangongsu.core.port.user.in.UserSignupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserLoginServiceImpl.class})
class UserLoginServiceTest {

    @Autowired
    private UserLoginService userLoginService;

    @MockBean
    private UserSignupService userSignupService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void 같은_이메일로_회원가입_이력이_없으면_회원가입을_진행하고_유저를_반환한다() throws Exception {
        // given
        UserInfoResponse nonRegistered = new UserInfoResponse(
                Platform.KAKAO,
                "hangongsu@test.com",
                "hangongsu",
                "hangongsu",
                1998L
        );

        User expected = nonRegistered.toEntity();

        given(userRepository.findByEmail(nonRegistered.email())).willReturn(Optional.empty());
        given(userSignupService.signup(nonRegistered)).willReturn(expected);

        // when
        User result = userLoginService.login(nonRegistered);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 같은_이메일로_회원가입_이력이_있으면_유저를_반환한다() throws Exception {
        // given
        UserInfoResponse registered = new UserInfoResponse(
                Platform.KAKAO,
                "hangongsu@test.com",
                "hangongsu",
                "hangongsu",
                1998L
        );

        User expectedUser = new User(
                1L,
                Platform.KAKAO,
                "hangongsu@test.com",
                "hangongsu",
                "hangongsu",
                1998L
        );

        given(userRepository.findByEmail(registered.email())).willReturn(Optional.of(expectedUser));

        // when
        User result = userLoginService.login(registered);

        // then
        assertThat(expectedUser).isEqualTo(result);
    }

}