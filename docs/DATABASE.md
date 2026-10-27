# 데이터베이스 테이블 구조

## 1. 데이터베이스 개요

**프로젝트:** 가디단 맛집 등록 조회 서비스

**DBMS:** MySQL

**사용 테이블**
- `tbl_user` : 회원 정보 관리
- `tbl_restaurant` : 식당 정보 관리
- `tbl_review` : 리뷰 정보 관리

---

## 2. tbl_user (회원 테이블)

**테이블 설명:** 회원의 계정 및 기본 정보를 저장하는 테이블

| 컬럼명 | 자료형 | 제약조건 | 설명 |
|---|---|---|---|
| id | INT | PK, AUTO_INCREMENT | 회원 고유 번호 |
| user_id | VARCHAR(255) | NOT NULL | 로그인에 사용할 아이디 |
| user_pw | VARCHAR(255) | NOT NULL | 로그인에 사용할 비밀번호 |
| name | VARCHAR(255) | NULL | 회원 이름 |

---

## 3. tbl_restaurant (식당 테이블)

**테이블 설명:** 리뷰 등록에 필요한 식당 정보를 저장하는 테이블

| 컬럼명 | 자료형 | 제약조건 | 설명 |
|---|---|---|---|
| restaurant_id | INT | PK, AUTO_INCREMENT | 식당 고유 번호 |
| restaurant_name | VARCHAR(255) | NOT NULL, UNIQUE | 식당 이름 |
| best_menu | VARCHAR(255) | NOT NULL | 대표 메뉴 |
| category | VARCHAR(255) | NOT NULL, CHECK | 식당 카테고리 |

**카테고리 허용 값:** 한식, 중식, 일식, 양식, 분식, 뷔페, 디저트

---

## 4. tbl_review (리뷰 테이블)

**테이블 설명:** 회원이 식당에 작성한 리뷰 정보를 저장하는 테이블

| 컬럼명 | 자료형 | 제약조건 | 설명 |
|---|---|---|---|
| review_id | INT | PK, AUTO_INCREMENT | 리뷰 고유 번호 |
| score | INT | NOT NULL, CHECK | 별점 |
| comment | VARCHAR(255) | NULL | 한줄평 |
| add_date | DATE | NOT NULL | 리뷰 등록 날짜 |
| modify_date | DATE | NULL | 리뷰 수정 날짜 |
| tbl_user_id | INT | FK, NULL, ON DELETE SET NULL | 리뷰 작성자 |
| restaurant_id | INT | FK, NOT NULL | 리뷰 대상 식당 |

**별점 허용 값:** 1~5점

---

## 5. 테이블 간 관계

| 기준 테이블 | 참조 컬럼 | 참조 대상 | 관계 |
|---|---|---|---|
| tbl_review | tbl_user_id | tbl_user.id | 회원 1명 : 리뷰 여러 개 |
| tbl_review | restaurant_id | tbl_restaurant.restaurant_id | 식당 1개 : 리뷰 여러 개 |

**외래키 제약조건**
- `tbl\_review.tbl\_user\_id` → `tbl\_user.id`
  - 회원 삭제 시 `ON DELETE SET NULL`

---

## 6. 테이블 구조 확인 SQL

```sql

SHOW CREATE TABLE tbl_user;

SHOW CREATE TABLE tbl_restaurant;

SHOW CREATE TABLE tbl_review;

```
---

## 7. 데이터베이스 테이블 구조 요약

| 테이블 | 주요 항목 | 관계 및 제약 |
| --- | --- | --- |
| tbl_user | id, user_id, user_pw, name | id PK, 리뷰와 1:N 관계 |
| tbl_restaurant | restaurant_id, restaurant_name, best_menu, category | restaurant_id PK, restaurant_name UNIQUE, category CHECK, 리뷰와 1:N 관계 |
| tbl_review | review_id, score, comment, add_date, modify_date, tbl_user_id, restaurant_id | review_id PK, score CHECK(1~5), tbl_user_id FK(ON DELETE SET NULL), restaurant_id FK, 회원·식당과 각각 N:1 관계 |

---

각 테이블의 실제 컬럼, 자료형, 제약조건을 확인하는 데 사용한다.
