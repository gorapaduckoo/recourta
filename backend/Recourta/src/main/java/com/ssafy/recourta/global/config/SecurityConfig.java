package com.ssafy.recourta.global.config;

import com.ssafy.recourta.global.Filter.JwtAuthenticationFilter;
import com.ssafy.recourta.global.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig{

    private final JwtTokenUtil jwtTokenUtil;

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
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("http://localhost:5173/")
                .and()
                    .httpBasic().disable()
                    .authorizeRequests() //URL별 권한 접근제어 관리 옵션 시작점
                    .antMatchers("/user").permitAll()
                    .antMatchers("/user/**").authenticated()
                    .antMatchers("/css/**", "/images/**",
                        "/js/**", "/h2-console/**", "/profile").permitAll()
//                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
