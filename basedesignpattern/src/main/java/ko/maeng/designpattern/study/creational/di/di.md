## Dependency Injection

Real-World-Example
- 늙은 마법사는 가끔식 파이프를 채우고 담배를 피우는 것을 좋아합니다. 그러나 그는 하나의 담배 브랜드에만 의존하는 것이 아닌,
여러가지를 바꿔서 즐길 수 있는 것을 좋아합니다.

In plain words
- 의존성 주입은 클라이언트의 의존성 생성과 자체 동작을 분리합니다.

먼저 담배 브랜드입니다.
```java
public abstract class Tobacco {

  private static final Logger LOGGER = LoggerFactory.getLogger(Tobacco.class);

  public void smoke(Wizard wizard) {
    LOGGER.info("{} smoking {}", wizard.getClass().getSimpleName(),
        this.getClass().getSimpleName());
  }
}

public class SecondBreakfastTobacco extends Tobacco {
}

public class RivendellTobacco extends Tobacco {
}

public class OldTobyTobacco extends Tobacco {
}
```

다음은 마법사 클래스입니다.
```java
public interface Wizard {
  void smoke();
}

public class AdvancedWizard implements Wizard {
  private final Tobacco tobacco;

  public AdvancedWizard(Tobacco tobacco) {
    this.tobacco = tobacco;
  }

  @Override
  public void smoke() {
    tobacco.smoke(this);
  }
}
```

사용 예)
```java
  var advancedWizard = new AdvancedWizard(new SecondBreakfastTobacco());
    advancedWizard.smoke();
```

실제 사용 가능 예)
- 객체에서 구체적인 구현 세부사항을 제거해야 할 경우.
- 모의 객체 또는 스텁을 사용하여 격리된 클래스의 단위 테스트를 작성해야할 경우.