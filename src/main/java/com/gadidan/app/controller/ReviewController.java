package com.gadidan.app.controller;

import com.gadidan.app.common.Category;
import com.gadidan.app.common.UserSession;
import com.gadidan.app.dto.ReviewDto;
import com.gadidan.app.repository.ReviewRepository;
import com.gadidan.app.repository.impl.ReviewRepositoryImpl;
import com.gadidan.app.view.ConsoleView;

import java.util.List;

public class ReviewController {
    private ReviewRepository repository;

    public ReviewController() {
        repository = new ReviewRepositoryImpl();
    }

    public void findReviews() {
        // TODO: 반환된 리스트를 사용자에게 출력하는 기능 구현
        List<ReviewDto> results = repository.findReviews();
        if (!results.isEmpty()) {
            ConsoleView.displayReviewList(results);
        } else {
            ConsoleView.displayMessage("현재 등록된 리뷰가 없어서 출력이 불가능합니다.");
        }
    }

    public void findReviewsByScoreDesc() {
        // TODO: 반환된 리스트를 사용자에게 출력하는 기능 구현
        List<ReviewDto> results = repository.findReviewsByScoreDesc();
        if (!results.isEmpty()) {
            ConsoleView.displayReviewList(repository.findReviewsByScoreDesc());
        } else {
            ConsoleView.displayPrompt("현재 등록된 리뷰가 없어서 출력이 불가능합니다.");
        }
    }

    public void findReviewsByCategory(Category category) {
        List<ReviewDto> results = repository.findReviewsByCategory(category);

        if (!results.isEmpty()) {
            ConsoleView.displayReviewList(repository.findReviewsByCategory(category));
        } else {
            ConsoleView.displayPrompt("현재 " + category.getCategory() + " 카테고리에 등록된 리뷰가 없어서 출력이 불가능합니다.");
        }
    }

    public int registerReview(ReviewDto reviewDto) {
        int result = repository.save(reviewDto);

        if (result > 0) {
            ConsoleView.displayMessage("리뷰 등록이 완료되었습니다.");
        } else {
            ConsoleView.displayMessage("리뷰 등록에 실패하였습니다.");
        }

        return result;
    }

    public int updateReview(ReviewDto review) {
        int result = repository.updateReview(review);

        if (result > 0) {
            ConsoleView.displayMessage("리뷰 수정이 완료되었습니다.");
        } else {
            ConsoleView.displayMessage("리뷰 수정에 실패하였습니다.");
        }

        return result;
    }

    public List<ReviewDto> findMyReviewsByRestaurant() {
        List<ReviewDto> results = repository.findMyReviews(UserSession.getUserSession().getId());
        String userName = UserSession.getUserSession().getName();

        if (!results.isEmpty()) {
            ConsoleView.displayNumberedReviewList(results);
        } else {
            ConsoleView.displayMessage(userName + "님이 작성한 리뷰가 없습니다.");
        }

        return results;
    }
}