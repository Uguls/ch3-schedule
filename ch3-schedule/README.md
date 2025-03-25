# 📅 일정 관리 시스템 (Schedule Manager)

Spring Boot 기반으로 개발된 RESTful 일정 관리 API입니다.
사용자는 할일(todo)을 생성, 조회, 수정, 삭제할 수 있으며, 각 일정은 작성자(User) 정보와 연관되어 저장됩니다.

Swagger UI를 통해 API 명세서를 확인하고 테스트할 수 있습니다.

---

## 📁 프로젝트 구조

```
com.sparta.schedule.ch3_schedule
├── controller              # REST API 컨트롤러
├── dto                    # 요청/응답 DTO 클래스
├── entity                 # 도메인 엔티티 클래스
├── exception              # 커스텀 예외 및 전역 예외 핸들러
├── repository             # JdbcTemplate 기반 데이터 조회 계층
├── service                # 비즈니스 로직 계층
├── Ch3ScheduleApplication # 메인 실행 파일
├── SwaggerConfig          # Swagger 설정
```

---

### 개발 환경
- Java 17+
- Spring Boot 3.x
- MySQL

---

## 🌐 Swagger
- 접속 주소: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Swagger + OpenAPI 3.0 기반 API 문서 자동 생성

---

## ✅ 주요 기능

| 기능 | 설명 |
|------|------|
| ✅ 일정 생성 | todo, author, password, email 입력 |
| ✅ 전체 일정 조회 | 페이지네이션(`page`, `size`) 지원 |
| ✅ 단건 일정 조회 | ID 기반 조회 |
| ✅ 일정 수정 | todo 수정, password 검증 필요 |
| ✅ 일정 삭제 | password 검증 필요 |
| ✅ 예외 처리 | 전역 예외 핸들러 + 커스텀 예외 포함 |
| ✅ 유효성 검증 | `@Valid`, `@NotBlank`, `@Email` 등 활용 |
| ✅ Swagger 문서화 | API 테스트 및 설명 제공 |

---

## 📘 API 명세서

| No | Method | URL | Request Body | Response |
|----|--------|-----|----------------|----------|
| 1 | POST | `/schedules` | { <br/>"todo": "string", <br/>"author": "string", <br/>"password": "string", <br/>"email": "string" <br/>} | `ScheduleAndUserResponseDto` |
| 2 | GET | `/schedules` | 없음 | `List<ScheduleAndUserResponseDto>` |
| 3 | GET | `/schedules/{id}` | 없음 | `ScheduleAndUserResponseDto` |
| 4 | PUT | `/schedules/{id}` | { <br/>"todo": "string", <br/>"password": "string" <br/>}` | `ScheduleResponseDto |
| 5 | DELETE | `/schedules/{id}` | `{ "password": "string" }` | `"success"` 또는 에러 메시지 |

---

## 🛠️ 예외 처리 정책

| 예외 유형 | HTTP 상태 코드 | 메시지 |
|-----------|----------------|--------|
| ScheduleNotFoundException | 404 Not Found | 존재하지 않는 일정입니다. id = {id} |
| PasswordMismatchException | 400 Bad Request | 비밀번호가 일치하지 않습니다. |
| MethodArgumentNotValidException | 400 Bad Request | 필드별 유효성 검사 메시지 map 반환 |
| 기타 예외 | 500 Internal Server Error | 서버 오류가 발생했습니다. |

---

## 🧾 데이터베이스 테이블 설계 (MySQL 기준)

```sql
create table user
(
    id          int auto_increment comment 'PK'
        primary key,
    author      varchar(255) null comment '글쓴이',
    email       varchar(255) null comment '이메일',
    create_date datetime     null comment '생성날짜',
    update_date datetime     null comment '수정날짜'
);

create table schedule
(
    id          int auto_increment comment 'PK'
        primary key,
    todo        varchar(255) null comment '할일',
    create_date datetime     null comment '생성날짜',
    update_date datetime     null comment '수정날짜',
    password    varchar(255) null comment '비밀번호',
    user_id     int          not null comment 'user테이블 id',
    constraint fk_schedule_user
        foreign key (user_id) references user (id)
            on delete cascade
);


```

---

## 🧪 추후 확장 아이디어

- PATCH 메서드로 일정 일부 필드만 수정
- 비밀번호 암호화 및 로그인 인증 로직
- 일정 마감일, 우선순위 필드 추가
- 테스트 코드 작성 (Service 단위)
- JPA 적용 버전 전환