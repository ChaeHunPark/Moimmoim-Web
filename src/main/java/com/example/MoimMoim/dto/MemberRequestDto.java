package com.example.MoimMoim.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
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

}
