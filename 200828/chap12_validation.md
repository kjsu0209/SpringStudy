# 커맨드 객체 값 검증      

### 커맨드 객체란?   
HTTP 통신으로 들어오는 값을 자동으로 바인딩하는 객체.      

### 검증 처리란?     
폼 값 검증과 에러 메세지 처리를 포함하는 검증 과정이다. 스프링은 커맨드 객체를 검증하는 Validator 인터페이스와 에러 메세지를 출력하는 Errors, ValidationUtils 클래스를 제공한다.        

### 필요한 모듈 (pom.xml)   
```
<dependency>
    <groupId>javax.validation</groupId>
		<artifactId>validation-api</artifactId>
		<version>1.1.0.Final</version>
</dependency>
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-validator</artifactId>
  <version>5.4.2.Final</version>
</dependency>
<dependency>
  <groupId>javax.validation</groupId>
  <artifactId>validation-api</artifactId>
  <version>1.1.0.Final</version>
</dependency>
```

## 검증 처리 방법     
### 1. 글로벌 범위 Validator      
모든 컨트롤러에 적용할 수 있는 Validator이다.     
- 구현 방법     
MVC 설정 파일에 getValidator 메소드를 오버라이딩한다. getValidator 메소드는 알맞은 검증 객체를 리턴한다. (검증 객체는 Validator 인터페이스를 구현한다.)
컨트롤러에서 검증할 파라미터의 앞에 @Valid 어노테이션을 붙여준다. 
이렇게 하면 요청 처리 메소드 실행 전에 글로벌 범위의 Validator가 해당 타입을 검증할 수 있는지 확인하고, 결과를 Errors 객체에 저장한다.  
          
- 주의점    
Errors 타입 파라미터가 없으면 검증 실패시 400 에러를 응답한다.

### 2. 컨트롤러 범위 Validator (@InitBinder)     
- 구현 방법     
컨트롤러에 @InitBinder 어노테이션을 붙인 initBinder 메소드를 구현한다. 이 방법 역시 검증할 파라미터의 앞에 @Valid 어노테이션을 붙여준다.    
```
	//컨트롤러 범위에 적용할 Validator 설정 => 이 컨트롤러 안에서 파라미터 앞에 @Valid 붙이면 설정된 Validator를 사용
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new RegisterRequestValidator());
	}
```
@InitBinder가 붙은 메소드는 컨트롤러의 요청 처리 메소드를 실행하기 전에 매번 실행된다.       
initBinder 메소드에 전달되는 WebDataBinder는 Global Validator를 포함하고 있다. 
이때 메소드 내부에서 setValidator() 메소드를 사용하면 글로벌 범위가 무시되고, addValidator()를 사용하면 글로벌 범위를 적용한 다음 컨트롤러 범위 Validator를 적용한다.    

### 3. Bean Validation이 제공하는 어노테이션 이용 (@NotBlank, @Email ...)      
Validator를 구현하지 않아도 어노테이션만으로 값 검증을 할 수 있게 해준다. 커맨드 클래스에서 검증하고 싶은 값의 선언 부분에 원하는 어노테이션을 붙여주기만 하면 된다.
값을 검증하는 객체를 OptionalValidatorFactoryBean이라 하며, @EnableWebMvc를 설정하면 글로벌 범위의 Validator가 따로 없을 경우 이 객체를 글로벌 범위 Validator로 사용한다.
이 방법 역시 검증하고 싶은 파라미터 앞에 @Valid를 붙이면 된다.        


## 오류 메세지    

커스텀 메세지를 사용하려면 메세지 프로퍼티 파일에 코드를 추가하면 된다.      
<규칙>       
* 어노테이션이름.커맨드객체모델명.프로퍼티명        
* 어노테이션이름.프로퍼티명        
* 어노테이션이름         
        
### JSP에서 커맨드 객체의 에러 메세지 출력하기      
```
<form:errors path="code" \> //스프링에서 제공하는 form:errors 태그 사용
```
위 태그에서 path 속성은 프로퍼티 이름을 지정한다. 메세지 코드가 여러 개일 경우, 먼저 검색되는 메세지 코드를 사용한다. 
path 속성을 지정해 주지 않으면 글로벌 에러에 대한 메세지를 출력한다. 


## Bean Validation 1, 2의 주요 어노테이션     
### Bean Validation 1.1      
* @AssertTrue, @AssertFalse : 값 true false 검사. 유효하면 null 리턴.       
* @DecimalMax, @DecimalMin : 지정 값 범위에 해당하는지 검사. inclusive 옵션으로 지정값 포함 여부를 정할 수 있다.         
* @Max, @Min : 지정값 범위에 해당하는지 검사.    
* @Digits: 자릿수 검사          
* @Size: 길이나 크기 검사        
* @Null, NotNull       
* @Pattern : 정규표현식으로 검사     

### Bean Validation 2.0        
* @NotEmpty: null과 길이 0을 같이 검사.    
* @NotBlank: 공백이 아닌지 검사.      
* @Positive, @Negative       
* @Email        
* @Future : 미래 시간인지 검사      
* @Past: 과거 시간인지 검사          
