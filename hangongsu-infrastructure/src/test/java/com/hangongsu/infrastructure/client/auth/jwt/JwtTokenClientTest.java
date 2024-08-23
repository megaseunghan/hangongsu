package com.hangongsu.infrastructure.client.auth.jwt;

import com.hangongsu.core.dto.auth.response.TokenResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class JwtTokenClientTest {

    private JwtTokenClient jwtTokenClient = mock(JwtTokenClient.class);

    @Test
    void 유저_정보를_통해_엑세스_토큰을_발급할_수_있다() throws Exception {
        // given
        Long userId = 1L;

        TokenResponse token = mock(TokenResponse.class);

        given(jwtTokenClient.createAccessToken(userId)).willReturn(token);

        // when
        TokenResponse result = jwtTokenClient.createAccessToken(userId);

        // then
        assertThat(result).isNotNull();
        assertThat(token).isEqualTo(result);
    }

}