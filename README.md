## 🐶❤ Lovepet 반려동물 쇼핑몰

#### 프로젝트 소개
스프링부트 기반 반려동물 쇼핑몰 웹사이트

#### 개발 환경
IDE: IntelliJ

JDK Version: JDK15

Spring Boot: 2.6.3

Data Base: MySQL

Build Tool: Maven

#### 주요기능
스프링 시큐리티를 이용한 회원가입 및 로그인, 로그아웃 기능 구현

상품 등록 및 수정 기능

상품 검색 및 페이징 기능

장바구니 추가 및 결제 기능

테스트 코드 작성

### ERD
<img width="60%" alt="ERD" src="https://user-images.githubusercontent.com/65703902/162138223-3b203c26-dc15-4640-a209-ae5f53014c12.JPG">


#### 화면구성(사진캡쳐)
<img width="60%" alt="메인" src="https://user-images.githubusercontent.com/97068969/162135315-89d1cfae-5f49-4b27-93c2-f3929f89776f.PNG">
✔메인 화면 - 로그인 카테고리만 보여짐
<br/>
<br/>
<br/>
<hr>

<img width="60%" alt="상품리스트" src="https://user-images.githubusercontent.com/97068969/162135375-53e780e2-a5db-422d-8990-8ce07abe6a50.PNG">
✔상품 리스트
<br/>
<br/>
<br/>

<img width="60%" alt="상세" src="https://user-images.githubusercontent.com/97068969/162142660-3a3f7066-8c04-4789-9a31-20511d685b00.PNG">
<img width="60%" alt="상세페이지" src="https://user-images.githubusercontent.com/97068969/162142369-5a8a0a46-631c-4ab0-aad8-b6e89a133067.PNG">
✔상품 상세 페이지 - 품절시 장바구니, 주문 모두 불가
<br/>
<br/>
<br/>

<img width="60%" alt="서치" src="https://user-images.githubusercontent.com/97068969/162136562-9bf21dcf-7e41-463a-9bda-d22e726ee4ad.PNG">
✔키워드 검색시 페이징 완료된 해당 상품 표시
<br/>
<br/>
<br/>

<img width="40%" alt="로그인" src="https://user-images.githubusercontent.com/97068969/162137046-4f8b42b9-0ff1-4ea9-bebf-f36b066b0411.PNG">
<img width="40%" alt="ㅎㅇㄱㅇ" src="https://user-images.githubusercontent.com/97068969/162137229-2bf62a52-8bd5-4c89-abb9-96e6bbd3ecdf.PNG">
✔로그인과 회원가입 페이지
<br/>
<br/>
<br/>

<img width="60%" alt="상품등록" src="https://user-images.githubusercontent.com/97068969/162140357-71faa0d2-83ed-49f8-968b-cb5be3b4e7d4.PNG">
✔admin 계정 로그인 후 상품 등록 페이지 - 판매중, 품절 선택 가능하며 이미지 최대 5개 업로드 가능
<br/>
<br/>
<br/>

<img width="60%" alt="상품관리" src="https://user-images.githubusercontent.com/97068969/162140915-cb996619-4310-41fa-95e0-6874c44f7052.PNG">
<img width="60%" alt="상품 수정" src="https://user-images.githubusercontent.com/97068969/162140926-02978132-8937-4b83-8ef5-b66111c6c516.PNG">
✔상품 관리 페이지 내 상품 클릭 시 수정 가능 / 수정하기 클릭시 메인 페이지로 리다이렉트
<br/>
<br/>
<br/>

<img width="60%" alt="장바구니" src="https://user-images.githubusercontent.com/97068969/162143219-af6fa2b3-832e-4181-ae18-3a1f35705026.PNG">
✔장바구니 페이지에서 결제 가능
<br/>
<br/>
<br/>

<img width="60%" alt="구매" src="https://user-images.githubusercontent.com/97068969/162143602-76878fa0-7596-409c-9527-94ef02bd378f.PNG">
✔장바구니 페이지에서 주문시 구매이력 페이지로 리다이렉트
✔주문 취소 기능 구현 -> 주문 취소된 수량은 다시 상품 수량으로 플러스

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>





### 트러블슈팅

<details>
<summary>로그인 실패 시 에러 메세지 출력 후 다시 로그인 페이지로 이동이 되지 않음</summary>
<div markdown="1">       
<br>
  
  ✔  <b>해결방법</b>
  
  ![image](https://user-images.githubusercontent.com/65703902/162134535-92ab14b9-06cc-490b-b5dc-76a26cdfda11.png)
  
기존에는 ajax 요청만 고려해서 UNAUTHORIZED 에러를 반환하도록 하였는데, ajax의 경우 request header에 XMLHttpRequest 라는 값이 세팅돼서 나옴. 그럴 경우만 UNAUTHORIZED 에러를 반환하고, 나머지 경우는 로그인 페이지로 리다이렉트 해줘야 함.

</div>
</details>

<details>
<summary>NoSuchMethodError 문제</summary>
<div markdown="1">       
<br>
  
  members/new를 실행 시

```

Cannot render error page for request [/members/new] and exception [org.thymeleaf.context.IWebContext.getExchange()Lorg/thymeleaf/web/IWebExchange;] as the response has already been committed. As a result, the response may have the wrong status code.

```

member/login 실행 시

```
Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Handler processing failed; nested exception is java.lang.NoSuchMethodError: org.thymeleaf.context.IWebContext.getExchange()Lorg/thymeleaf/web/IWebExchange;] with root cause

```
  
✔  <b>해결방법</b>
  
  버전 문제.  build.gradle에서 implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5' 으로 implementation
  
</div>
</details>

### 개선점

- API 로그인, 결제 시스템 추가 필요
- 촉박한 시간으로 커뮤니티 기능 추가 실패. 추후 구현 예정
- 관리자 공지 게시판
