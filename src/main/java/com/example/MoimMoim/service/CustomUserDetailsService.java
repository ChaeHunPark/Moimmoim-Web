package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 주어진 사용자 이름을 기반으로 사용자 정보를 데이터베이스에서 로드하여 UserDetails 객체로 반환
     * 사용자가 데이터베이스에 존재하지 않으면 UsernameNotFoundException을 발생
     *
     * @param username 조회할 사용자의 이름
     * @return UserDetails 객체
     * @throws UsernameNotFoundException 주어진 사용자 이름으로 데이터베이스에서 사용자를 찾을 수 없는 경우 발생하는 예외
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        return memberRepository.findOneWithAuthoritiesByUsername(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    /**
     * 사용자 정보를 기반으로 Spring Security의 UserDetails 객체를 생성
     * 만약 사용자가 활성화되어 있지 않으면 RuntimeException을 발생
     *
     * @param username 사용자 이름
     * @param member   Member 객체 (사용자 정보)
     * @return UserDetails 객체
     * @throws RuntimeException 사용자가 활성화되어 있지 않은 경우 발생하는 예외
     */
    private org.springframework.security.core.userdetails.User createUser(String username, Member member) {
        if (!member.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority_name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(member.getUsername(),
                member.getPassword(),
                grantedAuthorities);
    }
}