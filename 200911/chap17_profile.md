# 프로필   
개발 목적 설정과 실제 서비스 제공 목적의 설정을 구분해서 작성하기 위한 스프링 기능.    

- 설정 집합에 프로필 이름을 지정하면 스프링 컨테이너는 설정 집합 중에서 지정한 프로필의 설정을 사용한다.        
ex) dataSource 빈이 실서버, 테스트서버용으로 두 개가 필요할 때, 프로필 이름을 각각 다르게 지정하면 활성화한 프로필에 맞추어 사용할 수 있다.     

## 사용 방법      
### 1. 프로필 설정     
1) @Configuration 설정에서 프로필 사용하기     
@Configuration 어노테이션을 붙인 설정 클래스 파일에 @Profile 어노테이션을 붙이면 프로필 이름을 지정할 수 있다. 
아래 코드에서는 프로필 이름을 dev로 설정했으니, dev 프로필이 활성화되면 이 설정 클래스가 사용된다.   
```
@Configuration
@Profile("dev")
public class TestConfig{ 
...
}
```
2) 중첩 클래스를 이용해서 한꺼번에 프로필 설정하기       
중첩 클래스에 static인 설정 클래스를 넣고 각 클래스에 @Profile 어노테이션을 붙이면 된다.  
```
@Configuration
public class ProfileConfig{
@Configuration
@Profile("dev")
public static class DevConfig(){...}

@Configuration
@Profile("real")
public static class RealConfig(){...}
```
3) 두개 이상의 프로필 설정하기      
@Profile 어노테이션에 쉼표(,)로 두개 이상의 프로필을 설정할 수 있다.     
ex) @Profile("real,test")      
프로필 값 앞에 느낌표(!)를 붙이면 해당 프로필이 활성화되지 않을 때 사용할 설정을 지정할 수 있다.    
ex) @Profile("real") => real 프로필이 활성화 되지 않을 때 아래 설정을 사용한다.
      
### 2. 프로필 선택    
프로필 선택은 컨테이너를 초기화하기 전에 완료해야 한다. setActiveProfiles() 메소드를 사용하면 활성화할 프로필을 지정할 수 있다.   
```
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
context.getEnvironment().setActiveProfiles("프로필값");
context.register(설정 클래스...);
context.refresh();
```
+ 위 코드에서 register 메소드를 호출하고 나서 프로필을 지정하면 프로필 설정이 반영되지 않아 오류가 생긴다.     
+ 두 개 이상의 프로필을 활성화시키고 싶을 경우 setActiveProfiles("", "") 메소드를 사용한다.      

커맨드라인에서 시스템 프로퍼티에 사용할 프로필 값을 적용하는 방법도 있다. 이 방법을 사용하면 setActiveProfile 메소드를 호출하지 않아도 된다.
```
java -Dspring.profiles.active=프로필값 main.Main
```
+ OS의 환경 변수에 지정할 수도 있다.       
+ 우선순위는 setActiveProfiles > 시스템프로퍼티 > OS 환경 변수
        
