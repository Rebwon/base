## 객체지향 개발 5대 원리: SOLID

#### 1. 5가지 원리의 핵심 내용

#### A. SRP(단일책임의 원칙: Single Responsibility Principle)

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

#### B. OCP(개방폐쇄의 원칙: Open-Close Principle)

> You should be able to extend a classes behavior, without modifying it.

i. 정의

버틀란트 메이어(Bertrand Meyer)박사가 1998년 객체지향 소프트웨어 설계 라는 책에서 정의한 내용으로 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다는 원리입니다. 
이것은 변경을 위한 비용은 가능한 줄이고 확장을 위한 비용은 가능한 극대화 해야 한다는 의미로, 요구사항의 변경이나 추가사항이 발생하더라도, 기존 구성요소는 수정이 일어나지 말아야 하며, 기존 구성요소를 쉽게 확장해서 재사용할 수 있어야 한다는 뜻입니다. 
로버트 C. 마틴은 OCP는 관리가능하고 재사용 가능한 코드를 만드는 기반이며, OCP를 가능케 하는 중요 메커니즘은 추상화와 다형성이라고 설명하고 있습니다. OCP는 객체지향의 장점을 극대화하는 아주 중요한 원리라 할 수 있습니다.

ii. 적용방법
1. 변경(확장)될 것과 변하지 않을 것을 엄격히 구분합니다.
2. 이 두 모듈이 만나는 지점에 인터페이스를 정의합니다.
3. 구현에 의존하기보다 정의한 인터페이스에 의존하도록 코드를 작성합니다.

iii. 적용사례

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

SRP 원칙으로 분리한 클래스인데, 별 문제가 없어보입니다. 하지만 여기에서도 변경이 발생할 수 있습니다. 예를 들어 Guitar외에
 바이올린이나 첼로, 비올라와 같은 다른 악기들도 다루어야 한다면 어떻게 될까요? 그 해결책으로 매번 새로운 악기들과 요소를 만들어 간다면 어떻게 될까요?
우리는 항상 변화를 염두해 두고 있어야 합니다.

변화를 막을 수 있는 사람은 아무도 없습니다. 다만 변화에 적절히 대응할 뿐입니다. 위와 같이 변화에 몸을 맡겨버린다면 엄청난 재앙이 두고두고 여러 개발자들을 괴롭힐 것입니다. 
자 이제 지구를 구하는 심정으로 그럼 앞서 설명한OCP원리를 이용하여 위와 같은 변화에 대응해 보도록 합니다.

먼저, Guitar와 추가 될 다른 악기들을 추상화하는 작업이 필요합니다. 여기서는 추가될 악기들의공통 속성을 모두 담을 수 있는 StringInstrument라는 인터페이스를 생성하겠습니다. 
앞으로는 StringInstrument가 이들을 대표하게 될 것입니다.

```java
class Guitar extends StringInstrument{
    public Guitar(String serialNum, GuitarSpec spec) {
        this.serialNum = serialNum;
        this.spec = spec;
    }
    private GuitarSpec spec;
}
class GuitarSpec extends StringInstrumentSpec{
}

class Violin extends StringInstrument{
    public Violin(String serialNum, ViolinSpec spec) {
        this.serialNum = serialNum;
        this.spec = spec;
    }
    private ViolinSpec spec;
}
class ViolinSpec extends StringInstrumentSpec{
}
```

새로운 악기가 추가 되면서 변경이 발생하는 부분을 추상화 하여 분리하였음을 확인 할 수 있습니다. 
이렇게 해서 코드의 수정을 최소화하여 결합도는 줄이고 응집도는 높이는 효과를 볼 수 있습니다.

iv. 적용이슈

1. 확장되는 것과 변경되지 않는 모듈을 분리하는 과정에서 크기 조절에 실패하면 오히려 관계가 더 복잡해 질 수 있습니다. 
설계자의 좋은 자질 중 하나는 이런 크기 조절과 같은 갈등 상황을 잘 포착하여 (아깝지만) 비장한 결단을 내릴 줄 아는 능력에 있습니다.

2. 인터페이스는 가능하면 변경되어서는 안 됩니다. 따라서 인터페이스를 정의할 때 여러 경우의 수에 대한 고려와 예측이 필요합니다. 물론 과도한 예측은 불필요한 작업을 만들고, 
보통 이 불필요한 작업의 양은 상당히 크기 마련입니다. 따라서 설계자는 적절한 수준의 예측 능력이 필요한데, 설계자에게 필요한 또 하나의 자질은 예지력입니다.

