package com.gadidan.app.repository.impl;

import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.dto.RestaurantDto;
import com.gadidan.app.repository.RestaurantRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/// RestaurantRepository 인터페이스를 구현한 클래스입니다.
public class RestaurantRepositoryImpl implements RestaurantRepository {
    Properties prop = new Properties();

    public RestaurantRepositoryImpl() {
        try (FileInputStream fis = new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml")) {
            prop.loadFromXML(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(RestaurantDto restaurantDto) {
        int result;

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        prop.getProperty("insertRestaurant"), Statement.RETURN_GENERATED_KEYS);
        ) {
            pstmt.setString(1, restaurantDto.getRestaurantName());
            pstmt.setString(2, restaurantDto.getBestMenu());
            pstmt.setString(3, restaurantDto.getCategory());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int findRestaurantByName(String restaurantName) {
        int result = 0;

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        prop.getProperty("selectRestaurantByName"));
        ) {
            pstmt.setString(1, restaurantName);

            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                result = rset.getInt("restaurant_id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
