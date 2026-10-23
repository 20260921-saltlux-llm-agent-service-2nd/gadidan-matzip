package com.gadidan.app.controller;

import com.gadidan.app.common.UserSession;
import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.UserRepository;
import com.gadidan.app.repository.impl.UserRepositoryImpl;
import com.gadidan.app.view.ConsoleView;

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


            if (!isDuplicatedUser(user.getUserId())) {
                return repository.save(user) > 0 ? true : false;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean signUp(String id, String pw) {
        if (id != null && id.length() > 0 && id.length() <= 15) {
            user.setUserId(id);
            user.setUserPw(pw);

            if (!isDuplicatedUser(user.getUserId())) {
                return repository.save(user) > 0 ? true : false;
            } else {
                return false;
            }
        }
        return false;
    }

    /// 중복되는 ID를 가진 유저가 있는지 검토하는 함수
    ///
    /// @return 유저가 존재할 경우 True, 존재하지 않을 경우 False
    private boolean isDuplicatedUser(String id) {
        UserDto result = repository.findUserById(id);
        return result == null ? false : true;
    }

    public boolean login(String id, String pw) {
        if (!validateId(id)) return false;
        if (!validatePw(pw)) return false;

        UserDto result = repository.login(id, pw);
        if (result != null) {
            UserSession.setUserInfo(result);
            return true;
        } else {
            ConsoleView.displayMessage("로그인 실패.");
            return false;
        }
    }

    public void logout() {
        UserSession.removeUserInfo();
        if(!UserSession.isLogin()) {
            ConsoleView.displayMessage("로그아웃되었습니다.");
        }
    }

    private boolean validateId(String id) {
        if(id == null || id.equals("") || id.length() < 1) {
            return false;
        }
        return true;
    }

    private boolean validatePw(String pw) {
        if(pw == null || pw.equals("") || pw.length() < 1) {
            return false;
        }
        return true;
    }
}