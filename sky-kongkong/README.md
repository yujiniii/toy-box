## 스카이콩콩 - 스터디 카페 관리 프로젝트
> **인프런 워밍업클럽 2기** - 백엔드  
> [입문자를 위한 Spring Boot with Kotlin](https://inf.run/Y6bSZ) 강의를 수강하며 미니 프로젝트를 구현합니다.

1. [개요](#개요)   
   a. [기능](#기능)   
   b. [ERD](ERD)   


<img width="300" alt="잉" src="https://github.com/user-attachments/assets/4f110141-c0ec-4744-b0e0-7d4cb9be5c6c">


------

## 개요
평소 집 앞 스터디카페(이하 스카)를 자주 가는데,   
그때 느꼈던 불편사항을 웹 프로젝트를 통해 해소할 수 있지 않을까 해서 구상하게 되었습니다.
   
### 기능
* 웹을 통해 시설물 불편 신고를 할 수 있습니다. 
  * 가끔 스탠드가 깜박이거나 프린트가 고장나는 등의 문제가 생기기도 합니다.
  * 그런 경우 사장님 개인 휴대폰 번호로 문자를 보내야 하는데, 이 점이 사용자-관리자 모두 불편할 것이라는 생각이 들었습니다.
* 웹을 통해 스카에 가지 않아도 자리 현황을 확인할 수 있습니다.
  * 학생들 시험기간이 언제나 자리가 없는데, 짐을 잔뜩 들고 방문을 했다가 허탕치고 돌아오는 경험이 많았습니다.
  * 집에서 미리 확인하고 나올 수 있다면 훨씬 좋을 거라고 생각합니다.
    
### ERD

<img width="977" alt="erd" src="https://github.com/user-attachments/assets/edfaa0d8-1d78-418a-a2b5-4b07733597db">


   
20241101 기준


**users 테이블**

| 이름               | 키        | 타입         | NULL 허용 여부 | 디폴트     | 코멘트          |
|--------------------|-----------|--------------|----------------|------------|-----------------|
| user_id           | PK        | bigint       | NULL           |AUTO_INCREMENT| 사용자 ID       |
| name              |           | varchar(255) | NOT NULL       |            | 사용자 이름     |
| password          |           | varchar(255) | NOT NULL       |            | 비밀번호        |
| phone             |           | varchar(255) | NOT NULL       |            | 전화번호        |
| created_date_time |           | timestamp(6) | NOT NULL       |            | 생성 일시       |
| updated_date_time |           | timestamp(6) | NOT NULL       |            | 수정 일시       |


**user_time 테이블**

| 이름               | 키        | 타입         | NULL 허용 여부 | 디폴트     | 코멘트           |
|--------------------|-----------|--------------|----------------|------------|------------------|
| user_time_id      | PK        | bigint       | NULL           |AUTO_INCREMENT| 사용자 시간 ID   |
| remain_minutes    |           | integer      | NULL           |            | 남은 시간 (분)   |
| user_id           | FK        | bigint       | NULL           |            | 사용자 ID        |
| created_date_time |           | timestamp(6) | NOT NULL       |            | 생성 일시        |
| updated_date_time |           | timestamp(6) | NOT NULL       |            | 수정 일시        |



**report 테이블**

| 이름               | 키        | 타입         | NULL 허용 여부 | 디폴트     | 코멘트         |
|--------------------|-----------|--------------|----------------|------------|----------------|
| report_id         | PK        | bigint       | NULL           |AUTO_INCREMENT| 리포트 ID      |
| is_solved         |           | boolean      | NULL           |            | 해결 여부      |
| detail            |           | varchar(255) | NULL           |            | 상세 내용      |
| category          |           | enum         | NULL           |            | 리포트 카테고리 |
| user_id           | FK        | bigint       | NULL           |            | 사용자 ID      |
| created_date_time |           | timestamp(6) | NOT NULL       |            | 생성 일시      |
| updated_date_time |           | timestamp(6) | NOT NULL       |            | 수정 일시      |



**usage 테이블**

| 이름               | 키        | 타입         | NULL 허용 여부 | 디폴트     | 코멘트           |
|--------------------|-----------|--------------|----------------|------------|------------------|
| usage_id          | PK        | bigint       | NULL           |AUTO_INCREMENT| 사용 내역 ID     |
| is_active         |           | boolean      | NULL           |            | 활성화 상태      |
| seat_id           | FK        | integer      | NULL           |            | 좌석 ID          |
| check_in          |           | timestamp(6) | NULL           |            | 체크인 일시      |
| check_out         |           | timestamp(6) | NULL           |            | 체크아웃 일시    |
| use_minutes       |           | varbinary(255)| NULL          |            | 사용 시간 (분)   |
| created_date_time |           | timestamp(6) | NOT NULL       |            | 생성 일시        |
| updated_date_time |           | timestamp(6) | NOT NULL       |            | 수정 일시        |
| user_id           | FK        | bigint       | NULL           |            | 사용자 ID        |



**seat 테이블**

| 이름               | 키        | 타입         | NULL 허용 여부 | 디폴트     | 코멘트          |
|--------------------|-----------|--------------|----------------|------------|-----------------|
| seat_id           | PK        | integer      | NOT NULL       |            | 좌석 ID         |
| is_active         |           | boolean      | NULL           |            | 활성화 상태     |
| created_date_time |           | timestamp(6) | NOT NULL       |            | 생성 일시       |
| updated_date_time |           | timestamp(6) | NOT NULL       |            | 수정 일시       |






### API 설계
**user**
* 회원가입 POST api/auth/register
* 로그인 POST api/auth/login
* 본인 정보 조회 GET api/user/me
* 사용자 시간 충전 POST api/user/add
* 사용자 탈퇴 POST api/user/withdraw
* [admin]
  * 사용자 목록 조회 api/admin/user
  * 사용자 목록 조회 api/admin/user/:userId
  * 사용자 시간 충전 POST api/admin/user/:userId/add


**cafe**
* 현재 스카 이용 상태 GET /cafe
* 입실 POST /cafe/enter
* 퇴실 POST /cafe/leave
* 퇴실 POST /cafe/seat-info
* [admin](공용으로 사용해도 될지도)
  * 입실 POST api/admin/cafe/:usage_id/enter
  * 퇴실 POST api/admin/cafe/:usage_id/leave

**report**
* 불편 신고 POST api/report
* [admin]
  * 불편 신고 목록 조회 GET api/admin/report?{query}
  * 불편 신고 조회 GET api/admin/report/:reportId
  * 신고 상태 변경 PUT api/admin/report/:reportId
 

----

## 기능 구현 및 화면


#### 회원
|회원가입|로그인|
|--|--|
|<img width="1440" alt="스크린샷 2024-11-01 오후 2 12 39" src="https://github.com/user-attachments/assets/b1a5e55a-f635-4442-a4d9-26aac927edb1">|<img width="1440" alt="스크린샷 2024-11-01 오후 2 12 28" src="https://github.com/user-attachments/assets/90554394-263c-4335-b613-407eadd4dac9">|

* 시큐리티를 사용한 접근제한 


#### 메인페이지, 마이페이지
|메인|마이|
|--|--|
|<img width="1440" alt="스크린샷 2024-11-01 오후 2 17 28" src="https://github.com/user-attachments/assets/d0f9940b-a7ef-4ad3-bfea-8c9e6e1e12a6">|<img width="1440" alt="스크린샷 2024-11-01 오후 2 34 34" src="https://github.com/user-attachments/assets/f9f7de5c-9ddc-463c-ba1f-485d78438b0d">|

#### 시간충전
|시간충전|
|--|
|<img width="1440" alt="스크린샷 2024-11-01 오후 2 35 25" src="https://github.com/user-attachments/assets/d820435a-a00a-49ce-9897-0398cabb012f">||

* 일단 바로 충전되게 해둠


#### 자리확인, 입실, 퇴실
|자리확인|입실|퇴실|
|--|--|--|
|<img width="1440" alt="스크린샷 2024-11-01 오후 2 33 54" src="https://github.com/user-attachments/assets/39fa9aef-a2c9-4088-bc3c-6527405fbb27">|<img width="1440" alt="스크린샷 2024-11-01 오후 2 37 37" src="https://github.com/user-attachments/assets/4a36be97-e869-4750-a8f5-9d08eec96965">|<img width="1440" alt="스크린샷 2024-11-01 오후 2 38 02" src="https://github.com/user-attachments/assets/b3bcfe67-6dcf-4e28-96ac-0175ad7e5128">|

* 자리확인 및 남은자리 표시
* 입실 또는 퇴실 가능


### 불편신고
|신고|
|--|
|<img width="1440" alt="스크린샷 2024-11-01 오후 2 35 04" src="https://github.com/user-attachments/assets/49f41e91-b511-4d9f-9f51-5cebb646de1a">|




---

## todo
- [ ] 내 사용기록 보기  
- [ ] 관리자용 어드민페이지 만들기


