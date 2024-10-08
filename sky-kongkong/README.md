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
* 시설물 불편 신고를 할 수 있습니다. 
  * 가끔 스탠드가 깜박이거나 프린트가 고장나는 등의 문제가 생기기도 합니다.
  * 그런 경우 사장님 개인 휴대폰 번호로 문자를 보내야 하는데, 이 점이 사용자-관리자 모두 불편할 것이라는 생각이 들었습니다.
* 스카에 가지 않아도 자리 현황을 확인할 수 있습니다.
  * 학생들 시험기간이 언제나 자리가 없는데, 짐을 잔뜩 들고 방문을 했다가 허탕치고 돌아오는 경험이 많았습니다.
  * 집에서 미리 확인하고 나올 수 있다면 훨씬 좋을 거라고 생각합니다.
    
### ERD

<img width="802" alt="스크린샷 2024-10-04 오후 10 37 01" src="https://github.com/user-attachments/assets/5aa8adfb-af8d-443b-9bd6-180135844184">
   
20241004 기준
    
**user** - 사용자
|컬럼명|타입|null 허용|index|default|코멘트|
|--|--|--|--|--|--|
|user_id|int|not null|PK|auto_increment|user pk|
|phone|varchar(11)|not null|o||휴대폰번호|
|password|varchar(??)|not null|||비밀번호(암호화필수)|
|created_at|datetime|not null||current_timestamp|가입시간|
|updated_at|datetime|null|||수정시간|

  
**user_time** - 사용자가 가지고 있는 스카 시간

|컬럼명|타입|null 허용|index|default|코멘트|
|--|--|--|--|--|--|
|user_time_id|int|not null|PK|auto_increment|user_time pk|
|user_id|int|not null|FK||associate with user|
|remain_minutes|int|not null||0|남은 시간(분)|
|created_at|datetime|not null||current_timestamp|가입시간|
|updated_at|datetime|null|||수정시간|

**usage** - 스카 시간의 사용

|컬럼명|타입|null 허용|index|default|코멘트|
|--|--|--|--|--|--|
|usage_id|int|not null|PK|auto_increment|usage pk|
|user_id|int|not null|FK||associate with user|
|desk_number|int|not null|o||책상번호|
|check_in|datetime|not null||current_timestamp|입실시간|
|use_minutes|int|null|||사용된 시간(분)|
|check_out|datetime|null|||퇴실시간|


**report** - 스카 이용 중 불편 신고

|컬럼명|타입|null 허용|index|default|코멘트|
|--|--|--|--|--|--|
|report_id|int|not null|PK|auto_increment|report pk|
|user_id|int|not null|FK||associate with user|
|is_solved|tinyint|not null||0|해결 여부|
|category|varchar(10)|not null|||enum("불편사용자", "시설고장", "건의사항", "기타")|
|detail|varchar(300)|not null|||구체적인 내용|
|created_at|datetime|not null||current_timestamp|가입시간|
|updated_at|datetime|null|||수정시간|

### API 설계
**user**
* 회원가입 POST /user/register
* 로그인 POST /user/login
* 본인 정보 조회 GET /me
* 사용자 시간 충전 POST /user/add
* [admin]
  * 사용자 목록 조회 /user
  * 사용자 목록 조회 /user/:userId
  * 사용자 시간 충전 POST /user/:userId/add


**cafe**
* 현재 스카 이용 상태 GET /cafe
* 입실 POST /cafe/:usage_id/enter
* 퇴실 POST /cafe/:usage_id/leave
* [admin](공용으로 사용해도 될지도)
  * 입실 POST /cafe/:usage_id/enter
  * 퇴실 POST /cafe/:usage_id/leave

**report**
* 불편 신고 POST /report
* [admin]
  * 불편 신고 목록 조회 GET /report?{query}
  * 불편 신고 조회 GET /report/:reportId
  * 신고 상태 변경 PUT /report/:reportId



