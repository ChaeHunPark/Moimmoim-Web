package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.dto.MemberDto;


public interface MemberService {
    MemberDto signUp(MemberDto memberDto);
    boolean checkIfMemberExists(String username);
    boolean checkIfNicknameExists(String nickname);
    MemberDto getUserWithAuthorities(String id);
    MemberDto getMyUserWithAuthorities();


}
