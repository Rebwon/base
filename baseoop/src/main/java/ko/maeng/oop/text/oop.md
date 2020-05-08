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