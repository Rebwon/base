## oop

Tell dot't ask (묻지 말고 시켜라)
- 그 객체의 값을 가져와서 동작을 수행하지 말고 객체에게 위임해라

데미테르의 법칙
- **메서드에서 생성한 객체**의 메서드만 호출한다
- **파라미터로 받은 객체**의 메서드만 호출한다
- **필드로 참조하는 객체**의 메서드만 호출한다

데미테르의 법칙을 어기는 유형
- 연속된 getter 메서드 호출
- 임시 변수의 get 호출이 많음

연속된 get 메서드 호출은 아래와 같은 모양을 갖는다.
```
value = someObject.getA().getB().getValue();
```

두 번째는 임시 변수에 할당된 객체의 get을 호출하는 코드가 많은 경우이다. 이는 사실상 첫번째 증상과 동일하지만, 코드가 흩어져 있을 경우 발견하기 어렵다.
```
A a = someObject.getA();
B b = a.getB();
value = b.getValue();
```

### 객체지향 설계 과정

1. 제공해야할 기능을 찾고, 또는 세분화하고, 그 기능을 알맞은 객체에게 할당한다.
   - 기능을 구현하는데 필요한 데이터를 객체에 추가한다. 객체에 데이터를 먼저 추가하고 그 데이터를 이용하는 기능을 넣을 수도 있다.
   - 기능은 최대한 캡슐화해서 구현한다.
2. 객체 간에 어떻게 메세지를 주고받을 지 결정한다.
3. 과정1과 과정2를 개발하는 동안 지속적으로 반복한다.

### 다형성과 추상화

```java
public class FlowController {
    private boolean useFile;
    
    public FlowController(boolean useFile){
        this.useFile = useFile;
    }
    public void process() {
    	ByteSource source = null;
        if(useFile)
            source = new FileDataReader();
        else
            source = new SocketDataReader();
        byte[] data = source.read();
    }
}
```

위와 같은 클래스에서 데이터를 읽는 클래스가 추가되어도 FlowController가 바뀌지 않도록 하는 방법에는 다음 두 가지가 존재한다.

- ByteSource 타입의 객체를 생성하는 기능을 별도의 객체로 분리한 뒤, 그 객체를 사용해서 ByteSource를 생성
- 생성자(또는 다른 메서드)를 이용해서 사용할 ByteSource 전달받기

이 중 첫번째 방법을 사용해보자.

```java
public class ByteSourceFactory{
    public ByteSource create() {
        if(useFile)
        	return new FileDataReader();
        else
        	return new SocketDataReader();
    }

    private boolean useFile() {
        String useFileVal = System.getProperty("useFile");
        return useFileVal != null && Boolean.valueOf(useFileVal);
    }
    
    // 싱글톤 패턴 적용
    private static ByteSourceFactory instance = new ByteSourceFactory();

    public static ByteSourceFactory getInstance() {
        return instance;
    }

    private ByteSourceFactory() {}
}
```

위 클래스를 적용한 코드는 다음과 같다.

```java
public class FlowController {
    public void process() {
    	ByteSource source = ByteSourceFactory.getInstance().create();
        byte[] data = source.read();
    }
}
```

이제 새로운 요구사항이 반영되어도 수정할 부분은 ByteSourceFactory 뿐이다.

### 상속 오용의 문제

상속을 사용했을 때 발생할 수 있는 문제는 다음과 같다.
- 상위 클래스 변경의 어려움
- 클래스 개수 증가
- 상속의 오용 문제

위와 같은 방법을 해결하는 방법은 객체 조립을 이용하는 것이다.

```java
public class FlowController {
    private Encryptor encryptor = new Encryptor();  // 필드로 조립
    public void process() {
    	ByteSource source = ByteSourceFactory.getInstance().create();
        byte[] data = source.read();
        
        byte[] encryptData = encryptor.encrypt(data);
    }
}
```

한 객체가 다른 객체를 조립해서 필드로 갖는다는 것은 다른 객체의 기능을 사용한다는 의미를 내포한다.

위 코드의 경우 FlowController 클래스는 Encryptor 클래스의 암호화 기능을 사용하고 있다. 즉, Encryptor 클래스를 재사용하는 것이다.

### SOLID

1. 단일 책임 원칙
   - 하나의 책임만 갖는다.
   - 서로 다른 이유로 변경되는 것을 알아채려면 많이 해봐야 한다.
   - 잘 모를땐 메서드를 실행하는 것이 누구인지 확인해보자.

2. 개방 폐쇄 원칙
   - 사용되는 기능 확장에는 열려있고 기능을 사용하는 코드의 변경에는 닫혀있어야 한다.
   - 개방 폐쇄 원칙을 적용하려면, 추상화 혹은 상속을 이용하는 것이다.

3. 리스코프 치환 원칙
   - 리스코프 치환 원칙은 개방 폐쇄 원칙을 받쳐 주는 다형성에 관한 원칙을 제공한다.
   - 상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로
   동작해야 한다.
   
