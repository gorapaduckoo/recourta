package com.ssafy.recourta.domain.auth.service;

import com.ssafy.recourta.domain.auth.dto.TokenDto;
import com.ssafy.recourta.domain.user.entity.User;
import org.springframework.stereotype.Service;

public interface AuthService {

    TokenDto.Refresh createTokens(User user);
    TokenDto.Refresh refreshTokens(String accessToken, String refreshToken) throws Exception;
}
