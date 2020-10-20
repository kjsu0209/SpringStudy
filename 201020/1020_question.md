# 교재 내용
## 테스트 코드 작성에서의 중요한 점

- 원래 클래스(Main 폴더)에 대하여 의존성 주입이 되도록 하기 본 책에서는 @Autowired 어노테이션 / MockMvc 클래스를 사용

### MockMvc
- 서버에 배포하기 않고도 스프링 MVC의 동작을 재현함

### 동작 과정
1. 테스트 케이스는 DispatcherServlet에 요청할 데이터(경로/파라미터)를 설정
2. MockMvc는 DispatcherServlet에 요청을 보냄
3. DispatcherServlet은 테스트용으로 확장된 TestDispatcherServlet임
4. DispatcherServlet은 요청을 받아 매핑정보를 보고 올바른 핸들러 메서드를 호출
5. 테스트 케이스 메서드는 MockMvc가 반환하는 실행결과를 받아 맞는지 검증

### MockMvc의 주입받는 방법

1. 자동 주입
	클래스 선언 위에
	1. @SpringBootTest + @AutoConfigureMocMvc 어노테이션 - 통합테스트
	2. @WebMvcTest 어노테이션 - MVC 슬라이드 테스트

2. 직접 생성 (단독 모드)
'''java
@Before
public void before() {
  	mockMvc = MockMvcBuilders.standaloneSetup(MyController.class)
          	    .alwaysExpect(MockMvcResultMatchers.status().isOk())
           	    .build();
}
'''
---

# 관련 질문
### 1. 스프링 부트의 특징
- auto configuration
- embedded servlet container
- starter-dependencies
- Actuator
- Spring Boot CLI ( Groovy )

### 2. Spring boot에서 auto configuration 이란?
- 빌드하고자 하는 어플리케이션이 어떤 종류의 어플리케이션인지 탐지해서 어플리케이션이 필요로 하는 컴포넌트들을 자동으로 설정해줌

ex ) Security 설정이 되어있으면 특정 페이지에 대한 접근 권한이 없는데 요청된 경우 스프링 부트 자체의 로그인 페이지를 보여줌
ex ) MVC가 설정되어있다 -> DispatcherServlet이 설정됨

### 3. Spring boot의 Starter dependency
- 스프링부트에서 의존성 관리 문제를 해결해줌
- 특정 dependency (ex : thymeleaf, JPA)가 현재 프로젝트에 몇 버전이 적합한지 어떤 의존성 리스트가 필요한지 알 필요 없음
- gradle 혹은 Maven build file에 추가하면, 의존성 jar 파일이 자동으로 로드 됨

### 4. Spring boot actuator
'''xml
<artifactId>spring-boot-starter-actuator</artifactId>
'''
- 어플리케이션의 상태를 종합적으로 정리하여 제공해줌
- 스프링 부트 어플리케이션의 상태를 관리해줌
	- 상태정보 ( properties, beans, 구동된 auto configurations )
	- 각종 추상화 클래스 제공

### 5. Groovy
- JVM 상에서 동작하는 동적 스크립팅 언어
- 자바에는 없는 간편 표기법을 지원, 외에 리스트,맵, 정귯기을 위한 구문 제공
=> 프로그래밍을 쉽고 간결하게 해줌

### 6.리팩토링
- [코드의 나쁜냄새와 리팩토링](https://m.blog.naver.com/PostView.nhn?blogId=magnking&logNo=220973095825&proxyReferer=https:%2F%2Fwww.google.com%2F)
