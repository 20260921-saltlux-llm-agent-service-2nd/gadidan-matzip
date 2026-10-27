package com.gadidan.app.repository;

import com.gadidan.app.common.Category;
import com.gadidan.app.model.dto.ReviewDto;

import java.util.List;

public interface ReviewRepository {
    /// 리뷰 등록
    /// @param review ReviewDto 객체
    /// @return 영향 받은 행의 수
    int save(ReviewDto review);

    /// 카테고리 별 리뷰 조회
    /// @param category 조회하고자 하는 카테고리
    /// @return 조건에 해당하는 리뷰 리스트
    List<ReviewDto> findReviewsByCategory(Category category);

    /// 별점 순으로 리뷰 조회
    /// @return 조건에 해당하는 리뷰 리스트
    List<ReviewDto> findReviewsByScoreDesc();

    /// 등록 순으로 리뷰 조회
    /// @return 조건에 해당하는 리뷰 리스트
    List<ReviewDto> findReviews();

    /// 리뷰 삭제
    /// @param reviewId 삭제하고자 하는 리뷰의 아이디
    /// @return 영향 받은 행의 수
    int deleteReview(int userId, int reviewId);

    /// 리뷰 수정
    /// @param review 수정할 리뷰 정보를 담은 ReviewDto 객체
    /// @return 영향 받은 행의 수
    int updateReview(ReviewDto review);

    /// 특정 레스토랑에 작성한 본인 리뷰 조회
    /// @param userId 로그인한 회원의 PK
    /// @return 조건에 해당하는 리뷰 리스트
    List<ReviewDto> findMyReviews(int userId);
}