3. 인터페이스 설계에서 적당한 추상화 레벨을 선택해야 합니다. 우리는 추상화라는 개념에 '구체적이지 않은' 정도의 의미로 약간 느슨한 개념을 갖고 있습니다. 그래디 부치(Grady Booch)에 의하면 
‘추상화란 다른 모든 종류의 객체로부터 식별될 수 있는 객체의 본질적인 특징’이라고 정의하고 있습니다. 즉, 이 '행위'에 대한 본질적인 정의를 통해 인터페이스를 식별해야 합니다.

#### C. LSP(리스코브 치환의 원칙: The Liskov Substitution Principle)

> Functions that use pointers or references to base classes must be able to use objects of derived
> classes without knowing it.

i. 정의

이 원칙은 5가지 원칙 중에서 좀처럼 쉽게 이해 되지 않는 원칙의 하나로 LSP라는 이름에서는 도저히 원칙에 대한 내용을 도출 할 수 없는 원칙입니다. LSP를 한마디로 한다면, “서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다.”라고 할 수 있습니다. 
즉, 서브 타입은 언제나 기반 타입과 호환될 수 있어야 합니다. 달리 말하면 서브 타입은 기반 타입이 약속한 규약(public 인터페이스, 물론 메소드가 던지는 예외까지 포함됩니다.)을 지켜야 합니다. 상속은 구현상속(extends 관계)이든 인터페이스 상속(implements 관계)이든 궁극적으로는 다형성을 통한 확장성 획득을 목표로 합니다. 
LSP원리도 역시 서브 클래스가 확장에 대한 인터페이스를 준수해야 함을 의미합니다. 다형성과 확장성을 극대화 하려면 하위 클래스를 사용하는 것보다는 상위의 클래스(인터페이스)를 사용하는 것이 더 좋습니다. 일반적으로 선언은 기반 클래스로 생성은 구체 클래스로 대입하는 방법을 사용합니다. 
생성 시점에서 구체 클래스를 노출시키기 꺼려질 경우 생성 부분을 Abstract Factory 등의 패턴을 사용하여 유연성을 높일 수 있습니다. 상속을 통한 재사용은 기반 클래스와 서브 클래스 사이에 IS-A관계가 있을 경우로만 제한 되어야 합니다. 그 외의 경우에는 합성(composition)을 이용한 재사용을 해야 합니다. 
상속은 다형성과 따로 생각할 수 없습니다. 그리고 다형성으로 인한 확장 효과를 얻기 위해서는 서브 클래스가 기반 클래스와 클라이언트 간의 규약(인터페이스)를 어겨서는 안 됩니다. 결국 이 구조는 다형성을 통한 확장의 원리인 OCP를 제공 하게 됩니다. 따라서 LSP는 OCP를 구성하는 구조가 됩니다. 
객체지향 설계 원리는 이렇게 서로가 서로를 이용하기도 하고 포함하기도 하는 특징이 있습니다. LSP는 규약을 준수하는 상속구조를 제공 합니다. LSP를 바탕으로 OCP는 확장하는 부분에 다형성을 제공해 변화에 열려있는 프로그램을 만들 수 있도록 합니다.

ii. 적용방법

1. 만약 두 개체가 똑 같은 일을 한다면 둘을 하나의 클래스로 표현하고 이들을 구분할 수 있는 필드를 둡니다.

2. 똑같은 연산을 제공하지만, 이들을 약간씩 다르게 한다면 공통의 인터페이스를 만들고 둘이 이를 구현 합니다. (인터페이스 상속)

3. 공통된 연산이 없다면 완전 별개인 2개의 클래스를 만듭니다.

4. 만약 두 개체가 하는 일에 추가적으로 무언가를 더 한다면 구현 상속을 사용합니다.


iii. 적용사례

컬렉션 프레임워크

```java
void f(){  
    LinkedList list = new LinkedList();
    // …
    modify(list);
}

void modify(LinkedList list){  
    list.add(…);
    doSomethingWith(list);
}
```
위와 같이 List 자료구조만 사용할 것이라면 코드에 문제는 없어보입니다. 하지만 속도 개선을 위해 HashSet을 사용해야 하는 경우가 발생한다면
LinkedList를 다시 HashSet으로 어떻게 바꿀 수 있을까요? LinkedList와 HashSet은 모두 Collection 인터페이스를 상속하고 있으므로 다음과 같이
작성하는 것이 바람직합니다.

