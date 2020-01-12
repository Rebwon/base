## Immutable이란?

Immutable의 사전적 의미는 불변의, 변경할 수 없는 이라는 뜻이다.

Immutable Class는 변경이 불가능한 클래스이며, 가변적이지 않은 클래스이다. 만들어진 Immutable 객체는 레퍼런스 타입이기 때문에,
Heap 영역에 생성된다.

자바의 Immutable Class : String, Boolean, Integer, Long, Double 등등

Immutable 클래스는 Heap 영역에서는 변경이 불가하며, 재할당은 가능하다.  

```java
// 재할당
String a = "Immutable";
a = "Immutable Class";
```
a가 참조하고 있는 Heap영역의 객체가 바뀌는 것이지, Heap 영역의 값이 바뀌는 것이 아니다.

a가 처음에 참조한 값(Immutable)이 새로운 값(Immutable Class)로 변경되는 것이 아니라, 아예 새로운 값을 만들고
그 값을 a가 참조하고 있는 형태이다. 그러므로 처음에 참조한 값은 어느 누구도 참조하고 있지 않기 때문에 GC의 대상이 된다.

#### Immutable의 특징

장점 : 생성자, 접근 메서드에 대한 방어 복사가 필요하지 않음. 멀티 스레드 환경에서 동기화(synchronized) 처리 없이 
객체를 공유할 수 있음 (Thread-safe). 불변이기 때문에 객체가 안전함.

단점 : 객체가 가지는 값마다 새로운 객체가 필요함. 따라서 메모리 누수와 새로운 객체를 계속 생성해야하기 때문에 성능저하를 발생시킬 수 있음.

#### Immutable Class를 만드는 법.

Immutable Class를 만들기 위해서 알아야 할 2가지.
1. 멤버 변수를 final로 선언.
2. 접근 메서드를 구현하지 않는다.(setter)