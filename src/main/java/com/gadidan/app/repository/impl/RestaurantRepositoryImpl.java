package com.gadidan.app.repository.impl;

import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.dto.RestaurantDto;
import com.gadidan.app.repository.RestaurantRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/// RestaurantRepository 인터페이스를 구현한 클래스입니다.
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Override
    public int save(RestaurantDto restaurantDto) {
        Connection con = JDBCTemplate.getConnection();
        Properties prop = new Properties();
        int result;

        try (FileInputStream file = new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml")) {
            prop.loadFromXML(file);

            try (PreparedStatement pstmt = con.prepareStatement(
                    prop.getProperty("insertRestaurant"),
                    Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, restaurantDto.getRestaurantName());
                pstmt.setString(2, restaurantDto.getBestMenu());
                pstmt.setString(3, restaurantDto.getCategory());

                result = pstmt.executeUpdate();

            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int findRestaurantByName(String restaurantName) {

        Connection con = JDBCTemplate.getConnection();
        Properties prop = new Properties();
        int result = 0;

        try (FileInputStream file = new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml")) {
            prop.loadFromXML(file);

            try (PreparedStatement pstmt = con.prepareStatement(
                    prop.getProperty("selectRestaurantByName"))) {

                pstmt.setString(1, restaurantName);

                try (ResultSet rset = pstmt.executeQuery()) {
                    if (rset.next()) {
                        result = rset.getInt("restaurant_id");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
