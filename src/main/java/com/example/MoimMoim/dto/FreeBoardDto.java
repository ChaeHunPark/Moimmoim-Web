package com.example.MoimMoim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardDto {
    private String id;
    private String title;
    private String category;
    private String content;

    @Override
    public String toString() {
        return "FreeBoardDto{" +
                "userId='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
