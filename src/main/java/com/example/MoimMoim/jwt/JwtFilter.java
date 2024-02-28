package com.example.MoimMoim.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    //JWT(Jason Web Token) 토큰을 검증하고 해당 토큰을 사용하여 사용자의 인증 정보를 설정하는 역할
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // HTTP 요청에서 JWT 토큰 추출
        String jwt = resolveToken(httpServletRequest);

        // 현재 요청의 URI 확인
        String requestURI = httpServletRequest.getRequestURI();

        // 추출한 JWT 토큰이 유효하고, 검증을 통과하면
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            // JWT 토큰을 사용하여 사용자의 인증 정보를 가져옴
            Authentication authentication = tokenProvider.getAuthentication(jwt);

            // Security Context에 인증 정보를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * HTTP 요청에서 JWT 토큰을 추출하는 메서드
     *
     * @param request 현재 HTTP 요청
     * @return 추출된 JWT 토큰
     */
    private String resolveToken(HttpServletRequest request) {

        // Authorization 헤더에서 Bearer 토큰을 추출
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        // 추출한 Bearer 토큰이 있고, "Bearer "로 시작하면 실제 토큰 부분을 반환
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // 토큰이 없는 경우 null 반환
        return null;
    }
}
