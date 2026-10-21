## 📚 가디단 맛집 등록 조회 서비스


### 🎯 프로젝트 목표
1. SQL 문법을 익히고, DB 모델링 개념을 학습합니다.
2. MVC 패턴 적용과 사용법을 학습합니다.
3. Git 기반 협업 방식을 학습합니다.

---

#### 1. 아키텍처 구조
```
app/
├── run/Application.java                        // 앱 실행 진입점 및 메인 루프
├── common/
│   ├── JDBCTemplate.java            // JDBC 커넥션 관리
│   └── UserSession.java             // 로그인 상태(세션) 관리
├── model/
│   ├── dto/                         
│   │   ├── UserDto.java
│   │   ├── RestaurantDto.java
│   │   └── ReviewDto.java
├── repository/                      
│   ├── UserRepository.java          
│   ├── RestaurantRepository.java    
│   ├── ReviewRepository.java        
│   └── impl/                        
│       ├── UserRepositoryImpl.java
│       ├── RestaurantRepositoryImpl.java
│       └── ReviewRepositoryImpl.java
├── controller/                    
│   ├── UserController.java
│   ├── RestaurantController.java    
│   └── ReviewController.java
├── view/
│   └── ConsoleView.java             // 사용자 입·출력 화면
└── mapper/query.xml                 // 전체 쿼리 모음
```
---
#### 2. Branch Rules
1. `main` 브랜치에 직접 push를 금지한다.
2. 모든 작업 브랜치는 `main` 브랜치로부터 분기하여 작업한다.
3. 브랜치명은 `타입/#이슈번호-작업명` 형태로 생성한다.


#### 3. Git Workflow
`main` 브랜치에서 작업 브랜치 분기 -> 작업 브랜치 커밋 전 `main` 브랜치 `pull`하여 충돌은 로컬에서 해결 -> remote로 `push` 후 `PR` 생성 -> `PR`에 대한 코드 리뷰 후 `main`에 Merge


#### 4. Commit Convention
1. `타입: 작업 내용(한 줄로 작성)` 구조로 작성한다.
2. `타입`과 `작업 내용`은 소문자로 작성한다. (고유 명사의 경우 대문자 허용)
3. 헤더는 영문으로 작성한다. (인코딩 방식에 따른 깨짐 방지 차원. 문법은 틀려도 되지만 의미가 명확히 전달되도록 작성할 것)
4. 바디(본문)를 작성할 경우 헤더로부터 한 줄의 개행을 두고 작성한다.
 

#### 5. Code Convention
1. 모든 클래스명은 대문자로 시작하는 `CamelCase`를 채택한다.
2. 모든 변수명은 소문자로 시작하는 `CamelCase`를 채택하고 명사로 작명한다.
3. 모든 상수는 대문자로 작성하고, 공백은 `_`로 구분한다.
4. 코드는 한 라인 당 최대 `80자`까지 작성하고, 초과할 경우 줄 바꿈으로 작성한다.
5. 매개변수를 받는 메서드의 경우 JavaDoc(`///`)을 활용하여 매개변수 의미를 명시해주는 것을 권장한다.
6. Repository 계층은 인터페이스를 우선 정의하고, 구현체는 {도메인}RepositoryImpl 네이밍을 따른다. 상위 계층(Controller 등)은 구현체가 아닌 인터페이스에만 의존하도록 구성한다. (목적: 테스트 용이성 및 병목 방지)

---