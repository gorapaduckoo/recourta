package com.ssafy.recourta.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig{

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.cors().disable()
                .csrf().disable()  // 위조 요청 방지
                .formLogin().disable()
                .headers().frameOptions().disable()
                // h2-console 화면을 사용하기 위해 옵션을 disable
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .httpBasic().disable()
                    .authorizeRequests() //URL별 권한 접근제어 관리 옵션 시작점
                    .antMatchers("/**", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**", "/profile").permitAll()
                    .anyRequest().authenticated();
        return http.build();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
