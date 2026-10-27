package com.gadidan.app.repository.impl;

import com.gadidan.app.common.Category;
import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.dto.RestaurantDto;
import com.gadidan.app.dto.ReviewDto;
import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.ReviewRepository;
import com.gadidan.app.view.ConsoleView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

/// ReviewRepositoryImpl 인터페이스를 구현한 클래스입니다.
public class ReviewRepositoryImpl implements ReviewRepository {
    Properties prop = new Properties();

    public ReviewRepositoryImpl() {
        try (
                FileInputStream fis = new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml")
        ) {
            prop.loadFromXML(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int save(ReviewDto review) {
        Connection con = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        int result;

        try {
            pstmt = con.prepareStatement(prop.getProperty("insertReview"));

            pstmt.setInt(1, review.getScore());
            pstmt.setString(2, review.getComment());
            pstmt.setInt(3, review.getTblUser().getId());
            pstmt.setInt(4, review.getRestaurant().getRestaurantId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<ReviewDto> findReviewsByCategory(Category category) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstmt = con.prepareStatement(prop.getProperty("selectReviewsByCategory"));
        ) {
            pstmt.setString(1, String.valueOf(category.getCategory()));

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rset.getString("restaurant_name"));
                restaurantDto.setBestMenu(rset.getString("best_menu"));
                restaurantDto.setCategory(rset.getString("category"));
                UserDto userDto = new UserDto();
                userDto.setUserId(rset.getString("user_id"));
                userDto.setName(rset.getString("name"));

                reviewDtoList.add(new ReviewDto(
                        rset.getInt("review_id"),
                        rset.getInt("score"),
                        rset.getString("comment"),
                        rset.getObject("add_date", LocalDate.class),
                        rset.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviewDtoList;
    }

    @Override
    public List<ReviewDto> findReviewsByScoreDesc() {
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstmt = con.prepareStatement(prop.getProperty("selectReviewsByScoreDesc"));
        ) {


            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rset.getString("restaurant_name"));
                restaurantDto.setBestMenu(rset.getString("best_menu"));
                restaurantDto.setCategory(rset.getString("category"));
                UserDto userDto = new UserDto();
                userDto.setUserId(rset.getString("user_id"));
                userDto.setName(rset.getString("name"));

                reviewDtoList.add(new ReviewDto(
                        rset.getInt("review_id"),
                        rset.getInt("score"),
                        rset.getString("comment"),
                        rset.getObject("add_date", LocalDate.class),
                        rset.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviewDtoList;
    }

    @Override
    public List<ReviewDto> findReviews() {

        List<ReviewDto> reviewDtoList = new ArrayList<>();
        ResultSet rSet = null;

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstm = con.prepareStatement(prop.getProperty("selectAllReviews"));
        ) {
            rSet = pstm.executeQuery();


            while (rSet.next()) {
                UserDto userDto = new UserDto();
                userDto.setUserId(rSet.getString("user_id"));
                userDto.setName(rSet.getString("name"));

                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rSet.getString("restaurant_name"));
                restaurantDto.setBestMenu(rSet.getString("best_menu"));
                restaurantDto.setCategory(rSet.getString("category"));


                reviewDtoList.add(new ReviewDto(
                        rSet.getInt("review_id"),
                        rSet.getInt("score"),
                        rSet.getString("comment"),
                        rSet.getObject("add_date", LocalDate.class),
                        rSet.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviewDtoList;
    }

    @Override
    public int deleteReview(int userId, int reviewId) {


            List<ReviewDto> reviewDtoList = new ArrayList<>();
            Connection con = null;
            PreparedStatement pstm = null;
            int result = 0;

            try {

                con = JDBCTemplate.getConnection();
                String query = prop.getProperty("deleteReviewUserId");
                pstm = con.prepareStatement(query);
                pstm.setInt(1, userId);
                pstm.setInt(2,reviewId);
                result = pstm.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                JDBCTemplate.close(pstm);
                JDBCTemplate.close(con);
            }
        return result;
    }

    @Override
    public int updateReview(ReviewDto review) {
        int result;

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstmt = con.prepareStatement(prop.getProperty("updateReview"));
        ) {

            pstmt.setInt(1, review.getScore());
            pstmt.setString(2, review.getComment());
            pstmt.setString(3, review.getRestaurant().getRestaurantName());
            pstmt.setInt(4, review.getReviewId());
            pstmt.setInt(5, review.getTblUser().getId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public List<ReviewDto> findMyReviews(int userId) {
        ResultSet rset = null;
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        try (
                Connection con = JDBCTemplate.getConnection();
                PreparedStatement pstmt = con.prepareStatement(prop.getProperty("selectMyReviewsByRestaurant"));
        ) {
            pstmt.setInt(1, userId);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                UserDto userDto = new UserDto();
                userDto.setUserId(rset.getString("user_id"));
                userDto.setName(rset.getString("name"));

                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rset.getString("restaurant_name"));
                restaurantDto.setBestMenu(rset.getString("best_menu"));
                restaurantDto.setCategory(rset.getString("category"));

                reviewDtoList.add(new ReviewDto(
                        rset.getInt("review_id"),
                        rset.getInt("score"),
                        rset.getString("comment"),
                        rset.getObject("add_date", LocalDate.class),
                        rset.getObject("modify_date", LocalDate.class),
                        userDto,
                        restaurantDto
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviewDtoList;
    }
}
