package com.gadidan.app.repository.impl;

import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.UserRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/// UserRepositoryImpl 인터페이스를 구현한 클래스입니다.
public class UserRepositoryImpl implements UserRepository {
    Properties prop = new Properties();

    public UserRepositoryImpl() {


        try (FileInputStream fin = new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml")) {
            prop.loadFromXML(fin);


        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public int save(UserDto user) {

        //id 15 글자 제한인지
        //15글자 이하이면 insert

        String query = prop.getProperty("signupQuery");
        int result =0;

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstm = con.prepareStatement(query);
        ) {
            pstm.setString(1, user.getUserId());
            pstm.setString(2, user.getUserPw());
            pstm.setString(3, user.getName());

            result = pstm.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        - 아이디 -> 영어와 숫자 혼용
//        - 아이디/비번 길이 제한 -> 아이디만 15글자 제한. (자바에서 고정.)/비밀번호는 제한 없음
//                - 비번 보안체크용으로 특수문자와 길이로 해킹하기 어려운 문자인지 여부.
//        (-> 비번 영어와 숫자 혼용.)


        return result; // todo: 더미값입니다. 수정 예정
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

    @Override
    public boolean findUserById() {
        // todo: Repository에 ID로 유저 찾는 함수 구현
        // todo: 해당 함수 호출 결과에 따라 boolean 값 반환
        return false;
    }
}
