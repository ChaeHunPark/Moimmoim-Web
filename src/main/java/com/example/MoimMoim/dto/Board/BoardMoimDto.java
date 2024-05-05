package com.example.MoimMoim.dto.Board;

import com.example.MoimMoim.domain.Enum.BoardMoimStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardMoimDto {

    private double latitude;
    private double longitude;
    private int participantLimit;
    private int ageLimit;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BoardMoimStatus status;
    private BoardDto board;
}
