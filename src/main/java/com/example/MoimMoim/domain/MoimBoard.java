package com.example.MoimMoim.domain;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter // Getter 자동생성
@Setter // Setter 자동생성
@Entity // JPA의 엔티티 클래스임
@Table(name = "moim_board") //테이블 이름
public class MoimBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moim_board_key")
    private Long moim_board_key;

    @ManyToOne
    @JoinColumn(name = "user_key")
    private Member member;

    private String title;
    private String category;

    @Column(length = 2000)
    private String content;
    private String dateTime;
    private double latitude;
    private double longitude;
    private String address;
    private String detailAddress;
    private int peopleLimited;
    private int ageLimit;
}
