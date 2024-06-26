package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Authority;
import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.dto.MemberDto;
import com.example.MoimMoim.exception.NotFoundMemberException;
import com.example.MoimMoim.repository.MemberRepository;
import com.example.MoimMoim.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;


@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // 회원가입 서비스
    @Transactional
    public MemberDto signUp(MemberDto memberDto){

        Authority authority = Authority.builder()
                .authority_name("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .name(memberDto.getName())
                .birthdate(memberDto.getBirthdate())
                .gender(memberDto.getGender())
                .nickname(memberDto.getNickname())
                .question(memberDto.getQuestion())
                .answer(memberDto.getAnswer())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();



        return MemberDto.from(memberRepository.save(member));
    }

    @Override
    public boolean isUsernameAlreadyInUse(String username) {
        return memberRepository.existsByUsername(username);
    }

    @Override
    public boolean isNicknameAlreadyInUse(String nickname){
        return memberRepository.existsByNickname(nickname);
    }

    /**
     * ID를 기반으로 사용자와 해당 사용자의 권한을 조회하는
     *
     * @param username 조회할 사용자의 ID
     * @return MemberDto 객체 (사용자 정보 및 권한 정보 포함)
     */
    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String username) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    /**
     * 현재 인증된 사용자의 ID를 기반으로 사용자와 해당 사용자의 권한을 조회하는 메서드
     * 현재 인증된 사용자의 ID가 없거나 사용자가 존재하지 않으면 예외를 발생
     *
     * @return 현재 사용자의 MemberDto 객체 (사용자 정보 및 권한 정보 포함)
     * @throws NotFoundMemberException 사용자를 찾을 수 없을 경우 발생하는 예외
     */
    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(
                SecurityUtil.getCurrentid()
                        .flatMap(memberRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }

}
