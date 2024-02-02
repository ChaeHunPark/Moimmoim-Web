package com.example.MoimMoim.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    private Long userindex;
    private String id;
    private String password;
    private String email;
    private String phone;
    private String name;
    private String birth;
    private String gender;
    private String nickname;
    private String find_question;
    private String find_answers;

}
