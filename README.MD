# Schedule Project

### 목적 : Spring을 이용하여  일정표의 CRUD 구현

---

### API 명세서 ###

| 기능 | 메서드 | URL | Request | Response | 상태 코드 |
| --- | --- | --- | --- | --- | --- |
| 일정 생성 | POST | /schedule | 요청 body | 등록 정보 | 201 정상 등록 400 오류 |
| 일정 조회 | GET | /schedule | 요청 body | 다 건 응답 정보 | 200 정상 조회 400 오류 |
| 선택 일정 조회  | GET | schedule/{id} | 요청 param(id) | 단 건 응답 정보 | 200 정상 조회 400 오류  |
| 선택 일정 수정 | PUT | schedule/{id} | 요청 param(id) | 수정 정보 | 201 정상 등록 400 오류 |
| 선택 일정 삭제 | DELETE | schedule/{id} | 요청 param(id) |  | 204 정상 삭제  |

---
API 명세서 링크 : https://documenter.getpostman.com/view/39379207/2sAY4xBhVZ

---

### ERD ###

https://www.erdcloud.com/p/JC63WtsJfQ8DwPkwJ
