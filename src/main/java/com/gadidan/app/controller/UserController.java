package com.gadidan.app.controller;

import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.UserRepository;
import com.gadidan.app.repository.impl.UserRepositoryImpl;

public class UserController {
    private UserRepository repository;

    public UserController() {
        repository = new UserRepositoryImpl();
    }


    public boolean signUp(String id, String pw) {
        // todo: (1) ID/PW 유효성 검사

        // todo: (2) 이미 존재하는 사용자인지 검사
        UserDto user = new UserDto();
        // todo: (3) user 객체 구현

        if (id != null &&id.length() > 0 && id.length() <= 15) {
            return true;
        } if (!id.equals(user.getUserId())) {

                user.setUserId(id);
                user.setUserPw(pw);
                repository.save(user);
            }

            // todo: 쿼리 영향 받은 행의 수가 없는 상황 처리

        return false;
    }

}