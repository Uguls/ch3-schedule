# Schedule
---
# API 명세서
| 기능                      | Method   | URL                     | Request (Body)                                                 | Response 예시 | 상태 코드 |
|---------------------------|----------|-------------------------|----------------------------------------------------------------|--------------|------------|
| **일정 저장** | `POST` | `/` | `{ "scheduleResponseDto": "test", "author": "test", "password": "password" }` | `{ "id": 1, "scheduleResponseDto": "test", "author": "test" }` | `201 Created` |
| **일정 조회 (ID로)** | `GET` | `/{id}` | 없음 | `{ "id": 10, "scheduleResponseDto": "exampleTodo", "author": "exampleUser" }` | `200 OK` / `404 Not Found` |
| **모든 일정 조회** | `GET` | `/` | 없음 | `[ { "id": 1, "scheduleResponseDto": "test" }, { "id": 2, "scheduleResponseDto": "anotherTest" } ]` | `200 OK` |
| **일정 수정 (ID로)** | `PUT` | `/{id}` | `{ "scheduleResponseDto": "changedTodo", "password": "password123" }` | `{ "id": 10, "scheduleResponseDto": "changedTodo" }` | `200 OK` / `400 Bad Request` / `403 Forbidden` |
| **일정 삭제 (ID로)** | `DELETE` | `/{id}` | { "password": "password" } | `{ "message": "Deleted successfully" }` | `200 OK` / `404 Not Found` |
