package com.example.MoimMoim.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MoimBoardDto {
    private String id;
    private String title;
    private String category;
    private String content;
    private String dateTime;
    private double latitude;
    private double longitude;
    private String address;
    private String detailAddress;
    private int peopleLimited;
    private int ageLimit;

    @Override
    public String toString() {
        return "MoimBoardDto{" +
                "userId='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", peopleLimited=" + peopleLimited +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
