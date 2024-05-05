package com.example.MoimMoim.dto.Board;

import com.example.MoimMoim.domain.Enum.BoardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private Long memberId;
    private String category;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BoardType boardType;
}
