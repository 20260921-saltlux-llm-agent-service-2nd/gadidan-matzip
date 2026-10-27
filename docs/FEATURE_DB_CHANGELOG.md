# 브랜치별 작업 및 테이블 구조 문서화

## 1. DTO 클래스 구현

**Commit:** `feat: implement DTO classes`
**Commit:** `feat: implement DTO classes` | **SHA** `133df887`

**작업 내용**

- UserDto 클래스 구현
- RestaurantDto 클래스 구현
- ReviewDto 클래스 구현

**관련 테이블**

- `tbl_user`
- `tbl_restaurant`
- `tbl_review`

**테이블 구조 변경 사항**

- 없음

---

## 2. 회원가입

**Commit:** `feat: implementing-user-registration-functionality`
**Commit:** `feat: implementing-user-registration-functionality` | **SHA** `bb12bb5c`

**작업 내용**

- 아이디 글자수 15글자로 제한
- 아이디 중복체크
- 아이디 중복 아닐 시 로그인 구현

**관련 테이블**

- `tbl_user`

**사용 SQL**

- `INSERT`

**테이블 구조 변경 사항**

- 없음

---

## 3. 리뷰 별점순 조회

**Commit:** `feat: sort reviews by rating`
**Commit:** `feat: sort reviews by rating` | **SHA** `9fb29ff0`

**작업 내용**

- 리뷰를 별점 내림차순으로 조회
- 별점이 동일한 경우 등록 날짜 기준 정렬

**관련 테이블**

- `tbl_restaurant`
- `tbl_review`

**사용 SQL**

- `SELECT`
- `JOIN`
- `ORDER BY`

**테이블 구조 변경 사항**

- 없음

---

## 4. 리뷰 카테고리별 조회

**Commit:** `feat: implement review filtering by category`
**Commit:** `feat: implement review filtering by category` | **SHA** `2fe99a1b`

**작업 내용**

- 선택한 카테고리에 해당하는 리뷰 조회
- 카테고리별 조회 결과 출력

**관련 테이블**

- `tbl_restaurant`
- `tbl_review`

**사용 SQL**

- `SELECT`
- `JOIN`
- `WHERE`

**테이블 구조 변경 사항**

- 없음

---

## 5. 전체 리뷰 조회

**Commit:** `feat: implement a feature to view all reviews`  
**Commit:** `feat: implement a feature to view all reviews` | **SHA** `53e465c5`

**작업 내용**

- 전체 리뷰 조회
- 리뷰를 등록된 날짜 내림차순으로 정렬

**관련 테이블**

- `tbl_restaurant`
- `tbl_review`
- `tbl_user`

**사용 SQL**

- `SELECT`
- `JOIN`
- `ORDER BY`

**테이블 구조 변경 사항**

- 없음

---

## 6. 식당 및 리뷰 등록

**Commit:** `feat: implement restaurant and review registration`
**Commit:** `feat: implement restaurant and review registration` | **SHA** `25fdf6d7`

**작업 내용**

- 식당 중복 여부 확인
- 신규 식당 등록
- 리뷰 등록

**관련 테이블**

- `tbl_restaurant`
- `tbl_review`
- `tbl_user`

**사용 SQL**

- `SELECT`
- `INSERT`
- `WHERE`

**테이블 구조 변경 사항**

- tbl_restaurant.restaurant_name에 UNIQUE 제약조건 추가
- 변경 이유: 동일한 이름의 식당이 중복 등록되는 것을 방지

---

## 7. 리뷰 수정

**Commit:** `feat: implement review update functionality`
**Commit:** `feat: implement review update functionality` | **SHA** `9bd7d273`

**작업 내용**

- 로그인한 사용자의 리뷰 목록 조회
- 수정 대상 리뷰 선택
- 별점 및 한줄평 수정

**관련 테이블**

- `tbl_review`
- `tbl_restaurant`
- `tbl_user`

**사용 SQL**

- `SELECT`
- `UPDATE`
- `JOIN`
- `WHERE`

**테이블 구조 변경 사항**

- 없음

---

## 8. 리뷰 삭제

**Commit:** `feat: implementing a review deletion feature`
**Commit:** `feat: implementing a review deletion feature` | **SHA** `56095022`

**작업 내용**

- 사용자 본인이 등록한 리뷰 목록 조회
- 목록에서 삭제할 리뷰 선택
- 리뷰 삭제

**관련 테이블**

- `tbl_review`
- `tbl_user`

**사용 SQL**

- `DELETE`
- `WHERE`
- `AND`

**테이블 구조 변경 사항**

- 없음

---

## 9. 테이블 구조 변경 이력

| 작업         | 변경 테이블         | 변경 컬럼 및 제약조건             | 변경 이유              |
| ---------- | -------------- | ------------------------ | ------------------ |
| DTO 구현     | -              | -                        | -                  |
| 별점순 조회     | -              | -                        | -                  |
| 카테고리별 조회   | -              | -                        | -                  |
| 식당 및 리뷰 등록 | tbl_restaurant | restaurant_name : unique 추가 | 동일한 이름의 식당 중복 등록 방지 |
| 리뷰 수정      | -              | -                        | -                  |
