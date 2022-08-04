package com.ssafy.recourta.domain.global.util;

import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.global.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class JwtTokenUtilTest {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Test
    public void validateTokenTest() {
        User user = User.builder()
                .userId(1)
                .isStudent(1)
                .build();

        Claims claims = jwtTokenUtil.getClaims(user);
        String token = jwtTokenUtil.generateAccessToken(claims);
        assertThat(jwtTokenUtil.validateToken(token)).isEqualTo(true);
        // JwtTokenUtil에서 ACCESS_TOKEN_VALIDITY = 3L(3초)로 바꾸고 테스트 실행
        try {
            Thread.sleep(5000); // 5초
            assertThat(jwtTokenUtil.validateToken(token)).isEqualTo(false);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void generateAccessTokenTest() throws Exception {
        User user = User.builder()
                .userId(1)
                .isStudent(1)
                .build();

        Claims claims = jwtTokenUtil.getClaims(user);
        String token = jwtTokenUtil.generateAccessToken(claims);
        // 생성된 token을 jwt.io에서 decode 하여 정보 확인 가능
        System.out.println(token);
    }

    @Test
    public void getUserIdFromTokenTest() throws Exception {
        User user = User.builder()
                .userId(1)
                .isStudent(1)
                .build();

        Claims claims = jwtTokenUtil.getClaims(user);
        String token = jwtTokenUtil.generateAccessToken(claims);

        assertThat(jwtTokenUtil.getUserIdFromToken(token)).isEqualTo("1");
    }
}
