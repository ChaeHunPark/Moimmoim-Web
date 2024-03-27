package com.example.MoimMoim.config;

import com.example.MoimMoim.jwt.JwtAccessDeniedHandler;
import com.example.MoimMoim.jwt.JwtAuthenticationEntryPoint;
import com.example.MoimMoim.jwt.JwtSecurityConfig;
import com.example.MoimMoim.jwt.TokenProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests // 접근제한 설정 시작
//                                .requestMatchers("/api/register").authenticated() // 인증해야 접근가능
                                .requestMatchers("/api/main","/api/login","/api/registeruser","/api/logins").permitAll() //인증 필요 없음
                                .requestMatchers("/api/check-id","/api/check-nickname","/api/register", "/api/authenticate","/api/user",
                                                        "/api/free-board", "/api/moim-board","/api/get-all-free-boards").permitAll()
                )
                                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                                .sessionManagement(sessionManagement ->
                                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                )


                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .with(new JwtSecurityConfig(tokenProvider), customizer -> {});;
        return http.build();
    }


}
