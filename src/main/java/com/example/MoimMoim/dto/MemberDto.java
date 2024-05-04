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
    private String username;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String birthdate;
    private String gender;
    private String nickname;
    private String question;
    private String answer;
    private Set<AuthorityDto> authorityDtoSet;

    public static MemberDto from(Member member){
        if(member == null) return null;

        return MemberDto.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .email(member.getEmail())
                .phone(member.getPhone())
                .name(member.getName())
                .birthdate(member.getBirthdate())
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
