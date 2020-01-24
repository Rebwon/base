## 객체지향 개발 5대 원리: SOLID

####1. 5가지 원리의 핵심 내용

####A. SRP(단일책임의 원칙: Single Responsibility Principle)

> There should never be more than one reason for a class to change.

i. 정의 

작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축: axis of change)을 수행하는데 집중되어 있어야 한다는 원칙입니다.
이는 어떤 변화에 의해 클래스를 변경해야 하는 이유는 오직 하나 뿐이어야 함을 의미합니다. SRP원리를 적용하면 무엇보다도 책임 영역이 확실해지기 때문에 한 책임의 변경에서 다른 
책임의 변경으로의 연쇄작용에서 자유로울 수 있습니다. 뿐만 아니라 책임을 적절히 분배함으로써 코드의 가독성 향상, 유지보수 용이라는 이점까지 누릴 수 있으며
객체지향 원리의 대전제 격인 OCP원리뿐 아니라 다른 원리들을 적용하는 기초가 됩니다. 이 원리는 다른 원리들에 비해서 개념이 비교적 단순하지만, 이 원리를
적용해서 직접 클래스를 설계하기가 그리 쉽지만은 않습니다. 왜냐하면 실무의 프로세스는 매우 복잡 다양하고 변경 또한 빈번하기 때문에 경험이 많지 않거나 도메인에 대한 업무 이해가 부족하면
나도 모르게 SRP원리에서 멀어져 버리게 됩니다. 따라서 평소에 많은 연습('책임'이란 단어를 상기하는)과 경험이 필요한 원칙입니다.

ii. 적용방법

리팩토링(Refactoring: Improving the Design of Existing Code -Martin Fowler)에서 소개하는 대부분의 위험상황에 대한 해결방법은 직/간접적으로 SRP원리와 관련이 있으며, 
이는 항상 코드를 최상으로 유지한다는 리팩토링의 근본정신도 항상 객체들의 책임을 최상의 상태로 분배한다는 것에서 비롯되기 때문입니다.

- 여러 원인에 의한 변경(Divergent change) : Extract class를 통해 혼재된 각 책임을 각각의 개별 클래스로 분할하여 클래스 당 하나의 책임만을 맡도록 하는 것입니다. 여기서 관건은 책임만 분리하는 것이 아니라 분리된 두 클래스간의 관계의 복잡도를 줄이도록 설계하는 것입니다. 
만약 Extract Class된 각각의 클래스들이 유사하고 비슷한 책임을 중복해서 갖고 있다면 Extract Superclass를 사용할 수 있습니다. 
이것은 Extract된 각각의 클래스들의 공유되는 요소를 부모 클래스로 정의하여 부모 클래스에 위임하는 기법입니다. 따라서 각각의 Extract Class들의 유사한 책임들은 부모에게 명백히 위임하고 다른 책임들은 각자에게 정의할 수 있습니다.

- 산탄총 수술(Shotgun surgery) : Move Field와 Move Method를 통해 책임을 기존의 어떤 클래스로 모으거나, 이럴만한 클래스가 없다면 새로운 클래스를 만들어 해결할 수 있습니다. 즉 산발적으로 여러 곳에 분포된 책임들을 한 곳에 모으면서 설계를 깨끗하게 합니다. 즉 응집성을 높이는 작업입니다.

iii. 적용사례

```java
class Guitar {
    public Guitar(String serialNum, double price, Maker maker,
            Type type, String model, Wood backWood, Wood topWood, int stringNum) {
        this.serialNum = serialNum;
        this.price = price;
        this.maker = maker;
        this.type = type;
        this.model = model;
        this.backWood = backWood;
        this.topWood = topWood;
        this.stringNum = stringNum;
    }

    private String serialNum;
    private double price;
    private Maker maker;
    private Type type;
    private String model;
    private Wood backWood;
    private Wood topWood;
    private String stringNum;    
}
```

위와 같이 serialNum은 고유 정보이기 때문에 변화가 발생하지 않고, 나머지 모든 정보는
언제든지 변화가 발생할 수 있습니다. 따라서 특정 정보군에 변화가 발생하면 항상 해당 클래스를 수정해야 하는 부담이
발생하게 됨으로 이 부분이 SRP원칙의 적용 대상이 됩니다.

```java
class Guitar{
    public Guitar(String serialNum, GuitarSpec spec) {
        this.serialNum = serialNum;
        this.spec = spec;
    }
    
    private String serialNum;
    private GuitarSpec spec;
    
    ...
}

class GuitarSpec{
    double price;
    Maker maker;
    Type type;
    String model;

    ...
}
```

변화가 예상되는 특성 정보군을 분리한 것을 확인하실 수 있습니다. 따라서 특성 정보에 변경이 일어나면 GuitarSpec 클래스만
변경하면 됩니다. 훨씬 보기에도 좋아졌고 무엇보다 변화에 의해 변경되는 부분을 한 곳에서 관리할 수 있게 되었습니다.

iv. 적용이슈

클래스는 자신의 이름이 나타내는 일을 해야 합니다. 올바른 클래스 이름은 해당 클래스의 책임을 나타낼 수 있는 가장 좋은 방법입니다. 각 클래스는 하나의 개념을 나타내어야 합니다. 사용되지 않는 속성이 결정적 증거입니다. 
무조건 책임을 분리한다고 SRP가 적용되는 건 아닙니다. 
각 개체 간의 응집력이 있다면 병합이 순 작용의 수단이 되고 결합력이 있다면 분리가 순 작용의 수단이 됩니다.

#### OCP(개방폐쇄의 원칙: Open-Close Principle)

> You should be able to extend a classes behavior, without modifying it.

i. 정의

버틀란트 메이어(Bertrand Meyer)박사가 1998년 객체지향 소프트웨어 설계 라는 책에서 정의한 내용으로 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다는 원리입니다. 
이것은 변경을 위한 비용은 가능한 줄이고 확장을 위한 비용은 가능한 극대화 해야 한다는 의미로, 요구사항의 변경이나 추가사항이 발생하더라도, 기존 구성요소는 수정이 일어나지 말아야 하며, 기존 구성요소를 쉽게 확장해서 재사용할 수 있어야 한다는 뜻입니다. 
로버트 C. 마틴은 OCP는 관리가능하고 재사용 가능한 코드를 만드는 기반이며, OCP를 가능케 하는 중요 메커니즘은 추상화와 다형성이라고 설명하고 있습니다. OCP는 객체지향의 장점을 극대화하는 아주 중요한 원리라 할 수 있습니다.

