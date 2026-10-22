package com.gadidan.app.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    //TODO: restaurant table의 컬럼과 일치하는 필드 구현
    private int restaurantId;
    private String restaurantName;
    private String bestMenu;
    private String category;

    @Override
    public String toString() {
        return "RestaurantDto{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", bestMenu='" + bestMenu + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