4. 인터페이스 분리 원칙
   - 인터페이스는 그 인터페이스를 사용하는 클라이언트를 기준으로 분리해야 한다.

5. 의존 역전 원칙
   - 고수준 모듈은 저수준 모듈의 구현에 의존해서는 안 된다. 저수준 모듈이 고수준 모듈에서 정의한 추상 타입에
   의존해야 한다.

### 개방 폐쇄 원칙
   
아래와 같은 HTTP 응답 프로토콜에 맞춰 데이터를 전송해주는 클래스가 있다고 해보자.

```java
public class ResponseSender{
    private Data data;
        
    public ResponseSender(Data data) {
    	this.data = data;
    }

    protected void sendHeader() {
        // 헤더 데이터 전송
    }   

    protected void sendBody() {
        // 텍스트로 데이터 전송
    }
}
``` 

데이터를 전송할 때 압축해서 전송해야 한다는 요구사항이 추가되었다.

```java
public class ZippedResponseSender extends ResponseSender{
    public ZippedResponseSender(Data data) {
        super(data);
    }
    
    @Override
    protected void sendBody() {
        // 데이터 압축 처리
    }
}
```

ZippedResponseSender 클래스는 기존 기능에 압축 기능을 추가해 주는데, 이 기능을 추가하기 위해
ResponseSender 클래스의 코드는 바뀌지 않았다. 즉, ResponseSender 클래스는 확장에는 열려 있으면서
변경에는 닫혀 있다. 

위와 같은 예제는 Gof의 템플릿 패턴을 사용한 것이다. 템플릿 메서드 패턴은 상위 클래스에서 실행할 기본 코드를
만들고 하위 클래스에서 필요에 따라 확장해 나가는 패턴이다.

### 개방 폐쇄 원칙이 깨질 때의 주요 증상

추상화와 다형성을 사용해 개방 폐쇄 원칙을 구현하기 때문에, 추상화와 다형성이 지켜지지 않은 코드는
개방 폐쇄 원칙을 어기게 된다. 

개방 폐쇄 원칙을 어기는 코드의 전형적인 특징은 다음과 같다.

- 다운 캐스팅을 한다
- instanceof와 같은 타입 확인 연산자가 사용
- 비슷한 if else 블록이 존재한다

Enemy 캐릭터의 움직이는 경로를 몇 가지 패턴으로 정한다고 하자.
이 때, 정해진 패턴에 따라 경로를 이동하는 코드를 다음과 같이 작성할 수 있다.

```java
public class Enemy extends Character {
    private int pathPattern;

    public Enemy(int pathPattern) {
    	this.pathPattern = pathPattern;
    }

    public void draw() {
        if(pathPattern == 1) {
        	x += 4;
        } else if(pathPattern == 2) {
            y += 4;
        } else if(pathPattern == 4) {
        	x += 4;
            y += 10;
        }
    }
}
```

Enemy 클래스에 새로운 경로 패턴을 추가해야 할 경우 Enemy 클래스의 draw 메서드는 새로운 if 블록이 추가된다.
즉, 경로를 추가하는데 Enemy 클래스가 닫혀 있지 않다. 이를 개방 폐쇄 원칙을 따르도록 변경하면 Enemy 클래스에
PathPattern이라는 타입을 추상화하여 사용하는 구조로 바꿀 수 있다.

따라서 코드는 아래와 같이 변경된다.

```java
public class Enemy extends Character {
    private PathPattern pathPattern;

    public Enemy(PathPattern pathPattern) {
    	this.pathPattern = pathPattern;
    }

    public void draw() {
        int x = pathPattern.nextX();
        int x = pathPattern.nextY();
    }
}
```

훨씬 코드가 가독성이 있고 깔끔해졌으며, 변경 부분도 PathPattern으로 확장이 가능하고 Enemy는 닫혀있다.

개방 폐쇄 원칙은 변화가 예상되는 것을 추상화해서 변경의 유연함을 얻도록 해준다. 이 말은 변화하는 부분을
추상화하지 못하면 개방 폐쇄 원칙을 지킬 수 없게 되어 시간이 흐를수록 기능 변경이나 확장을 어렵게 만든다.

### 리스코프 치환 원칙

아래와 같은 간단한 코드가 있고 상위 타입 SuperClass와 하위 타입 SubClass가 있다고 하자. 특정 메서드는
상위 타입은 SuperClass를 이용할 것이다.

```
public void someMethod(SuperClass sc) {
    sc.someMethod();
}
```

someMethod()는 상위 타입인 SuperClass 타입의 객체를 사용하고 있는데, 이 메서드에 다음과 같이 하위 타입의 객체를
전달해도 someMethod()가 정상적으로 동작해야 한다는 것이 리스코프 치환 원칙이다.

```
someMethod(new SubClass());
```

