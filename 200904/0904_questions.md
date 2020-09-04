### Session과 Cookie의 차이
Http의 Connectionless,Stateless 특징 때문에 지속적으로 클라이언트의 정보를 확인하기 위한 방법

- Sessiosn
	- session도 Cookie 기반 
	- 관련 데이터는 객체 타입으로 Server에 저장
	- Cookie에 비해 보안성이 좋음

- Cookie
	- 사용자의 디스크/웹 브라우저 메모리에 텍스트 파일로 저장
	- 요청 속도 빠름
	- Client PC에 저장되기 때문에, 다른 사용자에 의해 임의 변경이 가능 => session에 비해 보안성이 낮은 이유

	< 쿠키와 세션의 라이프사이클 >
	[링크](https://interconnection.tistory.com/74)

=> session의 서버 메모리 부하 문제 때문에 JWT(Json Web Token)을 사용하기도...

### 스프링 필터와 인터셉터의 차이점
프로세스의 공통 업무 처리

- Filter ( dofilter )
	- Web application에 등록(web.xml)
	- Dispatcher servlet 앞단에서 정보 처리
	- J2EE 표준스펙
- Interceptor
	- Spring의 Context에 등록
	- Dispatcher servlet에 Handler로 가기 전에 정보 처리
	- Spring Framework 자체 기능

=> 인코딩/보안 관련 처리와 같이 web app의 전역적 처리에 대한 로직 : filter
=> 클라이언트에서 들어오는 디테일한 처리(인증,권한)에 대한 로직 : Interceptor

### Java의 데이터 타입 ( Array, ArrayList,LinkedList, Map, HashMap )의 특징과 차이
	[링크](https://velog.io/@hygoogi/%EA%B8%B0%EC%88%A0%EB%A9%B4%EC%A0%91-%EC%A4%80%EB%B9%84%ED%95%98%EA%B8%B0)

### Jenkins
- CI 툴
- SW 개발 통합 서비스
	[링크](https://ict-nroo.tistory.com/31)

### Garbage Collection(GC) - 자바 메모리 관리
- JVM 기반 java는 OS단에서 메모리 누수를 잡을 수 없어서, 정리되지 않거나, 앞으로 사용하지 않고 메모리는 가지고 있는 객체들에 대해 Garbage로 분류, 메모리가 부족할 때 free함
	[링크](https://velog.io/@litien/%EA%B0%80%EB%B9%84%EC%A7%80-%EC%BB%AC%EB%A0%89%ED%84%B0GC)

### URL / URI / URN

- URL이 가장 큰 범주, 내부 부분집합에 URN과 URL이 존재

- URL ( Uniform Resource Locator ) : 
	- 자원
	- 웹 상에 서비스를 제공하는 각 서버들에 있는 리소스의 위치를 표시하기 위한 것
- URI ( Uniform Resource Identifier )
	- 통합 자원 식별자
	- URI는 인터넷에서 요구되는 기본조건, 인터넷 프로토콜에 항상 붙어다님
	- 인터넷 상 자원을 식별하기 위한 문자열 구성

	=> 대체로 URI 라는 단어를 많이 사용한다고 함

- URN ( Uniform Resource Name )
	- 위치와 상관없이 리소스의 이름값을 이용해서 접근하는 방식
	- 독립적인 이름이기 때문에 리소스를 여기저기 옮기더라도 문제없이 작동

ex )  원주소 : http://test.tistory.com/19
	  변경주소 : http://test.tistory.com/test/19
	  => error, 그러나 URN 사용시 상관없이 19라는 이름으로
	  리소스를 찾음