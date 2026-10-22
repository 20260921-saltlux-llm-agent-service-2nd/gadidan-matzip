package com.gadidan.app.dto;


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
    private int tblUserId;
    private int restaurantId;

    @Override
    public String toString() {
        return "ReviewDto{" +
                "reviewId=" + reviewId +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                ", addDate=" + addDate +
                ", modifyDate=" + modifyDate +
                ", tblUserId=" + tblUserId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
