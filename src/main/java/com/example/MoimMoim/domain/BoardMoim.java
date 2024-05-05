package com.example.MoimMoim.domain;

import com.example.MoimMoim.domain.Enum.BoardMoimStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter // Getter 자동생성
@Setter // Setter 자동생성
@Entity // JPA의 엔티티 클래스임
@Table(name = "board_moim") //테이블 이름
public class BoardMoim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardMoimId;

    private double latitude;  // 위도

    private double longitude; // 경도

    @Column(name = "participant_limit")
    private int participantLimit;

    @Column(name = "age_limit")
    private int ageLimit;

    @Enumerated(EnumType.STRING)
    private BoardMoimStatus status;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

}
