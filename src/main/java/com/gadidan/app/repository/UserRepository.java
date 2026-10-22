package com.gadidan.app.repository;

import com.gadidan.app.dto.UserDto;

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
    /// @return 로그인 성공/실패 여부에 따른 Boolean 값
    public boolean login(String userId, String password);

    /// 로그아웃
    /// @return 로그아웃 성공/실패 여부에 따른 Boolean 값
    public boolean logout();

    public UserDto findUserById(String id);
}