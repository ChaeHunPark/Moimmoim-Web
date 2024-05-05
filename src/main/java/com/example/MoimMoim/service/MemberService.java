package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.dto.MemberDto;


public interface MemberService {

    /*
    * 생성
    * */
    MemberDto signUp(MemberDto memberDto);


    /*
    * 조회
    * */
    boolean isUsernameAlreadyInUse(String username);
    boolean isNicknameAlreadyInUse(String nickname);
    MemberDto getUserWithAuthorities(String id);
    MemberDto getMyUserWithAuthorities();


}
