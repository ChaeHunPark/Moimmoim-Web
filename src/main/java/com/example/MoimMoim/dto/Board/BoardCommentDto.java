package com.example.MoimMoim.dto.Board;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardCommentDto {
    private Long boardId;
    private Long memberId;
    private String content;
}
