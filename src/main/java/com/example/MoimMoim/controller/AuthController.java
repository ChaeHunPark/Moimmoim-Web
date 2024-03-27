package com.example.MoimMoim.controller;

import com.example.MoimMoim.dto.LoginDto;
import com.example.MoimMoim.dto.TokenDto;
import com.example.MoimMoim.jwt.JwtFilter;
import com.example.MoimMoim.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    // AuthController 생성자
    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }


    /**
     * 사용자가 입력한 아이디와 비밀번호로 인증을 수행하고, 성공 시 JWT 토큰을 생성하여 반환
     *
     * @param loginDto 사용자의 아이디와 비밀번호를 담은 LoginDto 객체
     * @return JWT 토큰과 함께의 ResponseEntity
     */
    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        // 사용자가 입력한 아이디와 비밀번호로 UsernamePasswordAuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPassword()); //

        try {
            // 인증을 수행하고, 성공하면 Authentication 객체가 SecurityContext에 설정됨
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 토큰 생성
            String jwt = tokenProvider.createToken(authentication);

            // HTTP 응답 헤더에 토큰을 담아 클라이언트에게 반환
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);

        } catch (AuthenticationException e){
            // 인증 실패 시, 예외 처리
            // 여기에서는 간단하게 401 Unauthorized 응답 반환
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
