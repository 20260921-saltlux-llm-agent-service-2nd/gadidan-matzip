package com.gadidan.app.model.dto;


import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    //TODO: review table의 컬럼과 일치하는 필드 구현
    private int reviewId;
    private int score;
    private String comment;
    private LocalDate addDate;
    private LocalDate modifyDate;
    private UserDto tblUser;
    private RestaurantDto restaurant;

    @Override
    public String toString() {
        return "ReviewDto{" +
                "reviewId=" + reviewId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", addDate=" + addDate +
                ", modifyDate=" + modifyDate +
                ", tblUser=" + tblUser +
                ", restaurant=" + restaurant +
                '}';
    }
}
