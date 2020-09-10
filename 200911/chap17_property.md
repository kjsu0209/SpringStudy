# 프로퍼티 설정   
외부 프로퍼티 파일을 이용해서 스프링 빈을 설정할 수 있다.  
PropertySourcesPlaceholderConfigurer 빈을 설정하고, @Value 어노테이션을 지정해 해당 프로퍼티 값을 사용할 수 있다.   

## PropertySourcesPlaceholderConfigurer    
이 타입의 빈을 설정하는 메소드는 static이고, setLocations() 메소드로 프로퍼티 파일 목록을 전달받는다. 
빈은 setLocations()로 받은 파일 목록을 읽어와 @Value 어노테이션으로 지정한 값에 맞는 프로퍼티 값을 치환한다.  
빈 메소드, 세터 메소드에서도 @Value 어노테이션을 붙일 수 있다.    

## @Value 어노테이션
@Autowired와 비슷한 역할로, 프로퍼티 설정을 주입하는 역할을 하는 어노테이션이다.    
@Value로 시스템 변수를지정할 수 있다. 또한 SpEL을 지원하는데, SpEL이란 Spring 표현 언어로, 런타임시 객체를 조작할 수 있다.
