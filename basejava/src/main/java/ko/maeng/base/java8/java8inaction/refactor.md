### 리팩토링 자바8

자바8로 업데이트됨에 따라 이전 코드들을 간결하고 쉽게 이해
할 수 있도록 구현할 수 있다.

총 3개의 기능으로 다양한 리팩토링을 보여줄 수 있다.
- 익명 클래스를 람다로 리팩토링
- 람다를 메서드 레퍼런스로 리팩토링
- 명령형 데이터 처리를 스트림으로 리팩토링

먼저 결론부터 이야기하자면, 모든 것은 적절히 사용해야 한다. 단순히 가독성을 위한
리팩토링을 추구하게 되면 성능 저하를 일으킬 여지가 생긴다. 

단적인 예로 스트림을 사용하지 않고 처리가 가능한 부분에 가독성을 위하여 스트림을 처리하게 되면 성능이
저하되게 된다.

이런 부분에서 주의해서 항상 코드를 작성하도록 하자.

아래는 익명 클래스를 람다로 리팩토링하는 부분이다.

```java
// before
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Hello");
    }
};

// after
Runnable r2 = () -> System.out.println("Hello");
```

위와 같이 익명클래스를 람다로 리팩토링이 가능하지만, 모든 익명 클래스를 람다로
변환할 수 있는 것은 절대 아니다.

첫째, 익명 클래스에서 사용한 this와 super는 람다 표현식에서 다른 의미를 갖는다.

익명클래스에서 this는 익명 클래스 자신을 가리키지만, 람다에서 this는 람다를 감싸는
클래스를 가리킨다.

둘째, 익명클래스는 감싸고 잇는 클래스의 변수를 가릴 수 있다(shadow variable).

하지만 다음 코드에서 보여주는 것처럼 람다로는 변수를 가릴 수 없다(아래 코드는 컴파일되지 않는다).

```java
int a = 10;
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        int a = 2;  // 컴파일 에러
        System.out.println(a);
    }
};

Runnable r2 = new Runnable() {
    @Override
    public void run() {
        int a = 2;  
        System.out.println(a); // 정상적으로 2가 출력된다.
    }
};
```

마지막으로 익명클래스를 람다로 바꾸면 콘텍스트 오버로딩에 따른 모호함이 초래될 수 있다.

익명클래스는 인스턴스화할 때 명시적으로 형식이 정해지는 반면 람다의 형식은 콘텍스트에 따라
달라지기 때문이다.

다음은 이와 같은 문제가 일어날 수 있음을 보여주는 예제 코드다.

```java
interface Task{
    public void execute();
}
public static void doSomething(Runnable r) {r.run();}
public static void doSomething(Task a) {r.execute();}
```

Task를 구현하는 익명클래스를 전달할 수 있다.

```java
doSomething(new Task() {
    public void execute(){
        System.out.println("Danger!");
    }
});
```

하지만 익명클래스를 람다 표현식으로 바꾸면 메서드를 호출할 때 Runnable과 Task 모두
대상 형식이 될 수 있으므로 모호함이 발생한다.

```java
doSomething(() -> System.out.println("Danger!")); // 해당 코드가 Runnable을 인자로 받는지, Task를 인자로 받는지 알 수 없다.
```

이런 문제는 명시적 형변환으로 모호함을 제거할 수 있다.

```java
doSomething((Task)() -> System.out.println("Danger!"));
```

