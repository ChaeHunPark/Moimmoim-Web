package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.domain.MemberRepository;
import com.example.MoimMoim.dto.MemberRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 서비스
    @Transactional
    public Member signUp(MemberRequestDto memberRequestDto){
        System.out.println("id : " + memberRequestDto.getId());

        //회원 가입 처리
        Member newMember = new Member();
        newMember.setId(memberRequestDto.getId());
        newMember.setPassword(memberRequestDto.getPassword());
        newMember.setEmail(memberRequestDto.getEmail());
        newMember.setPhone(memberRequestDto.getPhone());
        newMember.setName(memberRequestDto.getName());
        newMember.setBirth(memberRequestDto.getBirth());
        newMember.setGender(memberRequestDto.getGender());
        newMember.setNickname(memberRequestDto.getNickname());
        newMember.setFind_question(memberRequestDto.getFind_question());
        newMember.setFind_answers(memberRequestDto.getFind_answers());

        return memberRepository.save(newMember);
    };


}
