package com.gadidan.app.controller;

import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.UserRepository;
import com.gadidan.app.repository.impl.UserRepositoryImpl;

public class UserController {
    private UserRepository repository;
    UserDto user = new UserDto();
    public UserController() {
        repository = new UserRepositoryImpl();
    }


    public boolean signUp(String id, String pw, String name) {
        // todo: (1) ID/PW 유효성 검사

        // todo: (2) 이미 존재하는 사용자인지 검사

        // todo: (3) user 객체 구현

        // 입력받은 아이디가 데이터베이스 있으면 저장못하게 할려고
        // -> 사용자가입력한 id가 데이터베이스에 있으면 저장못함

        if (id != null && id.length() > 0 && id.length() <= 15) {


            // 저장하기 위한 용도

            user.setUserId(id);
            user.setUserPw(pw);
            user.setName(name);
            repository.save(user);
            return true;
        }
        return false;

        // todo: 쿼리 영향 받은 행의 수가 없는 상황 처리

    }

    public boolean signUp(String id, String pw) {
        // todo: (1) ID/PW 유효성 검사

        // todo: (2) 이미 존재하는 사용자인지 검사

        // todo: (3) user 객체 구현

        // todo: 쿼리 영향 받은 행의 수가 없는 상황 처리


        if (id != null && id.length() > 0 && id.length() <= 15) {


            user.setUserId(id);
            user.setUserPw(pw);
            repository.save(user);
            return true;
        }



        return false;
    }

}