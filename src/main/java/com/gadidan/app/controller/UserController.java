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
        UserDto user = null;
        // todo: (3) user 객체 구현
        if(repository.save(user) > 0) {
            return true;
        } else {
            // todo: 쿼리 영향 받은 행의 수가 없는 상황 처리
            return false;
        }
    }

}