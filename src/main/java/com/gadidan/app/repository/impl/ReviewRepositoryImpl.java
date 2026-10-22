package com.gadidan.app.repository.impl;

import com.gadidan.app.common.Category;
import com.gadidan.app.common.JDBCTemplate;
import com.gadidan.app.dto.RestaurantDto;
import com.gadidan.app.dto.ReviewDto;
import com.gadidan.app.dto.UserDto;
import com.gadidan.app.repository.ReviewRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/// ReviewRepositoryImpl 인터페이스를 구현한 클래스입니다.
public class ReviewRepositoryImpl implements ReviewRepository {
    @Override
    public int save(ReviewDto review) {
        return 0;  // todo: 더미값입니다. 수정 예정
    }

    @Override
    public List<ReviewDto> findReviewsByCategory(Category category) {
        return List.of();  // todo: 더미값입니다. 수정 예정
    }

    @Override
    public List<ReviewDto> findReviewsByScoreDesc() {
        Connection con = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        Properties prop = new Properties();
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/gadidan/app/mapper/query.xml"));
            String query = prop.getProperty("selectReviewsByScoreDesc");

            pstmt = con.prepareStatement(query);

            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                // -------
                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setRestaurantName(rset.getString("restaurant_name"));
                restaurantDto.setBestMenu(rset.getString("best_menu"));
                restaurantDto.setCategory(rset.getString("category"));
                UserDto userDto = new UserDto();

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

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        return reviewDtoList;
    }

    @Override
    public List<ReviewDto> findReviews() {
        return List.of();  // todo: 더미값입니다. 수정 예정
    }

    @Override
    public int deleteReview(int reviewId) {
        return 0;  // todo: 더미값입니다. 수정 예정
    }
}
