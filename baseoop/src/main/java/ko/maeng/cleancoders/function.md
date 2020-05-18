## Function Structure

### CQS(Command Query Separation)

- Command
  - 시스템의 상태 변경 가능
  - Side Effect를 갖는다
  - 아무것도 반환하지 않는다.(리턴타입 void)

- Query
  - Side Effect가 없다
  - 계산 값이나 시스템의 상태를 반환

- CQS 정의
  - 상태를 변경하는 함수는 값을 반환하면 안된다.
  - 값을 반환하는 함수는 상태를 변경하면 안된다. 
  
CQS를 지키지 못한 코드
```
User u = authorizer.login(username, password);
```

사용자의 이름과 비밀번호를 받아서 로그인을 하는 절차와 이후 로그인된 사용자의 User 객체를 반환하는 2가지 일을 하고 있다.
user를 얻으려면 login을 무조건 해야만 하고, login을 하면 원치 않아도 user를 반환하고 있다.

CQS를 지키는 코드
```
authorizer.login(username, password);
User u = authorizer.getUser(username);
```

### Low of Demeter

CQS와 Tell Don't ask를 지키기 위한 Low of Demeter

- 함수가 시스템의 전체를 알게 하면 안된다.
- 개별 함수는 아주 제한된 지식만 가져야 한다.
- 객체는 요청된 기능 수행을 위해 이웃 객체에게 요청해야 한다.
- 요청을 수신하면 적절한 객체가 수신할 때까지 전파되어야 한다.

Law of Demeter는 아래와 같은 일련의 규칙을 통해 Tell, Don't ask를 형식화한다.
- 아래와 같은 객체들의 메서드만 호출할 수 있다.
  - 파라미터로 전달된 객체
  - 자기 function 내에서 local하게 생성한 객체
  - 필드로 선언된 객체
  - 전역 객체
- 이전 메서드 호출의 결과로 얻는 객체의 메서드를 호출하면 안된다.
  - o.getX().getY().doSomething()