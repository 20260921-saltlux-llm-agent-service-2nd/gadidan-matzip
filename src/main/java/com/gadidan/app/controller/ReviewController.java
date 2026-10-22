package com.gadidan.app.controller;

import com.gadidan.app.common.Category;
import com.gadidan.app.dto.ReviewDto;
import com.gadidan.app.repository.ReviewRepository;
import com.gadidan.app.repository.impl.ReviewRepositoryImpl;

import java.util.List;

public class ReviewController {
    private ReviewRepository repository;

    public ReviewController() {
        repository = new ReviewRepositoryImpl();
    }

    public void findReviews() {
        // TODO: 반환된 리스트를 사용자에게 출력하는 기능 구현
        List<ReviewDto> results = repository.findReviews();
    }

    public void findReviewsByScoreDesc() {
        // TODO: 반환된 리스트를 사용자에게 출력하는 기능 구현
        List<ReviewDto> results = repository.findReviewsByScoreDesc();
    }

    public void findReviewsByCategory(Category category) {
        List<ReviewDto> results = repository.findReviewsByCategory(category);
    }
}