```java
void f(){  
     Collection collection = new HashSet();
     //…
     modify(list);
}

void modify(Collection collection){  
     collection.add(…);
     doSomethingWith(collection);
}
```

이제 컬렉션 생성 부분만 고치면 마음대로 어떤 컬렉션 구현 클래스든 사용할 수 있습니다. 이 프로그램에서 LSP와 OCP 모두를 찾아볼 수 있는데 우선 컬렉션 프레임워크가 LSP를 준수하지 않았다면 Collection 인터페이스를 통해 수행하는 범용 작업이 제대로 수행될 수 없습니다. 하지만 모두 LSP를 준수하기 때문에 이들을 제외한 모든 Collection 연산에서는 앞의 modify() 메소드가 잘 동작하게 됩니다. 
그리고 이를 통해 modify()는 변화에 닫혀 있으면서, 컬렉션의 변경과 확장에는 열려 있는 구조(OCP)가 됩니다. 물론 Collection이 지원하지 않는 연산을 사용한다면 한 단계 계층 구조를 내려가야 합니다. 그렇다 하더라도 ArrayList, LinkedList, Vector 대신 이들이 구현하고 있는 List를 사용하는 것이 현명한 방법입니다.


iv. 적용이슈

1. 혼동될 여지가 없고 트레이드 오프를 고려해 선택한 것이라면 그대로 둡니다.

2. 다형성을 위한 상속 관계가 필요 없다면 Replace with Delegation을 합니다. 상속은 깨지기 쉬운 기반 클래스 등을 지니고 있으므로 IS-A관계가 성립되지 않습니다. LSP를 지키기 어렵다면 상속대신 합성(composition)을 사용하는 것이 좋습니다.

3. 상속 구조가 필요 하다면 Extract Subclass, Push Down Field, Push Down Method 등의 리팩토링 기법을 이용하여 LSP를 준수하는 상속 계층 구조를 구성 합니다.

4. IS-A관계가 성립한다고 프로그램에서 까지 그런것은 아닙니다. 이들간의 관계 맺음은 이들의 역할과 이들 사이에 공유하는 연산이 있는지, 그리고 이들 연산이 어떻게 다른지 등을 종합적으로 검토해 봐야 합니다.

5. Design by Contract(“서브 클래스에서는 기반 클래스의 사전 조건과 같거나 더 약한 수준에서 사전 조건을 대체할 수 있고, 기반 클래스의 사후 조건과 같거나 더 강한 수준에서 사후 조건을 대체할 수 있다.”)적용: 기반 클래스를 서브 클래스로 치환 가능하게 하려면 받아들이는 선 조건에서 서브 클래스의 제약사항이 기반 클래스의 제약 사항보다 느슨하거나 같아야 합니다. 
만약 제약조건이 더 강하다면 기반 클래스에서 실행되던 것이 서브 클래스의 강 조건으로 인해 실행되지 않을 수도 있기 때문입니다. 반면 서브 클래스의 후 조건은 같거나 더 강해야 하는데, 약하다면 기반 클래스의 후 조건이 통과시키지 않는 상태를 통과시킬 수도 있기 때문입니다.

#### D. ISP(인터페이스 분리의 원칙: Interface Segregation Principle)

> Clients should not be forced to depend upon interfaces that they do not use.

i. 정의

ISP원리는 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다는 원리입니다. 즉 어떤 클래스가 다른 클래스에 종속될 때에는 가능한 최소한의 인터페이스만을 사용해야 합니다. ISP를 ‘하나의 일반적인 인터페이스보다는, 여러 개의 구체적인 인터페이스가 낫다’라고 정의할 수 도 있습니다. 만약 어떤 클래스를 이용하는 클라이언트가 여러 개고 이들이 해당 클래스의 특정 부분집합만을 이용한다면, 
이들을 따로 인터페이스로 빼내어 클라이언트가 기대하는 메시지만을 전달할 수 있도록 합니다. 
SRP가 클래스의 단일책임을 강조한다면 ISP는 인터페이스의 단일책임을 강조합니다. 하지만 ISP는 어떤 클래스 혹은 인터페이스가 여러 책임 혹은 역할을 갖는 것을 인정합니다. 이러한 경우 ISP가 사용되는데 SRP가 클래스 분리를 통해 변화에의 적응성을 획득하는 반면, ISP에서는 인터페이스 분리를 통해 같은 목표에 도달 합니다.

