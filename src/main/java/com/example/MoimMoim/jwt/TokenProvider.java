package com.example.MoimMoim.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


// TokenProvider 클래스는 JWT 토큰을 생성, 검증하고 사용자 인증 정보를 추출하는 역할을 수행
@Component
public class TokenProvider implements InitializingBean {

    // Logger를 초기화
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    // JWT에서 권한 정보를 담는 키 이름을 정의
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long tokenValidityInMilliseconds;
    private Key key;

    /**
     * TokenProvider 클래스의 생성자
     *
     * @param secret                JWT 시크릿 키
     * @param tokenValidityInSeconds JWT 토큰 유효 기간(초)
     */
    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() {
        // 빈이 초기화된 후 호출되는 메서드로, secret 값을 기반으로 Key를 생성
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 인증 정보를 기반으로 JWT 토큰을 생성
     *
     * @param authentication 인증 정보
     * @return 생성된 JWT 토큰
     */
    public String createToken(Authentication authentication) {
        // 주어진 인증 정보를 사용하여 JWT 토큰을 생성.

        // 사용자의 권한 정보를 ","로 구분하여 문자열로 변환.
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 현재 시간과 토큰의 유효 기간을 계산하여 설정
        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        // JWT 토큰을 빌더를 통해 생성하고 반환
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    /**
     * 주어진 JWT 토큰으로부터 인증 정보를 추출
     *
     * @param token JWT 토큰
     * @return 추출된 인증 정보
     */
    public Authentication getAuthentication(String token) {

        // 토큰의 클레임을 파싱합니다.
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // 권한 정보를 추출하고 GrantedAuthority 리스트로 변환
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // 추출된 정보를 기반으로 사용자 객체를 생성하고 UsernamePasswordAuthenticationToken으로 반환
        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * 주어진 JWT 토큰의 유효성을 검증
     *
     * @param token JWT 토큰
     * @return 유효한 경우 true, 그렇지 않은 경우 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
