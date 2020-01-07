#### 인터페이스의 이해

먼저 인터페이스를 이해하기 위해서는 다음의 두 가지 사항을 반드시 염두에 두고 있어야 한다.

1. 클래스를 사용하는 쪽(User)과 클래스를 제공하는 쪽(Provider)이 있다.
2. 메서드를 사용(호출)하는 쪽(User)에서는 사용하려는 메서드(Provider)의 선언부만 알면 된다. (내용은 몰라도 된다.)

```java
class A{
    public void method(B b){
        b.methodB();
    }       
}

class B{
    public void methodB(){
        System.out.println("methodB()");
    }
}
```
클래스 A(User)는 클래스 B(Provider)의 인스턴스를 생성하고 메서드를 호출한다. 이 두 클래스는 서로 직접적인 관계에 있다.
이것을 간단히 A->B(A가 B를 참조한다)라고 표현한다.

이와 같이 직접적인 관계의 두 클래스는 한쪽(Provider)이 변경되면 다른 한 쪽(User)도 변경되어야 한다는 단점이 있다.

그러나, 클래스 A가 클래스 B를 직접 호출하지 않고 인터페이스를 매개체로 해서 클래스 A가 인터페이스를 통해 클래스 B의 메서드에 접근하도록 하면,
클래스 B에 변경사항이 생기거나 클래스 B와 같은 기능의 다른 클래스로 대체 되어도 클래스 A는 전혀 영향을 받지 않도록 하는 것이 가능하다.

```java
interface I{
    public abstract void methodB();
}

class B implements I {
    public void methodB(){
        System.out.println("methodB in class");    
    }
}

class A{
    public void methodA(I i){
        i.methodB();
    }
}
```
단, methodA가 호출될 때 I를 구현한 클래스의 인스턴스를 제공받아야 한다.

#### 추상(abstract) 클래스

클래스를 설계도에 비유한다면 추상 클래스는 미완성 설계도에 비유할 수 있다.
미완성 설계도란, 단어의 뜻 그대로 완성되지 못한 채 남겨진 설계도를 의미한다.

클래스가 미완성이라는 것은 멤버의 개수에 관계된 것이 아니라, 단지 미완성 메서드(추상 메서드)를 포함하고 있다는 의미이다.

미완성 설계도로 완성된 제품을 만들 수 없듯이 추상 클래스로는 인스턴스를 생성할 수 없다.

**추상 클래스는 상속을 통해서 자식 클래스에서 구현하여 완성이 가능하다.**

추상 클래스 자체로는 클래스로서의 역할을 다 못하지만, 새로운 클래스를 작성하는데 있어서 바탕이 되는 부모 클래스로써 중요한 의미를 갖는다.

새로운 클래스를 작성할 때 아무 것도 없는 상태에서 시작하는 것보다는 완전하지 못하더라도 어느 정도 틀을 갖는 상태에서 시작하는 것이 나을 것이다.

```java
public abstract class Price {
    abstract int getPriceCode();
    abstract double getCharge(int daysRented);
    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}

class ChildrensPrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    double getCharge(int daysRented) {
        double result = 1.5;
        if(daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}

class NewReleasePrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}

class RegularPrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.REGUlAR;
    }

    @Override
    double getCharge(int daysRented) {
        double result = 2;
        if(daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
}
```
리팩토링 1판 샘플 예제의 Price 클래스를 추상 클래스로 정의한 부분이다.

Price를 추상 클래스로 정의한 후 Children, NewRelease, Regular로 나눠서 각각 기능에 맞게 구현했다.
위와 같은 패턴을 디자인 패턴에서는 상태 패턴이라고 하기도 한다.

getFrequentRenterPoints를 주목하자. 추상 클래스의 활용도가 나타나는 부분이다.

대여 일수에 따라 적립 포인트를 추가하는 메서드인데, 
기본 적립포인트는 1포인트이며, 최신물을 대여했을 경우 대여 일수에 따라서 2포인트까지 줄 수 있게 재정의 한 것이다.

인터페이스와 추상클래스의 차이를 의미적으로 나타낸다면, 인터페이스는 기본적인 설계도이고 추상 클래스는 미완성된 설계도라고 할 수 있다.

|인터페이스|추상클래스|
|------|---|
|추상메서드, 상수|추상메서드, 일반메서드, 멤버변수,상수
