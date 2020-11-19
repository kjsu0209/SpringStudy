# 질문
## 1. 스프링 시큐리티
- 스프링 시큐리티는 __스프링 기반의 어플리케이션에서 보안을 위해 인증과 권한 부여를 사용하여 접근을 제어하는 프레임워크이다.__

### 1-1. 스프링 시큐리티 특징
1. 보안과 관련해서 체계적으로 많은 옵션을 제공해주기 때문에 개발자는 하나하나 로직을 작성하지 않아도 된다.
2. __Filter 기반으로 동작하며__ MVC와 분리하여 관리할 수 있다.
3. __어노테이션을 통해__ 간단히 설정할 수 있다.
4. 기본적으로 __세션 & 쿠키__ 방식으로 인증한다.

### 1-2. 필터
- __인증관리자(Authentication Manager)와 접근 결정 관리자(Access Decision Manager)를 통해 사용자의 리소스 접근을 관리한다.__
- 인증 관리자는 UsernamePasswordAuthenticationFilter, 접근 결정 관리자는 FilterSecurityInterceptor가 수행한다.

1. SecurityContextPersistenceFilter — 요청(request)전에, SecurityContextRepository에서 받아온 정보를 SecurityContextHolder에 주입한다.
2. LogoutFilter — 주체(Principal)의 로그아웃을 진행합니다. 주체는 보통 유저를 말한다.
3. __UsernamePasswordAuthenticationFilter__ — (로그인) 인증 과정을 진행한다.
4. DefaultLoginPageGeneratingFilter — 사용자가 별도의 로그인 페이지를 구현하지 않은 경우, 스프링에서 기본적으로 설정한 로그인 페이지를 처리한다.
5. BasicAuthenticationFilter — HTTP 요청의 (BASIC)인증 헤더를 처리하여 결과를 SecurityContextHolder에 저장한다.
6. RememberMeAuthenticationFilter — SecurityContext에 인증(Authentication) 객체가 있는지 확인하고RememberMeServices를 구현한 객체의 요청이 있을 경우, Remember-Me(ex 사용자가 바로 로그인을 하기 위해서 저장 한 아이디와 패스워드)를 인증 토큰으로 컨텍스트에 주입한다.
7. AnonymousAuthenticationFilter — SecurityContextHolder에 인증(Authentication) 객체가 있는지 확인하고, 필요한 경우 Authentication 객체를 주입한다.
8. SessionManagementFilter — 요청이 시작된 이 후 인증된 사용자 인지 확인하고, 인증된 사용자일 경우SessionAuthenticationStrategy를 호출하여 세션 고정 보호 메커니즘을 활성화하거나 여러 동시 로그인을 확인하는 것과 같은 세션 관련 활동을 수행한다.
9. ExceptionTranslationFilter — 필터 체인 내에서 발생(Throw)되는 모든 예외(AccessDeniedException, AuthenticationException)를 처리한다.
10. __FilterSecurityInterceptor__ — HTTP 리소스의 보안 처리를 수행한다.

## 2. OAuth2
- __다양한 플랫폼 환경에서 권한 부여를 위한 산업 표준 프로토콜이다.__
- __네이버로 로그인, 페이스북으로 로그인, 카카오로 로그인 등__
- 기존의 OAuth는 구현이 복잡하고 웹이 아닌 어플리케이션에서 지원이 부족했다. 또한 암호화를 하는 과정이 번거로웠다.
- 이런 단점들을 개선한 버전이 OAuth 2.0이다.

### 2-1. OAuth2 특징
1. OAuth 기능이 단순해졌다.
2. https를 필수로 하며 암호화는 https에 맡긴다.
3. 1.0은 한 가지의 인증방식을 사용했지만 2는 다양한 인증방식을 지원한다.
4. api 서버에서 인증서버를 분리할 수 있다.

### 2-2. 구성
- Resource owner(자원 소유자): Resource server(구글, 페이스북, 카카오 등)의 계정을 소유하고 있는 사용자를 의미합니다.
- Client: 구글, 페이스북, 카카오 등의 API 서비스를 이용하는 제 3의 서비스
- Authorization Server(권한 서버): 권한을 관리해 주는 서버, Access Token, Refresh Token을 발급, 재발급 해주는 역할을 합니다.
- Resource Server: OAuth2 서비스를 제공하고, 자원을 관리하는 서버입니다.
- Access Token: Authorization Server로 부터 발급 받은 인증 토큰, Resource Server에 전달하여 서비스를 제공 받을 수 있습니다.

### 2-3. 인증 과정
1. 네이버로 로그인 버튼을 클릭하면 네이버 로그인 페이지로 이동한다.
2. 고객이 네이버에 로그인했다.  
2-1. 네이버 서버에서 해당 고객이 네이버 회원임을 확인했다.  
2-2. 이 고객과 관련된 Access Token을 발급한다.  
2-3. Access Token 값은 URL에 묶는다.  
3. 네이버에서 고객을 내 서비스로 리다이렉트 시켜준다.  
4. 내 서비스에서 Access Token을 받는다.
5. 네이버가 정보를 건네주는 페이지(api 요청 URL)에 Access Token을 넘겨준다.
6. 네이버가 정보를 전달해준다.

