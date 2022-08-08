package com.ssafy.recourta.domain.auth.service;

import com.ssafy.recourta.domain.auth.dto.TokenDto;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.global.util.JwtTokenUtil;
import com.ssafy.recourta.global.util.RedisUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private final RedisUtil redisUtil;

    @Override
    public TokenDto.Refresh createTokens(User user) {
        String accessToken = jwtTokenUtil.generateAccessToken(jwtTokenUtil.getClaims(user));
        String refreshToken = jwtTokenUtil.generateRefreshToken();

        String userId = String.valueOf(user.getUserId());
        redisUtil.setDataExpire(userId, refreshToken, 3 * 24 * 60 * 60L);

        return TokenDto.Refresh.builder()
                                .accessToken(accessToken)
                                .refreshToken(refreshToken)
                                .build();
    }

    @Override
    public TokenDto.Refresh refreshTokens(String accessToken, String refreshToken) throws Exception {
        String userId = jwtTokenUtil.getUserIdFromToken(accessToken);

        if(jwtTokenUtil.validateToken(refreshToken) && refreshToken.equals(redisUtil.getData(userId))) {
            User user = User.builder().userId(Integer.parseInt(userId)).build();
            Claims claims = jwtTokenUtil.getClaims(user);
            String newAccessToken = jwtTokenUtil.generateAccessToken(claims);
            String newRefreshToken = jwtTokenUtil.generateRefreshToken();
            redisUtil.setDataExpire(userId, newRefreshToken, 3 * 24 * 60 * 60L);    // JwtTokenUtil과 똑같이 설정

            return TokenDto.Refresh.builder()
                                    .accessToken(newAccessToken)
                                    .refreshToken(newRefreshToken)
                                    .build();
        } else {
            throw new Exception();
        }
    }
}