ii. 적용방법

1. 클래스 인터페이스를 통한 분리
    
    - 클래스의 상속을 이용하여 인터페이스를 나눌 수 있습니다.
      이와 같은 구조는 클라이언트에게 변화를 주지 않을 뿐 아니라 인터페이스를 분리하는 효과를 갖습니다. 하지만 거의 모든 객체지향 언어에서는 상속을 이용한 확장은 상속받는 클래스의 성격을 디자인 시점에 규정해 버립니다. 
      따라서 인터페이스를 상속받는 순간 인터페이스에 예속되어 제공하는 서비스의 성격이 제한 됩니다.
      
2. 객체 인터페이스를 통한 분리

    - 위임(Delegation)을 이용하여 인터페이스를 나눌 수 있습니다.
      위임이란, 특정 일의 책임을 다른 클래스나 메소드에 맡기는 것입니다. 만약 다른 클래스의 기능을 사용해야 하지만 그 기능을 변경하고 싶지 않다면, 상속 대신 위임을 사용 합니다.
      
      
iii. 적용사례

Java Swing의 JTable.

JTable 클래스에는 굉장히 많은 메소드들이 있습니다. 컬럼을 추가하고 셀 에디터 리스너를 부착하는 등 여러 역할이 하나의 클래스 안에 혼재되어 있지만 JTable의 입장에서 본다면 모두 제공해야 하는 역할입니다. 
JTable은 ISP가 제안하는 방식으로 모든 인터페이스 분리를 통해 특정 역할만을 이용할 수 있도록 해줍니다. 즉, Accessible, CellEditorListener, ListSelectionListener, Scrollable, TableColumnModelListener, TableMoldelListener 등 여러 인터페이스 구현을 통해 서비스를 제공합니다. 
JTable은 자신을 이용하여 테이블을 만드는 객체, 
즉 모든 서비스를 필요로 하는 객체에게는 기능 전부를 노출하지만, 이벤트 처리와 관련해서는 여러 리스너 인터페이스를 통해 해당 기능만 노출합니다.

```java
import javax.swing.event.*;  
import javax.swing.table.TableModel;

public class SimpleTableDemo ... implements TableModelListener {  
    ...
    public SimpleTableDemo() {
        ...
        table.getModel().addTableModelListener(this);
        ...
    }
    //인터페이스를 통해 노출할 기능을 구현합니다.
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

        ...// Do something with the data...
    }
    ...
}
```

iv. 적용이슈

1. 가 구현된 클라이언트에 변경을 주지 말아야 합니다.
2. 두 개 이상의 인터페이스가 공유하는 부분의 재사용을 극대화 합니다.
3. 서로 다른 성격의 인터페이스를 명백히 분리 합니다.

#### E. DIP(의존성 역전의 원칙: Dependency Inversion Principle)

> A. High level modules should not depend upon low level modules.
> Both should depend upon abstractions.
>
> B. Abstracions should not depend upon details. Details should depend upon abstractions.

i. 정의

의존 관계의 역전 Dependency Inversion 이란 구조적 디자인에서 발생하던 하위 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의역전입니다. 
실제 사용 관계는 바뀌지 않으며, 추상을 매개로 메시지를 주고 받음으로써 관계를 최대한 느슨하게 만드는 원칙입니다.

DIP의 키워드는 ‘IOC’, ‘훅 메소드(슈퍼클래스에서 디폴트 기능을 정의해두거나 비워뒀다가 서브클래스에서 선택적으로 오버라이드할 수 있도록 만들어둔 메소드를 훅(hook) 메소드라고 합니다. 
서브클래스에서는 추상 메소드를 구현하거나, 훅 메소드를 오버라이드하는 방법을 이용해 기능의 일부를 확장합니다.)’’, 
‘확장성’입니다. 이 세 가지 요소가 조합되어 복잡한 컴포넌트들의 관계를 단순화하고 컴포넌트 간의 커뮤니케이션을 효율적이게 합니다.

출처 : nextree