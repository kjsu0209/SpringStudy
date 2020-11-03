## REST(Representational State Transfer)
### REST란?
HTTP를 활용한 소프트웨어 아키텍처로 네트워크상에서 client와 server 사이의 통신 방식 중 하나이다.

### 목표
- 애플리케이션 분리/통합
- 확장성
- 인터페이스의 범용성(다양한 클라이언트와 통신 가능)
- 캡슐화
- ...

### 6가지 조건    
1. 인터페이스 일관성
1. 무상태(Stateless): 각 요청 간 클라이언트의 상태가 서버에 저장되면 안됨
1. 캐시 처리 기능: 클라이언트는 응답을 캐싱할 수 있어야 함
1. 계층화: 중간 서버를 두어 로드 밸런싱이나 캐시를 통해 시스템 규모의 확장성을 향상시킬 수 있어야 함
1. Code on demand: 서버가 클라이언트가 실행시킬 수 있는 로직을 전송하여 기능을 확장시킬 수 있어야 함.
   => Client-side scripting: 클라이언트 사이드에서 사용자와 상호작용할 수 있게 한다.(ex. ajax, innerHTML)
1. 클라이언트/서버 구조

### 기본 개념
- HTTP URI: 자원 명시
- HTTP Method: CRUD Operation 적용 (GET, POST, PUT, DELETE)

### 구성 요소
- 자원: URI
- 동작: HTTP Method
- 표현: JSON, XML


## JavaScript Scope
: 변수에 접근할 수 있는 범위
### 종류
- global scope
: 변수가 함수 바깥이나 중괄호 바깥에 선언되었을 경우
-> const나 let사용할때 충돌 생기면 에러 발생. var은 덮어쓰기만 함.
- local scope
-> 함수 스코프: 함수 내부에서 변수 선언
-> 블록 스코프: 중괄호 내부에서 변수 선언

### 함수 호이스팅(Function hoisting)
- 함수가 선언되면 현재 스코프의 최상단으로 호이스팅(hoist)됨.
- 함수 표현식으로 선언되면 호이스팅되지 않음.

### 네스팅된 스코프(Nested scopes)
- 함수가 다른 함수 내부에서 정의되었다면, 내부함수는 외부함수의 변수에 접근 가능함(lexical scoping)
- 단, 외부함수는 내부함수의 변수에 접근 안됨.
- 교재에서는 이를 이용해 save, update등의 함수를 블록 스코프 내부의 클로저로 만들었음.

### 클로저(Closures)
- 클로저는 내부에 작성된 함수로 외부 함수의 변수에 접근할 수 있다.  
- 목적  
=> 사이드 이펙트 제어   
함수에서 값을 반환할 때를 제외하고 무언가를 수행할때 사이드 이펙트가 발생함.
ajax나 timeout 생성하는 등의 작업이 해당됨.
클로저를 반환하는 함수를 만들어서 원하는 시점에 사이드 이펙트가 생길 만한 작업을 할 수 있다.
```
function sideEffect(){
  return function(){
    setTimeout( ... );
  }
}

const doEventLater = sideEffect();
// 원하는 시점에 클로저 반환하는 함수 실행
doEventLater();
```

=> private 변수 생성   
클로저가 해당 함수 바깥에서 유일하게 특정 변수를 노출한다면,
특정 변수는 private 변수로 캡슐화되어 클로저 반환 함수를 실행하지 않으면 접근할 수 없게 된다.

