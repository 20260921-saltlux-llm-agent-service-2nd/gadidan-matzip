package com.gadidan.app.repository.impl;

import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.model.dto.UserDto;
import com.gadidan.app.repository.UserRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        int result = 0;

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
        int result = 0;

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstm = con.prepareStatement(prop.getProperty("cancelMembershipQuery"));
        ) {
            pstm.setString(1, user.getUserId());
            pstm.setString(2, user.getUserPw());
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public UserDto login(String userId, String password) {
        UserDto user = null;
        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstm = con.prepareStatement(prop.getProperty("loginQuery"));
        ) {
            pstm.setString(1, userId);
            pstm.setString(2, password);
            ResultSet resultSet = pstm.executeQuery();

            if (resultSet.next())
                user = new UserDto(
                        resultSet.getInt("id"),
                        resultSet.getString("user_id"),
                        resultSet.getString("user_pw"),
                        resultSet.getString("name")
                );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }


    @Override
    public UserDto findUserById(String id) {
        UserDto result = null;
        // todo: 해당 함수 기능 구현
        // todo: 해당 함수 호출 결과에 따라 boolean 값 반환

        // 아이디 유효성 검사.
        // 입력받은 아이디가 데이터베이스 있으면 저장못하게 할려고
        // -> 사용자가입력한 id가 데이터베이스에 있으면 저장못함

        String query = prop.getProperty("IdValidation");


        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstm = con.prepareStatement(query)
        ) {

            pstm.setString(1, id);
            ResultSet result1 = pstm.executeQuery();  // 커밋 전에 변수명 바꾸기.
            if (result1 == null) {
                return result;
            }
            while (result1.next()) {
                result = new UserDto();
                result.setId(result1.getInt("id"));
                result.setUserId(result1.getString("user_id"));
                result.setUserPw(result1.getNString("user_pw"));
                result.setName(result1.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
