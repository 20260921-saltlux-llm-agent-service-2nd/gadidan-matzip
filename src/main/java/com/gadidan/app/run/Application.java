package com.gadidan.app.run;

import com.gadidan.app.controller.UserController;
import com.gadidan.app.view.ConsoleView;

import java.util.NoSuchElementException;
import java.util.Scanner;

/// 앱의 메인 실행점과 콘솔 화면 흐름을 관리합니다.
// IntelliJ Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
// 위 설정의 실행 도구를 IntelliJ로 변경할 것!!
// main/resources 경로에 db.properties 파일 생성할 것!!
public class Application {
    static UserController controller = new UserController();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                ConsoleView.displayMainMenu();
                int choice = readNumber(scanner, "선택: ");

                switch (choice) {
                    case 1 -> signUp(scanner);
                    case 2 -> login(scanner);
                    case 3 -> browseReviews(scanner);
                    case 0 -> running = false;
                    default -> ConsoleView.displayMessage(
                            "메뉴에 있는 번호를 입력해 주세요.");
                }
            }
        } catch (NoSuchElementException e) {
            ConsoleView.displayMessage("입력이 종료되었습니다.");
        }
        ConsoleView.displayMessage("프로그램을 종료합니다.");
    }

    /// 회원 가입 선택 시 호출되는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    private static void signUp(Scanner scanner) {
        ConsoleView.displayMessage("회원 가입");
        String userId = readText(scanner, "사용할 ID를 입력하세요: ");
        String password = readText(scanner, "사용할 패스워드를 입력하세요: ");
        String userName = readText(scanner, "사용자명을 입력해 주세요: ");

        // TODO: Controller에서 회원 가입 기능을 구현해주세용
        controller.signUp(userId, password);
        ConsoleView.displayMessage(
                userName + "님의 회원 가입 입력을 받았습니다. (처리 예정)");
    }

    /// 로그인 선택 시 호출되는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    private static void login(Scanner scanner) {
        ConsoleView.displayMessage("로그인");
        String userId = readText(scanner, "ID를 입력하세요: ");
        String password = readText(scanner, "패스워드를 입력하세요: ");

        // TODO: 인증 성공 시 UserSession에 로그인 정보를 저장 예정
        ConsoleView.displayMessage(
                "인증 연동 전입니다. 임시 로그인 화면으로 이동합니다.");
        showUserMenu(scanner);
    }

    /// 리뷰 조회 선택 시 호출되는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    private static void browseReviews(Scanner scanner) {
        boolean browsing = true;

        while (browsing) {
            ConsoleView.displayReviewMenu();
            int choice = readNumber(scanner, "선택: ");

            switch (choice) {
                case 1 -> ConsoleView.displayMessage(
                        "전체 리뷰 조회 기능을 연결할 예정입니다.");
                case 2 -> ConsoleView.displayMessage(
                        "리뷰 별점 순 조회 기능을 연결할 예정입니다.");
                case 3 -> {
                    String category = readText(
                            scanner, "검색할 카테고리를 입력해 주세요: ");
                    // TODO: 카테고리별 별점 조회 기능을 연결합니다.
                    ConsoleView.displayMessage(
                            category + " 카테고리 별점 조회 예정입니다.");
                }
                case 0 -> browsing = false;
                default -> ConsoleView.displayMessage(
                        "메뉴에 있는 번호를 입력해 주세요.");
            }
        }
    }

    /// 로그인 완료 시 호출되는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    private static void showUserMenu(Scanner scanner) {
        boolean loggedIn = true;

        while (loggedIn) {
            ConsoleView.displayUserMenu();
            int choice = readNumber(scanner, "선택: ");

            switch (choice) {
                case 1 -> registerReview(scanner);
                case 2 -> deleteReview(scanner);
                case 3 -> updateReview(scanner);
                case 4 -> {
                    // TODO: UserSession의 로그인 정보를 비웁니다.
                    ConsoleView.displayMessage("임시 로그아웃합니다.");
                    loggedIn = false;
                }
                case 5 -> {
                    // TODO: 회원 탈퇴 처리 후 UserSession을 비웁니다.
                    ConsoleView.displayMessage(
                            "회원 탈퇴 처리 예정입니다. 첫 화면으로 이동합니다.");
                    loggedIn = false;
                }
                default -> ConsoleView.displayMessage(
                        "메뉴에 있는 번호를 입력해 주세요.");
            }
        }
    }

    /// 로그인 -> 리뷰 등록 시 호출되는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    private static void registerReview(Scanner scanner) {
        ConsoleView.displayMessage("리뷰 등록");
        String restaurantName = readText(scanner, "식당명: ");
        int rating = readNumber(scanner, "별점(정수): ");
        String mainMenu = readText(scanner, "대표메뉴: ");
        String comment = readText(scanner, "한줄평: ");

        // TODO: 입력값과 UserSession의 사용자 정보로 리뷰를 등록합니다.
        ConsoleView.displayMessage(
                restaurantName + " 리뷰 입력을 받았습니다. (등록 예정)");
    }

    /// 로그인 -> 리뷰 삭제 시 호출되는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    private static void deleteReview(Scanner scanner) {
        ConsoleView.displayMessage("리뷰 삭제");
        // TODO: UserSession 사용자 본인의 리뷰 목록을 조회해 출력합니다.
        ConsoleView.displayMessage(
                "본인이 작성한 리뷰 목록을 표시할 예정입니다. (연동 전)");
        int reviewNumber = readNumber(scanner, "삭제할 리뷰의 목록 번호: ");

        // TODO: 목록 번호에 해당하는 본인의 리뷰를 삭제합니다.
        ConsoleView.displayMessage(
                reviewNumber + "번 리뷰 삭제 입력을 받았습니다. (처리 예정)");
    }

    /// 로그인 -> 리뷰 수정 시 호출되는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    private static void updateReview(Scanner scanner) {
        ConsoleView.displayMessage("리뷰 수정");
        // TODO: UserSession 사용자 본인의 리뷰 목록을 조회해 출력합니다.
        ConsoleView.displayMessage(
                "본인이 작성한 리뷰 목록을 표시할 예정입니다. (연동 전)");
        int reviewNumber = readNumber(scanner, "수정할 리뷰의 목록 번호: ");
        String restaurantName = readText(scanner, "수정할 식당명: ");
        int rating = readNumber(scanner, "수정할 별점(정수): ");
        String mainMenu = readText(scanner, "수정할 대표메뉴: ");
        String comment = readText(scanner, "수정할 한줄평: ");

        // TODO: 목록 번호에 해당하는 본인의 리뷰를 입력값으로 수정합니다.
        ConsoleView.displayMessage(
                reviewNumber + "번 리뷰 수정 입력을 받았습니다. (처리 예정)");
    }

    /// 프롬프트를 먼저 출력하고 문자열을 입력 받는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    /// @param prompt: 입력받기 전에 표시할 안내 문구입니다.
    private static String readText(Scanner scanner, String prompt) {
        ConsoleView.displayPrompt(prompt);
        return scanner.nextLine();
    }

    /// 프롬프트를 먼저 출력하고 정수를 입력 받는 함수
    /// @param scanner: 콘솔 입력에 사용하는 Scanner입니다.
    /// @param prompt: 정수 입력을 안내하는 문구입니다.
    private static int readNumber(Scanner scanner, String prompt) {
        while (true) {
            String input = readText(scanner, prompt);
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                ConsoleView.displayMessage("정수를 입력해 주세요.");
            }
        }
    }
}
