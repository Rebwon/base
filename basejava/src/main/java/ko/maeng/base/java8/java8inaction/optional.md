## null 대신 Optional

null 때문에 발생하는 문제

1. 에러의 근원이다
   - 자바에서 가장 흔히 발생하는 에러이다.
2. 코드를 어지럽힌다
   - 때로는 충첩된 null 확인 코드를 추가해야 하므로 코드 가독성이 떨어진다.
3. 아무 의미가 없다
   - null은 아무 의미도 표현하지 않는다. 특히 정적 형식 언어에서 값이 없음을 표현하는 방법으로는 적절치 않다.
4. 자바 철학에 위배된다
   - 자바는 개발자로부터 모든 포인터를 숨겼다. 하지만 예외가 있는데 그것이 바로 null 포인터이다.
5. 형식 시스템에 구멍을 만든다
   - null은 무형식이며 정보를 포함하고 있지 않으므로, 모든 참조 형식에 null을 할당할 수 있다. 이런식으로
   null이 할당되기 시작하면서 시스템의 다른 부분으로 null이 퍼졌을 때 애초에 null이 어떤 의미로 사용되었는지 알 수 없다.

### Optional 만들기

빈 Optional
```java
Optional<String> opt = Optional.empty();
```

null이 아닌 값으로 Optional만들기
- null이 아닌 값으로 만들었기 때문에 of에 주어진 변수가 null이라면, NPE를 발생시킨다.
```java
Optional<Car> optCar = Optional.of(car);
```

null값으로 Optional만들기
```java
Optional<Car> optCar = Optional.ofNullable(car);
```

아래와 같은 메서드가 있다고 하자. person의 값을 넣을때 어떻게 넣어야 null-safety한 것일까?
```java
public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
}
```

답은 ofNullable을 활용하여 빈 Optional객체를 반환하도록 해야 한다.


### 도메인 모델에 Optional을 사용했을 때 데이터를 직렬화 할 수 없는 이유

바로 위의 메서드를 통해 알 수 있듯이 Optional을 활용하면 도메인 모델에서 값의 여부를 구체적으로 표현할 수 있었다.

하지만 Optional의 설계자와 자바 언어 아키텍트는 Optional의 용도가 선택형 반환값을 지원하는 것이라고 명확하게 못박았다.

Optional 클래스는 필드 형식으로 사용할 것을 가정하지 않았으므로, Serializable 인터페이스를 구현하지 않는다. 따라서 우리의 도메인 모델에
 Optional을 사용한다면 직렬화 모델을 사용하는 도구나, 프레임워크에서 문제가 생길 수 있다.

이와 같은 단점에도 불구하고 여전히 Optional을 사용해서 도메인 모델을 구성하는 것이 바람직하다고 생각한다.

객체 그래프에서 일부 또는 전체 객체가 null일 수 있는 상황이라면 더욱 그렇다. 직렬화 모델이 필요하다면, Optional로 값을 반환받을 수 있는
메서드를 추가하는 방식을 권장한다.

Optional 클래스는 인스턴스에서 값을 얻을 수 있는 다양한 인스턴스 메서드를 제공한다.

- get()은 값을 읽는 가장 간단한 메서드이면서, 동시에 가장 안전하지 않은 메서드이다. 메서드 get은 래핑된 값이 있다면, 반환하고
없으면 NoSuchElementException을 발생시킨다. 따라서 반드시 값이 있다고 가정하고 쓰지 않는다면 쓰지 않는 것이 바람직하다.

- orElse(T other)는 값이 없을 경우 디폴트 값을 제공할 수 있는 메서드이다.

- orElseGet(Supplier<? extends T> other)는 orElse 메서드에 대응하는 게으른 버전이다. Optional에 값이 없을 때만 Supplier가 실행되기 때문이다.
디폴트 메서드를 만드는데 시간이 걸리거나(효율성 때문에) Optional이 비어있을 때만 디폴트값을 생성하고 싶다면(디폴트가 무조건 필요한 상황) 사용해야 한다.

- orElseThrow(Supplier<? extends X> exceptionSupplier)는 Optional이 비어있을 때 예외를 발생시킨다는 점에서 get과 유사하다. 하지만 이 메서드는 발생시킬 예외를
선택할 수 있다.

- ifPresent(Consumer<? super T> consumer)를 이용하면 값이 존재할 때 인수로 넘겨준 동작을 실행할 수 있다. 값이 없으면 아무일도 일어나지 않는다.


Optional 클래스의 메서드

|메서드|설명|
|------|---|
|empty|빈 Optional 인스턴스 반환.|
|filter|값이 존재하며 프레디케이트와 일치하면 값을 포함하는 Optional을 반환하고, 값이 없거나 프레디케이트와 일치하지 않으면 빈 Optional을 반환함.|
|flatMap|값이 존재하면 인수로 제공된 함수를 적용한 결과 Optional을 반환하고, 값이 없으면 빈 Optional을 반환함.|
|get|값이 존재하면 Optional이 감싸고 있는 값을 반환하고, 값이 없으면 NoSuchElementException이 발생함.|
|ifPresent|값이 존재하면 지정된 Consumer를 실행하고, 값이 없으면 아무 일도 일어나지 않음.|
|isPresent|값이 존재하면 true를 반환하고, 값이 없으면 false를 반환함.|
|map|값이 존재하면 제공된 매핑 함수를 적용함.|
|of|값이 존재하면 값을 감싸는 Optional을 반환하고, 값이 null이면 NullPointerException을 발생함.|
|ofNullable|값이 존재하면 값을 감싸는 Optional을 반환하고, 값이 null이면 빈 Optional을 반환함.|
|orElse|값이 존재하면 값을 감싸는 Optional을 반환하고, 값이 없으면 디폴트 값을 반환함.|
|orElseGet|값이 존재하면 값을 감싸는 Optional을 반환하고, 값이 없으면 Supplier에서 제공하는 값을 반환함.|
|orElseThrow|값이 존재하면 값을 감싸는 Optional을 반환하고, 값이 없으면 Supplier에서 생성한 예외를 발생함.|
