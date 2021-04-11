# ❤HongKong Communication Web For Tripper❤

### 🌹 프로젝트 소개 (2021.02.22 ~ 진행중)
-------
홍콩 여행에 관심이 있는 여행객들을 위한 사이트입니다.
<br>
아직 홍콩을 여행해보지 못한 사람들에게는 홍콩에 대한 정보를 얻을 수 있고
<br>
홍콩여행을 다녀온 사람들에게는 여행 정보를 공유할 수 있는 사이트를 만들고자 했습니다.
<br>

### 🌹 개발에 사용된 것...<br>
--------
SpringFramework, Github <br>
Gradle, JPA, MyBatis, MySQL, JQuery <br>
Java, HTML, CSS, JavaScript, Ajax, JQuery <br>
<br>

### 🌹 게시판 개요
--------
#### 1. 메인페이지 (OpenAPI - WeatherAPI, SNS Login API)
#### 2. 홍콩 여행 후기, NOTICE (JPA, Ajax를 이용한 API)
#### 3. PHOTO 후기(FILE을 이용한 API)
#### 4. Q&A(MyBatis를 이용한 API)
#### 5. Spring Security(Spring Security를 통한 인증,인가 ) - 진행중
<br>
<br>

### 💡 메인페이지(OpenAPI - WeatherAPI, SNS Login API)
---------
**JSON**데이터를 활용해 날씨와 로그인 **OpenAPI** 기능을 이용하여 홍콩 여행 커뮤니티 유저들에게 편리한 기능들을 제공했다.<br>
◽ **Weather API**<br>
:  날씨정보의 여러 데이터들 중 가장 필요한 정보인 섭씨온도와 날씨에 대한 설명 그리고 직관적으로 볼 수 있는 날씨 이미지를 적용하였다.
<br>
◽ **SNS Login API(Google, Naver, Kakao)**<br>
: 사용자들에게 편리한 로그인 서비스를 제공하기 위해 Google, Naver, Kakao의 OpenAPI서비스를 활용하여 SNS Login 기능을 하였다.<br>


### 💡 홍콩 여행 후기, NOTICE(JPA, Ajax를 이용한 API)
--------
◽ **JPA**를 사용하여 관계형 데이터베이스의 관리 표현하여 유저들끼리 게시글을 써서 정보를 주고받는 게시판을 만들었다.<br>
◽ **Ajax**의 비동기 통신을 이용한 댓글기능을 구현하였다.
<br>
<br>

### 💡 PHOTO 후기(FILE을 이용한 API)
--------
◽ **File**입출력을 구현하여 사용자들간 사진을 이용한 정보공유가 가능한 게시판을 만들었다.
<br>
<br>

### 💡 Q&A(MyBatis를 이용한 API)
--------
◽ **MyBatis**를 활용하여 XML과 annotation을 사용하여 SQL문으로 객체를 연결한 게시판을 만들었다.
<br>
<br>

### 💡 Spring Security(Spring Security를 유저 권한설정)
--------
◽ **Spring Security**의 인증, 인가를 활용하여 guest, user, admin의 권한을 달리 부여함으로 글의 수정, 삭제 등의 기능이 유저마다 다르게 부여되도록 한다.
<br>
◽ **OAuth2** SNS Login기능을 제공하는 각 사이트의 token을 발급받아 AccessToken을 활용하여 SNS Login 기능을 제공하였다.<br>
<br>
<br>
