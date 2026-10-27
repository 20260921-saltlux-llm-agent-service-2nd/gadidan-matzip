package com.gadidan.app.model.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    //TODO: user table의 컬럼과 일치하는 필드 구현
    private int id;
    private String userId;
    private String userPw;
    private String name;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
