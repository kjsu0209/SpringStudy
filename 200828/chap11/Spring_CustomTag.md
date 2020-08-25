# 스프링 MVC 커스텀 태그

### 1. <form> 태그를 위한 커스텀 태그 : <form:form>

```java
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form>
</form:form>
```

- 태그의 method 속성과 action 속성을 지정하지 않으면 method : post , action : 현재 요청 url로 설정됨

```java
<form:form modelAttribute="loginCommand">
</form:form>
```

- 커맨드 객체 이름값은 지정하지 않으면 "command"가 됨. 아니라면 위와 같이 modelAttribute를 통해 지정

- **<form:form> 커스텀 태그는 <form> 태그와 관련하여 다음 속성을 추가 제공**
    - action  : 폼 데이터를 전송할 URL을 입력
    - enctype : 전송될 데이터의 인코딩 타입, HTML <form> 태그와 속성 동일
    - method : 전송 방식, HTML <form> 태그 속성과 동일

### 2. <input> 관련 커스텀 태그 : <form:input>, <form:password>, <form:hidden>

```java
<form:input path = "email"/>

<input id="email" name="email" type="text" value=""/>

<form:hidden path = "defaultSecurityLevel"/>
<form:password path = "password"/>
```

### 3. <select> 관련 커스텀 태그 : <form:select>, <form:options>, <form:option>

- 선택 옵션 제공시 사용, 옵션 정보는 대개 컨트롤러에서 생성해서 뷰에 전달하는 경우가 많다. <select> 태그에서 사용할 옵션 목록을 Model을 통해 전달한다.

```java
@GetMapping("/login")
public String form(Model model){
	List<String> loginTypes = new ArrayList<>();
	loginTypes.add("일반회원");
	loginTypes.add("기업회원");
	loginTypes.add("헤드헌터회원");
	model.addAttribute("loginTypes",loginTypes);
	return "login/form";
```

```java
<p>
	<label for="loginType">로그인 타입</lable>
	<form:select path="loginType" items="${loginTypes}"/>
</p>

혹은

<p>
	<label for="loginType">로그인 타입</lable>
	<form:options items="${loginTypes}"/>
</p>
```

- <form:options> 커스텀 태그는 주로 콜렉션에 없는 값을 <option> 태그로 추가할 때 사용한다.

- 특정 객체에 대한 세부 값들을 <option>을 통해 나타낼 때

```java
<form:options items="${jobCodes}" itemLabel="label" itemValue="code" />
```

스프링이 제공하는 위 세개의 커스텀 태그의 장점은 커맨드 객체의 프로퍼티 값과 일치하는 값을 갖는 <option>을 자동으로 선택해 준다는 점 selected = "selected"

### 4. 체크박스 관련 커스텀 태그 : <form:checkboxes>, <form:checkbox>

- 한개 이상의 값을 커맨드 객체의 특정 프로퍼티로 저장하고 싶다면, 배열이나 List와 같은 타입을 사용해서 값을 저장함

```java
<p>
	<label>선호 OS</label>
	<form:checkboxes items="${favoriteOsNames}" path="favoriteOs" />
</p>
```

- <form:checkbox> 커스텀 태그는 연결되는 값 타입에 따라 처리 방식이 달라진다. 만약 boolean 타입이라면 "checked" 속성을 설정한다.

### 5. 라디오버튼 관련 커스텀 태그 : <form:radiobuttons>, <form:radiobutton>

- 여러가지 옵션 중에서 한가지를 선택하는 경우 radio 타입의 <input> 태그를 사용한다

```java
<p>
	<label>주로 사용하는 개발툴</label>
	<form:radiobuttons items="${tools}" path="tool"/>
</p>
```

### 6. <textarea> 태그를 위한 커스텀 태그 : <form:textarea>

- 게시글 내용처럼 여러 줄을 입력받아야 하는 경우 사용

```java
<p>
	<label for = "etc">기타<label>
	<form:textarea path = "etc" cols="20" rows="3" />
</p>
```

### 7. CSS 및 HTML 태그와 관련된 공통 속성

- CSS 관련 속성
    - cssClass : HTML의 class 속성값
    - cssErrorClass : 폼 검증 에러가 발생했을 때 사용할 HTML의 class 속성값
    - cssStyle : HTML의 style 속성값
- HTML 관련 속성
    - id, title, dir
    - disabled, tabindex
    - onfocusm onblur, onchange
    - onclick, ondbclick
    - onkeydown, onkeypress, onkeyup
    - onmousedown, onmousemove, onmouseup
    - onmouseout, onmouseover

 Seunghye Jung