# 타임리프 

타임리프는 Server-side Java 템플릿 엔진으로, HTML, XML, JS, CSS를 처리할 수 있어 웹과 단일 환경을 지원한다.
타임리프의 주 목적은 템플릿을 유지보수가 용이하도록 제작하는 방법을 제공하는 것이다. 
그래서 타임리프는 Natural Templates의 개념을 적용해 디자인에 영향을 미치지 않는 템플릿에만 로직을 주입하는 방식을 사용한다.
이 방법은 디자인 팀과 개발 팀의 갭을 줄이도록 해준다.      

## 사용하는 방법
- 자체 컨트롤러 인터페이스를 사용하는 경우         
타임리프를 사용하기 위해서는 응답 프로세스마다 타임리프 context를 설정해주어야 한다. 
컨트롤러에서 뷰로 모델 객체를 넘겨주는 대신, 타임리프 context에 변수로 설정해주면 된다.
- Spring MVC        
스프링의 ViewResolver를 타임리프에서 제공하는 ViewResolver로 사용하도록 설정하면 된다. 그 외의 컨틀롤러 구현은 동일하다.
- Spring boot       
스프링 부트는 기본으로 타임리프 템플릿 파일을 검색하기 때문에 타임리프 모듈을 의존으로 추가하기만 하면 된다. 

## 타임리프의 주요 expression     
- 변수 식: ${OGNL}
${x}: Thymeleaf context 또는 request 속성에 있는 변수 x를 반환한다.   
${param.x} : request 파라미터에 있는 변수 x를 반환한다.   
${session.x} : 세션 속성 x를 반환한다.  
${application.x} : servlet context 속성 x를 반환한다.  
- 메시지 식: #{코드}     
외부 메시지 자원에서 코드에 해당하는 문자열을 읽어와 출력. <spring:message>와 동일      
ex)
```
<title th:text="#{member.register}">title</title>
```
- 링크 식: @{링크}         
링크 문자열을 생성한다. 링크 경로에 변수를 넣을 수도 있다.      
```
<a href="#" th:href="@{/members}">목록</a>
<a href="#" th:href="@{/members/{memId}(memId=${mem.id})}">상세 정보</a>
```
- 선택 변수식: *{OGNL}        
th:object 속성은 특정 객체를 선택하는데, 선택 변수식은 선택한 객체를 기준으로 나머지 경로를 값으로 사용한다.
th:object로 member를 선택했다면, member.name이나 member.age를 경로로 사용할 때 앞에 member를 붙이지 않아도 경로로 인정된다.
```
<div th:object="${member}"> // member 객체 선택
  <span th:text="*{name}">name</span> // 선택 객체를 기준으로한 경로 사용
</div>
```
- 조건식: ${x}? 'true' : 'false'

> OGNL(Object-Graph Navigation Language)는 객체의 프로퍼티 경로를 표현할 때 사용한다.

## 타임리프 기본 속성    
- th:text : 식의 값을 태그 body로 출력한다. HTML 특수 문자는 엔티티 형식(&lt;)으로 변환한다.      
- th:utext : 식의 값을 HTML 특수 문자를 포함하여 그대로 출력한다.
- th:href : <a>태그의 href 속성을 지정한다.
- th:action : <form>태그의 action 속성 값을 치환한다.
- th:value : <input>태그같은 폼 관련 태그의 value 속성을 지정한다.
- th:each : 반복 처리에 사용된다. forEach와 작동 원리가 같다.
```
// members 변수에 담긴 각 객체에 대해 tr 태그를 반환한다.
<tr th:each="mem : ${members}"> 
  <td th:text="${mem.id}">Id</td>
</tr>
```
- th:fragement : 다른 템플릿에 삽입될 때 사용할 fragemnt 선택자를 지정한다.
```
<div th:fragment="선택자">
  &템플릿 이름;
</div>
```
- th:insert="~{템플릿 이름::fragment선택자}" : 다른 템플릿의 fragement를 삽입한다.

## 타임리프 식 객체    
타임리프에서 제공하는 식 객체를 사용하면 뷰에 값을 보여주기 전에 가공할 수 있다.        
- #strings: String 타입을 위한 기능 제공
- #numbers : 포맷팅 등 숫자 타입 기능 제공
- #dates, #calendars: 날짜 타입 기능 제공
- #lists, #sets, #maps: list, set, map 자료형 타입을 위한 기능 제공

## 타임리프와 HTML5     
타임리프를 사용하기 위해서는 th:*로 시작하는 속성이름을 붙여야 한다. 
하지만 HTML5 표준을 따른다면, 브라우저는 HTML 태그가 아닌 것들을 자동으로 제외시킨다. 
(브라우저는 th:text같은 이해할 수 없는 모든 속성들을 무시한다.)            
타임리프의 data attribute syntax를 사용하면 이 문제를 해결할 수 있다. 
접두사 <em>data-</em>는 기존에 사용되던 세미콜론 대신에 -를 사용한다. 
```
<p th:text="#{home.welcome}">...</p> // 브라우저가 무시함
<p data-th-text="#{home.welcome}">...</p>
```

## 타임리프와 lazy loading
데이터를 실제로 사용할 때만 불러오고 싶은 경우에 변수를 LazyContextVariable로 설정한다.
```
context.setVariable(
     "users",
     new LazyContextVariable<List<User>>() {
         @Override
         protected List<User> loadValue() {
             return databaseRepository.findAllUsers();
         }
     });
```
뷰에서 해당 변수를 불러올 때, 조건문으로 초기화 시키지 않게 한다면 Context는 데이터를 불러오지 않는다.
```
<ul th:if="${condition}">
  <li th:each="u : ${users}" th:text="${u.name}">user name</li>
</ul>
```


