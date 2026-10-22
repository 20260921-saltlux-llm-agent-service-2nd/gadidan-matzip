package com.gadidan.app.repository.impl;

import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.UserRepository;

/// UserRepositoryImpl 인터페이스를 구현한 클래스입니다.
public class UserRepositoryImpl implements UserRepository {
    @Override
    public int save(UserDto user) {
        return 0; // todo: 더미값입니다. 수정 예정
    }

    @Override
    public int delete(UserDto user) {
        return 0; // todo: 더미값입니다. 수정 예정
    }

    @Override
    public boolean login(String userId, String password) {
        return false; // todo: 더미값입니다. 수정 예정
    }

    @Override
    public boolean logout() {
        return false; // todo: 더미값입니다. 수정 예정
    }
}
