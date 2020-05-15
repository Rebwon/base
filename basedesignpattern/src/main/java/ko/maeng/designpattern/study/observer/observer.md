## 옵저버 패턴

정리 : 최범균님의 객체지향 책

웹 사이트의 상태를 확인해서 응답 속도가 느리거나 연결이 안 되면 모니터링 담당자에게
이메일로 통지해 주는 시스템을 만들기 위해, 상태를 확인하는 StatusChecker 클래스를 다음과 같이 구현할 수 있다.

```java
public class StatusChecker{
    private EmailSender emailSender;
    
    public void check() {
        Status status = loadStatus();
    
        if(status.isNotNormal()) {
            emailSender.sendEmail(status);
        }
    }
}
```

이메일뿐만 아니라 긴급한 메시지는 SMS로 바로 알려주는 기능을 추가해 달라는 요구가 들어왔다.

코드는 아래와 같이 바뀐다.

```java
public class StatusChecker{
    private EmailSender emailSender;
    private SmsSender smsSender;
    
    public void check() {
        Status status = loadStatus();
    
        if(status.isNotNormal()) {
            emailSender.sendEmail(status);
            smsSender.sendSms(status);
        }
    }
}
```

만약 회사 내부에서 사용하는 메신저로도 메시지를 보내 달라는 요구가 들어온다면?
시스템의 문제 상황을 알려주는 방식이 추가될 때마다, StatusChecker 클래스도 변경된다.

이렇게 한 객체의 상태 변화를 정해지지 않은 여러 다른 객체에 통지하고 싶을 때 사용되는 패턴이 옵저버 패턴이다.

옵저버 패턴에는 크게 주제(Subject) 객체와 옵저버(Observer) 객체가 등장하는데, 주제 객체는 다음의 두 가지 책임을 갖는다.
- 옵저버 목록을 관리하고, 옵저버를 등록하고 제거할 수 있는 메서드를 제공한다.
- 상태의 변경이 발생하면 등록된 옵저버에 변경 내역을 알린다.

옵저버 패턴에서 주제에 해당하는 클래스 구현

```java
import java.util.ArrayList;

public abstract class StatusSubject{
    private List<StatusObserver> observerList = new ArrayList<>();

    public void add(StatusObserver observer) {
        observerList.add(observer);
    }
    
    public void remove(StatusObserver observer) {
        observerList.remove(observer);
    }
    
    public void notifyStatus(Status status) {
        for(StatusObserver observer : observerList)
            observer.onAbnormalStatus(status);
    }
}
```

notifyStatus() 메서드는 observer List에 등록된 각 StatusObserver 객체의 onAbnormalStatus() 메서드를 호출하는데,
이렇게 옵저버 객체의 메서드를 호출하는 방식으로 상태에 변화가 생겼음을 옵저버 객체에게 알린다.

Status의 상태 변경을 알려야 하는 StatusChecker 클래스는 다음과 같이 StatusSubject 클래스를 상속받아 구현한다.

```java
public class StatusChecker extends StatusSubject {
    public void check() {
        Status status = loadStatus();
    
        if(status.isNormal())
            super.notifyStatus(status);
    }
}
```

StatusChecker 클래스는 비정상 상태가 감지되면 상위 클래스의 notifyStatus()를 호출해서
등록된 옵저버 객체들에 상태 값을 전달한다.

### 옵저버 패턴 구현의 고려 사항

- 주제 객체의 통지 기능 실행 주체
- 옵저버 인터페이스의 분리
- 통지 시점에서의 주제 객체 상태
- 옵저버 객체의 실행 제약 조건

