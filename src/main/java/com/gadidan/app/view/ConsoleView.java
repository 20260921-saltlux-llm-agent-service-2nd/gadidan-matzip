package com.gadidan.app.view;

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
        System.out.println("3. 카테고리 별 별점 조회");
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
