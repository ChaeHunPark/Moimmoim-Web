package com.example.MoimMoim.dto;


import com.example.MoimMoim.domain.Member;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String id;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String birth;
    private String gender;
    private String nickname;
    private String question;
    private String answer;

    private Set<AuthorityDto> authorityDtoSet;

    public static MemberDto from(Member member){
        if(member == null) return null;

        return MemberDto.builder()
                .id(member.getId())
                .password(member.getPassword())
                .email(member.getEmail())
                .phone(member.getPhone())
                .name(member.getName())
                .birth(member.getBirth())
                .gender(member.getGender())
                .nickname(member.getNickname())
                .question(member.getQuestion())
                .answer(member.getAnswer())
                .authorityDtoSet(member.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthority_name()).build())
                        .collect(Collectors.toSet()))
                .build();
    }

}
