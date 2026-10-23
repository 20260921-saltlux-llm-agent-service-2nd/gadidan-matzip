package com.gadidan.app.repository.impl;

import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.dto.RestaurantDto;
import com.gadidan.app.repository.RestaurantRepository;
import com.gadidan.app.view.ConsoleView;

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

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));

            try (PreparedStatement pstmt = con.prepareStatement(
                    prop.getProperty("insertRestaurant"),
                    Statement.RETURN_GENERATED_KEYS)) {

                pstmt.setString(1, restaurantDto.getRestaurantName());
                pstmt.setString(2, restaurantDto.getBestMenu());
                pstmt.setString(3, restaurantDto.getCategory());

                result = pstmt.executeUpdate();

                if (result > 0) {
                    try (ResultSet rset = pstmt.getGeneratedKeys()) {
                        if (rset.next()) {
                            restaurantDto.setRestaurantId(rset.getInt(1));
                        } else {
                            throw new SQLException("등록된 식당 ID를 가져오지 못했습니다.");
                        }
                    }
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int findRestaurantByName(RestaurantDto restaurantDto) {

        Connection con = JDBCTemplate.getConnection();
        Properties prop = new Properties();
        int result=0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));

            try (PreparedStatement pstmt = con.prepareStatement(
                    prop.getProperty("selectRestaurantByName"))) {
                pstmt.setString(1, restaurantDto.getRestaurantName());

                try (ResultSet rset = pstmt.executeQuery()) {
                    if (rset.next()) {
                        restaurantDto.setRestaurantId(rset.getInt("restaurant_id"));
                        return 1;
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
