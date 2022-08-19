package com.ssafy.recourta.domain.auth.controller;

import com.ssafy.recourta.domain.auth.dto.TokenDto;
import com.ssafy.recourta.domain.auth.service.AuthService;
import com.ssafy.recourta.domain.user.entity.User;
import com.ssafy.recourta.domain.user.service.UserService;
import com.ssafy.recourta.global.exception.AuthException;
import com.ssafy.recourta.global.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenDto.Refresh> refreshTokens(@RequestHeader(value="Refresh") String refreshToken, @RequestHeader(value="Authorization") String accessToken) {
        try {
            TokenDto.Refresh response = authService.refreshTokens(accessToken, refreshToken);
            return ResponseEntity.ok().body(response);
        } catch(AuthException.RefreshTokenExpired e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<TokenDto.Refresh> deleteTokens(@RequestHeader(value="Authorization") String accessToken) {
        String userId = jwtTokenUtil.getUserIdFromToken(accessToken);
        User user = userService.getUser(Integer.parseInt(userId));
        TokenDto.Refresh response = authService.deleteTokens(user);
        return ResponseEntity.ok().body(response);
    }
}
