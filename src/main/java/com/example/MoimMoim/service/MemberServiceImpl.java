package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.repository.MemberRepository;
import com.example.MoimMoim.dto.MemberRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public Member signUp(MemberRequestDto memberRequestDto){

        //회원 가입 처리
        Member newMember = new Member();
        newMember.setId(memberRequestDto.getId());
        newMember.setPassword(passwordEncoder.encode(memberRequestDto.getPassword()));
        newMember.setEmail(memberRequestDto.getEmail());
        newMember.setPhone(memberRequestDto.getPhone());
        newMember.setName(memberRequestDto.getName());
        newMember.setBirth(memberRequestDto.getBirth());
        newMember.setGender(memberRequestDto.getGender());
        newMember.setNickname(memberRequestDto.getNickname());
        newMember.setQuestion(memberRequestDto.getQuestion());
        newMember.setAnswer(memberRequestDto.getAnswer());

        return memberRepository.save(newMember);
    };

    // 중복아이디 조회
    public boolean checkIfMemberExists(String id){
        return !memberRepository.existsById(id); // 아이디를 찾지 못하면 true를 넘겨줌
    }

    public boolean checkIfNicknameExists(String nickname){
        return !memberRepository.existsByNickname(nickname); //닉네임이 없으면 true
    }

}
