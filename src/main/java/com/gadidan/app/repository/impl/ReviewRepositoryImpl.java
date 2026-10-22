package com.gadidan.app.repository.impl;

import com.gadidan.app.common.Category;
import com.gadidan.app.dto.ReviewDto;
import com.gadidan.app.repository.ReviewRepository;

import java.util.List;

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
        return List.of();  // todo: 더미값입니다. 수정 예정
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
