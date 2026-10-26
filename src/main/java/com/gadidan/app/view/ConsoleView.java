package com.gadidan.app.view;

import com.gadidan.app.dto.ReviewDto;

import java.util.List;

public final class ConsoleView {
    public static void displayMainMenu() {
        displayMessage("가디단 맛집");
        System.out.println("1. 회원 가입");
        System.out.println("2. 로그인");
        System.out.println("3. 리뷰 조회하기");
        System.out.println("0. 종료");
    }

    public static void displayReviewMenu() {
        displayMessage("리뷰 조회");
        System.out.println("1. 전체 리뷰 조회");
        System.out.println("2. 리뷰 별점 순 조회");
        System.out.println("3. 카테고리 별 리뷰 조회");
        System.out.println("0. 뒤로 가기");
    }

    public static void displayUserMenu() {
        displayMessage("로그인 후 메뉴");
        System.out.println("1. 리뷰 등록하기");
        System.out.println("2. 리뷰 삭제하기");
        System.out.println("3. 리뷰 수정하기");
        System.out.println("4. 로그아웃");
        System.out.println("5. 회원 탈퇴");
    }

    /// prompt: 줄바꿈 없이 표시할 입력 안내 문구입니다.
    public static void displayPrompt(String prompt) {
        System.out.print(prompt);
    }

    public static void displayReviewList(List<ReviewDto> reviewDtoList) {
        for (ReviewDto reviewDto : reviewDtoList) {
            System.out.println("식당 이름 : " + reviewDto.getRestaurant().getRestaurantName()
                            + ", 별점 : " + reviewDto.getScore()
                            + ", 대표 메뉴 : " + reviewDto.getRestaurant().getBestMenu()
                            + ", 카테고리 : " + reviewDto.getRestaurant().getCategory()
                            + ", 한줄평 : " + reviewDto.getComment()
                            + ", 등록 날짜 : " + reviewDto.getAddDate()
                            + ", 수정 날짜 : " + (reviewDto.getModifyDate() == null ? "-" : reviewDto.getModifyDate()));
        }
    }

    public static void displayNumberedReviewList(List<ReviewDto> reviewDtoList) {
        for (int i = 0; i < reviewDtoList.size(); i++) {
            ReviewDto reviewDto = reviewDtoList.get(i);

            System.out.println((i + 1) + "번 식당 이름 : " + reviewDto.getRestaurant().getRestaurantName()
                    + ", 별점 : " + reviewDto.getScore()
                    + ", 대표 메뉴 : " + reviewDto.getRestaurant().getBestMenu()
                    + ", 카테고리 : " + reviewDto.getRestaurant().getCategory()
                    + ", 한줄평 : " + reviewDto.getComment()
                    + ", 등록 날짜 : " + reviewDto.getAddDate()
                    + ", 수정 날짜 : " + (reviewDto.getModifyDate() == null ? "-" : reviewDto.getModifyDate()));
        }
    }

    /// message: 화면에 표시할 메시지입니다.
    public static void displayMessage(String message) {
        String separator = "─".repeat(50);

        System.out.println();
        System.out.println(separator);
        for (String line : message.split("\\R", -1)) {
            System.out.println("  > " + line);
        }
        System.out.println(separator);
        System.out.println();
    }
}
