package com.example.MoimMoim.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter // Getter 자동생성
@Setter // Setter 자동생성
@Entity // JPA의 엔티티 클래스임
@Table(name = "board_comment") //테이블 이름
public class BoardComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_comment_id")
    private Long boardCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    private String content;

}
