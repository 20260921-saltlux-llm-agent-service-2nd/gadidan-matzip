package com.gadidan.app.repository;

import com.gadidan.app.model.dto.UserDto;

public interface UserRepository {
    /// 회원 가입
    /// @param user UserDto 객체
    /// @return 쿼리 영향을 받은 행의 수
    public int save(UserDto user);

    /// 회원 탈퇴
    /// @param user UserDto 객체
    /// @return 쿼리 영향을 받은 행의 수
    public int delete(UserDto user);

    /// 로그인 시도
    /// @param userId   로그인할 User의 아이디
    /// @param password 로그인할 User의 패스워드
    /// @return 로그인 유저 객체
    public UserDto login(String userId, String password);

    public UserDto findUserById(String id);
}