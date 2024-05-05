package com.example.MoimMoim.domain;

import com.example.MoimMoim.domain.Enum.BoardMoimStatus;
import com.example.MoimMoim.domain.Enum.BoardType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter // Getter 자동생성
@Setter // Setter 자동생성
@Entity // JPA의 엔티티 클래스임
@Table(name = "board") //테이블 이름
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(name = "category")
    private String category;

    @Column(name = "title")
    private String title;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @PrePersist
    protected void onCreate(){
        dateTime = LocalDateTime.now();
    }

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", dateTime=" + dateTime +
                ", content='" + content + '\'' +
                ", boardType=" + boardType +
                ", memberId=" + (member != null ? member.getMemberId() : null) +
                '}';
    }
}
