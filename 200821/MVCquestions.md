# Spring MVC 질문 1         
-----------------       
1. Spring MVC framework란?
Spring MVC는 Spring framework의 핵심 구성요소 중 하나로, MVC를 위한 컴포넌트들을 제공해 유연하고 빠르게 웹 애플리케이션을 만들 수 있게 돕는다. 

2. 다른 MVC와 비교했을 때 장점은?    
* 역할 구분이 명확하다.   
* 재사용이 쉽다.      
* 기타 OOP 장점...     

3. DispatcherServlet은 무엇인가?       
Spring MVC framework는 request-driven 모델이고, Servlet(서블릿)이 중앙에 위치해 모든 HTTP 요청과 응답을 수행하도록 디자인되어있다. DispatcherServlet은 여기서 IoC 컨테이너를 통합시켜 스프링의 기능들을 사용할 수 있게 한다.    
*IoC 컨테이너란? : Inversion Of Control Container로 스프링 컨테이너라고도 불린다. 
          
4. Spring MVC의 front controller는?      
Front controller는 웹 앱에서 모든 요청을 다루는 컨트롤러이다. Spring MVC에서는 DispatcherServlet이 front controller에 해당된다.       
           
5. Viewresolver pattern은 무엇이고 MVC에서 무슨 역할을 하는가?      
View Resolver는 J2EE 패턴으로, 동적으로 View에 데이터를 보여줄 수 있게 한다. 컨트롤러는 매핑하고 싶은 view의 이름을 리턴하고, View Resolver는 그에 맞는 view를 선택한다.   
   
