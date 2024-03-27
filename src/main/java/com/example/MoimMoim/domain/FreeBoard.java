package com.example.MoimMoim.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter // Getter 자동생성
@Setter // Setter 자동생성
@Entity // JPA의 엔티티 클래스임
@Table(name = "free_Board") //테이블 이름
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "free_board_key")
    private Long freeBoardKey;

    @ManyToOne
    @JoinColumn(name = "user_key")
    private Member member;

    @Column(name = "title")
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 현재 날짜와 시간으로 설정
    }
}
