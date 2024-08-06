# ❗주의사항

1. 완성된 프로젝트는 <u>**yeon 브랜치**</u>에 올려져있습니다.</br> 반드시 브랜치를 바꿔서 프로젝트를 받아주세요.</br>

2. <u>**application.yml**</u> 에서 업로드 된 이미지를 저장할 경로를 설정하는 부분이 있습니다. </br> 이 부분도 잊지 마시고 설정해주세요.</br>

# 📃 ERD

![ERD](https://github.com/user-attachments/assets/0a5824f5-576b-457e-a2db-a7ae75871fdc)

# 📝 프로그램 소개

채용공고 사이트 

# ⭐ 기술스택

### 프론트엔드
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat-squre&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=flat-squre&logo=css3&logoColor=white">  <img src="https://img.shields.io/badge/jquery-0769AD?style=flat-squre&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/jsp-8A8A8A?style=flat-squre&logo=jsp&logoColor=white">

### 백엔드

<img src="https://img.shields.io/badge/java-007396?style=flat-squre&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat-squre&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=flat-squre&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/jpa-8A8A8A?style=flat-squre&logo=jpa&logoColor=white">   <img src="https://img.shields.io/badge/mysql-4479A1?style=flat-squre&logo=mysql&logoColor=white">

# ⭐ 기능소개

**공통**: 로그인, 채용공고 목록 보기, 채용공고 상세 보기, 검색</br>
**일반유저**: 지원내역 보기, 지원하기, 지원취소</br>
**기업유저**: 채용공고 등록, 수정, 삭제</br>

## 📍로그인

![로그인 페이지](https://github.com/user-attachments/assets/b80b8baa-ce18-41d7-9194-8f4b68fc0c8a)

일반유저, 기업유저 모두 **동일한 로그인** 폼에서 로그인합니다. </br>

**JPA를 사용**하여 DB에서 로그인폼으로 올라온 정보를 조회한 후 반환되는 **User 엔티티를 UserDTO로 변환**하여 **세션에 저장**합니다. </br>

**서버에서 로그인 성공여부를 체크**하여 성공 시 "로그인 성공" , 실패 시 "아이디 혹은 비밀번호를 확인해주세요." 라는 메시지가 클라이언트로 보내집니다.</br>

**특정 페이지**는 로그인이 되어있지 않으면 접근조차 불가능하도록 서버에서 **인터셉터**를 설정해두었습니다.

## 📍채용공고 목록 보기

![채용공고 목록 페이지](https://github.com/user-attachments/assets/d48b3563-cb03-49e2-a2fb-ad93ec6839c1)

기업유저가 등록한 채용공고들을 간략하게 볼 수 있습니다. </br>

서버에서는 JPA를 이용하여 job_opening 테이블에 있는 모든 행을 가져와 **JobOpening 엔티티 리스트**에 담은 후 **JobOpeningDTO 리스트로 변환**하여 클라이언트로 보냅니다.</br>

## 📍채용공고 상세 보기-지원하기

![채용공고 상세 페이지](https://github.com/user-attachments/assets/8fe57a6f-a7d1-4211-8c0a-cca4a835a882)

각 채용공고마다 붙어있는 **상세 버튼**을 누르면 상세페이지로 이동합니다. </br>

상세 버튼을 누를 시 Get 요청으로 **jobOpeningId 값**을 서버로 보내게 되고 서버에서는  DB에서 job_opening_id 컬럼의 값과 jobOpeningId 값이 동일한 행을 찾아서 마찬가지로 **JobOpening 엔티티**로 받아와 **JobOpeningDTO 변환**합니다. </br>

이때 JobOpeningDTO 에는 **CompanyId** 값이 들어있으므로 이를 이용해 **같은 기업의 채용목록** 또한 JobOpening 엔티티 리스트로 받아와 **JobOpeningDTO 리스트**로 변환하여 jobOpeningId 값으로 찾은 **JobOpeningDTO와 함께 클라이언트로** 보내줍니다.

### 지원하기

일반유저의 경우 **지원하기 버튼**을 클릭하면 지원할 수 있습니다. </br>

이 요청은 jobOpeningId 값과 함께 넘어가게 되고 서버에서는 **먼저 세션을 확인**해 현재 이 유저가 **로그인 상태인지 확인**하고 그 다음은 **일반유저인지, 기업유저인지 확인**하여 로그인 되어있는 일반유저가 아닐 시 요청을 실패처리합니다. </br>

그 후 이 유저가 **이미 지원한 공고인지 확인**한 후에 이미 지원한 공고라면 실패처리를, 지원하지 않았던 공고라면 **application DB에 정보를 추가**한 후 성공처리를 반환합니다. 


## 📍검색

![검색 창](https://github.com/user-attachments/assets/fa6674b1-2547-4d0d-9de7-c4e66c1ce1b3)

메인페이지의 검색 영역에서 **카테고리 별로 검색**을 할 수 있습니다. 

![검색결과](https://github.com/user-attachments/assets/d6d3a2d3-db9f-4be8-b2ab-f7ce412f15af)

검색버튼을 누르면 카테고리와 검색어가 서버로 넘어가게 되고 **JPA의 리포지토리**에 선언해둔 **커스텀 검색 메소드**를 이용해 DB에서 JobOpening 엔티티 리스트를 받아와 JobOpeningDTO 리스트로 변환하여 클라이언트로 넘겨줍니다.

## 📍마이페이지(일반유저)-지원취소

![일반유저 마이페이지](https://github.com/user-attachments/assets/1023af9f-939a-4f62-bbf4-b3c1d99fd08a)

일반유저의 마이페이지에서는 일반유저가 **지원한 채용공고 목록**을 볼 수 있고 여기서 **지원을 취소**할 수 있습니다. </br>

**지원취소 버튼**을 누르게 되면 jobOpeningId 값이 서버로 넘어가게 되고 서버는 먼저 **로그인 여부를 확인**한 다음 로그인 되어있다면 **application DB**에 해당 값을 가진 행이 있는 지 확인 후 **삭제처리**하여 성공여부를 클라이언트로 반환합니다.</br>

## 📍마이페이지(기업유저)-채용공고 등록 & 수정 & 삭제

![기업유저 마이페이지](https://github.com/user-attachments/assets/40ccfd8a-5376-4ddd-87d9-7525fa88500c)

기업유저의 마이페이지에서 기업유저는 **채용공고를 등록**할 수도 있고 **채용공고를 수정**할 수도 있습니다. </br>

### 채용공고 등록

![채용공고 등록 페이지](https://github.com/user-attachments/assets/e7daa28c-2d04-46d6-80e8-f0d8cb8a8bf6)

채용내용은 **CKEditor5** 를 사용하여 채울 수 있게 하였으며 채용포지션, 채용보상금, 사용기술, 채용내용은 하나의 객체에 담겨 서버로 보내지게 되고 서버에서는 이를 **jobOpeningDto** 에 한꺼번에 받습니다. </br>

여기에서도 마찬가지로 먼저 **로그인 여부를 확인**한 뒤 현재 로그인 된 유저의 **UserDTO 객체에 포함된 companyId 속성 값**과 **jobOpeningDto**를 이용하여 **완전한 jobOpening 객체**를 만듭니다. </br>

이때 CKEditor5 로 만들어진 **채용내용**은 DB에서 **JSON 컬럼**에 저장되므로 이를 주고 받기 편하게 하기 위해 **JsonConverter** 를 설정해두었습니다. </br>

이제 이 완전한 객체를 **job_opening DB에 저장**한 후 성공여부를 클라이언트에 반환합니다. </br>

### 채용공고 수정 & 삭제

![채용공고 수정 페이지](https://github.com/user-attachments/assets/59101a88-1bcd-4f99-b429-052740fda201)

수정페이지는 등록페이지와 동일하며 수정 후 **수정하기 버튼**을 누르면 서버에서는 **이미 등록된 행의 정보**를 JobOpeningDTO에 담아와서 클라이언트에서 보내준 **새로운 내용으로 수정**한 후 **UPDATE** 처리합니다.

**삭제하기 버튼**을 클릭하면 수정과 동일하게 **이미 등록된 행이 있는지 확인**한 후 **삭제처리** 합니다. 

**취소하기 버튼**을 클릭하면 마이페이지로 돌아갑니다.

감사합니다 :)
