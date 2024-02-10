package com.example.MoimMoim.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter // Getter 자동생성
@Setter // Setter 자동생성
@Entity // JPA의 엔티티 클래스임
@Table(name = "Member") //테이블 이름
public class Member {


    @Id // primary key, 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 0번부터 자동 생성
    private Long user_index;

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String find_question;

    @Column(nullable = false)
    private String find_answers;

    @Column(nullable = false)
    private LocalDateTime registration_date;

    @PrePersist
    protected void onCreate(){
        registration_date = LocalDateTime.now();
    }

}
