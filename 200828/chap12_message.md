# 메세지      
스프링의 메세지 기능은 프로퍼티 파일에 있는 특정 값을 <spring:message>의 커스텀 태그를 이용해 출력하는 것이다.    
      
### 사용법
1. label.properties 파일을 UTF-8로 작성한다.      
1. MVC 설정 클래스에 messageSource 메소드를 오버라이딩해 프로퍼티 파일을 등록해준다.     
1. JSP에서 message 커스텀 태그로 메세지를 불러온다.   

      
### 프로퍼티 파일 작성법     
- 일반적인 방법
코드=문자     

ex) code_name=<b>String</b>       
- 메시지 인자 처리
문자 부분에 {인덱스}를 넣으면 커스텀 태그에서 argument로 넘겨준 파라미터 리스트 중 인덱스번째의 값을 넣어준다.    

ex)     
JSP
```
// registerRequest.name 값을 인자로 넘겨준다.
<spring:message code='register.done' arguments='${registerRequest.name}' />
```

  메세지 프로퍼티 파일

```
// 인자로 받은 값 중 0번째 인덱스에 있는 값을 넣어준다
register.done={0}님, 안녕하세요
```
        
### MessageSource 빈     
메세지 관리 인터페이스인 MessageSource를 정의하고, 특정 언어에 해당하는 메세지 프로퍼티를 가져오기 위해 getMessage() 메소드를 사용한다.


### 태그 라이브러리 설정    
```
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
```

### 다국어 지원      
label 뒤에 _언어 형식의 접미사를 붙인다. 특정 언어에 해당하는 메세지 파일이 없으면 언더바가 없는 프로퍼티 파일을 사용한다.