### 리스코프 치환 원칙을 지키지 않을 떄의 문제

리스코프 치환 원칙을 설명할 때 자주 사용되는 대표적인 예가 직사각형-정사각형 문제이다.
직사각형을 표현하기 위한 Rectangle 클래스는 다음과 같이 가로와 세로 두 개의 값을 구하거나 수정하는 기능을
제공할 것이다.

```java
public class Rectangle{
    private int width;
    private int height;
    
    // Getter, Setter
}
```

정사각형을 직사각형의 특수한 경우로 보고 정사각형을 표현하기 위한 Square 클래스가 Rectangle 클래스를 상속받도록
구현을 했다고 하자. 정사각형은 가로와 세로가 동일한 값을 가져야 하므로, Square 클래스는 Rectangle 클래스의 setWidth()
 메서드와 setHeight() 메서드를 재정의해서 가로와 세로의 값이 일치되도록 구현했다.
 
```java
public class Square extends Rectangle{
    @Override
    public void setWidth(int width){
        super.setWidht(width);
        super.setHeight(width);
    } 

    @Override
    public void setHeight(int height){
        super.setWidht(height);
        super.setHeight(height);
    }
}
```

이제 Rectangle 클래스를 사용하는 코드를 살펴보자. 이 코드는 높이와 폭을 비교해서 높이를 더 길게 만들어 주는 기능을
제공한다고 해보자.

```
public void increaseHeight(Rectangle rtc) {
    if(rec.getHeight() <= rec.getWidth()) {
        rec.setHeight(rec.getWidth() + 10);
    }
}
```

이제 위의 메서드를 사용할 때 달라아햐는 가로와 세로가 같을 경우 increaseHeight을 실행해도 높이와 폭이 변경되지 않는다.

이 문제를 해소하기 위해 instanceof로 Square인 경우는 실행하지 않도록 변경할 수 있다. 하지만, instanceof를 사용한다는 것이
리스코프 치환 원칙에 위배되고 개방 폐쇄 원칙을 위반하는 것이 된다.

직사각형-정사각형 문제는 개념적으로 상속 관계에 있는 것처럼 보이지만 실제 구현에서는 상속 관계가 아닐 수도 있다는 것을 보여주고
있다. 개념상 정사각형은 높이와 폭이 같은 직사각형이므로, Rectangle클래스를 상속받아 구현하는 것이 합리적으로 보일 수 있으나,
실제 프로그램에서는 이 둘을 상속 관계로 묶을 수 없다는 것이다. 이 문제를 해결하려면 별도의 타입으로 구현해 주는 것이 맞다.

기능 실행의 제약과 관련해서 흔히 발생하는 위반 사례는 아래와 같다.

- 명시된 명세에서 벗어난 값을 리턴한다.
- 명시된 명세에서 벗어난 익셉션을 발생한다.
- 명시된 명세에서 벗어난 기능을 수행한다.

### 의존 역전 원칙

고수준 모듈은 어떤 의미 있는 단일 기능을 제공하는 모듈이라고 정의할 수 있으며, 저수준 모듈은 고수준 모듈의 기능을 구현하기 위해
필요한 하위 기능의 실제 구현으로 정의할 수 있다. 

고수준 모듈은 상대적으로 큰 틀에서 프로그램을 다룬다면, 저수준 모듈은 각 개별 요소가 어떻게 구현될 지에 대해서 다룬다. 

### DI와 Service Locator

서비스 로케이터는 필요한 객체를 서비스 로케이터 객체를 만들어서 그 객체에서 제공하는 것이다.

생성자 주입 방식의 DI를 활용한 의존 객체 주입

```java
public class Main{
    public static void main(String[] args){
        // 상위 수준인 transcoder 패키지에서 사용할
        // 하위 수준의 모듈 객체 생성
        JobQueue jobQueue = new FileJobQueues();
        Transcoder transcoder = new FfmpegTranscoder();
        
        // 상위 수준 모듈 객체를 생성하고 실행
        final Worker worker = new Worker(jobQueue, transcoder);
    }
}
```

위와 같은 방식에서 나아가 조립기로 분리하여 DI를 할 수 있다.

```java
public class Assembler {
    public void createAndWire() {
        JobQueue jobQueue = new FileJobQueues();
        Transcoder transcoder = new FfmpegTranscoder();
        this.worker = new Worker(jobQueue, transcoder);
    }

    public Worker getWorker() {
        return this.worker;
    }
}
```

이제 Main 클래스는 Assembler에게 객체 생성과 조립 책임을 위임한 뒤에 생성한 Worker객체를 구하는 방식으로 변경된다.

```java
public class Main{
    public static void main(String[] args){
        Assembler assembler = new Assembler();
        assembler.createAndWire();
        final Worker worker = assembler.getWorker();
    }
}
```

스프링 프레임워크의 DI 프레임워크가 바로 객체를 생성하고 조립해주는 기능을 담당한다.