package com.example.MoimMoim.service;

import com.example.MoimMoim.domain.Member;
import com.example.MoimMoim.dto.MemberRequestDto;


public interface MemberService {
    Member signUp(MemberRequestDto memberRequestDto);
    boolean existsId(String id);

}
