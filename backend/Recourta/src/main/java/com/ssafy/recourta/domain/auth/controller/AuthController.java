package com.ssafy.recourta.domain.auth.controller;

import com.ssafy.recourta.domain.auth.dto.TokenDto;
import com.ssafy.recourta.domain.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto.Refresh> refreshTokens(@RequestHeader(value="Refresh") String refreshToken, @RequestHeader(value="Authorization") String accessToken) throws Exception {
        TokenDto.Refresh response = authService.refreshTokens(accessToken, refreshToken);
        return ResponseEntity.ok().body(response);
        // 만료된 경우 예외처리?
    }
}
