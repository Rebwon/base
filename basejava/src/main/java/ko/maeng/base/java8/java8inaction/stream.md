## 스트림API(Stream API)

자바 8의 스트림 API의 특징을 다음처럼 요약할 수 있다.
- 선언형 : 더 간결하고 가독성이 좋아진다.
- 조립할 수 있음 : 유연성이 좋아진다.
- 병렬화 : 성능이 좋아진다.

**filter()** : 람다를 인수로 받아 스트림에서 특정 요소를 제외시킨다. 예를 들어, d -> d.getCalories() > 300이라는 람다를 전달해서 300칼로리 이상의 요리를 선택한다.

**map()** : 람다를 이용해서 한 요소를 다른 요소로 변환하거나 정보를 추출한다. 예를 들어, Dish::getName으로 각각의 요리명만 추출한다.

**limit()** : 정해진 개수 이상의 요소가 스트림에 저장되지 못하게 스트림 크기를 축소(truncate)한다.

**collect()** : 스트림을 다른 형식으로 변환한다. 예를 들면 toList(), toSet() 등등.

### 스트림과 컬렉션

데이터를 언제 계산하느냐가 컬렉션과 스트림의 가장 큰 차이라고 할 수 있다. 

컬렉션은 현재 자료구조가 포함하는 모든 값을 메모리에 저장하는 자료구조다. 
반면 스트림은 이론적으로 요청할 때만 요소를 계산하는 고정된 자료구조이다. 스트림은 생산자(producer)와 소비자(consumer) 관계를 형성한다. 

또한 스트림은 게으르게 만들어지는 컬렉션과 같다. 즉, 사용자가 데이터를 요청할 때만 값을 계산한다.(경영학에서는 이를 요청 중심 제조 demand-driven-manufacturing 또는 즉석 제조 just-in-time-manufacturing라고 부른다.)

스트림은 단 한번만 소비할 수 있다. 이 말의 의미는 반복자(for)와 마찬가지로 한 번 탐색한 요소를 재 탐색하려면 초기 데이터 소스에서 새로운 스트림을 만들어야 한다.

예)
```java
List<String> title = Arrays.asList("Java8", "stream", "start!");
Stream<String> s = title.stream();
s.forEach(System.out::println); //title의 각 단어 출력
s.forEach(System.out::println); //java.lang.IllegalStateException : 스트림이 이미 소비되었거나 닫힘.
```

**외부 반복과 내부 반복**

컬렉션 인터페이스를 사용하려면 사용자가 직접 요소를 반복해야 한다. (예를 들면 for-each등을 사용해서). 이를 **외부 반복(external iteration)** 이라고 한다. 
반면 스트림 라이브러리는 (반복을 알아서 처리하고 결과 스트림 값을 어딘가에 저장해주는) **내부 반복(internal iteration)** 을 사용한다. 

컬렉션: for-each 루프를 사용하는 외부 반복
```java
List<String> names = new ArrayList<>();
for(Dish d : menu){
   names.add(d.getName());
}
```

컬렉션: 내부적으로 숨겨졌던 반복자를 사용한 외부 반복
```java
List<String> names = new ArrayList<>();
Iterator<Dish> iterator = menu.iterator();
while(iterator.hasNext()){  //명시적 반복
   Dish d = iterator.next();
   names.add(d.getName());
}
```

스트림: 내부 반복
```java
List<String> names = menu.stream()
                       .map(Dish::getName)
                       .collect(toList());
```

### 스트림 연산

```java
List<String> threeHighCaloricDishNames = menu.stream()  //메뉴에서 스트림 획득
       .filter(d -> d.getCalories() > 300) //파이프라인 연산 만들기. 첫 번째로 고칼로리 요리를 필터링
       .map(Dish::getName)     //요리명 추출
       .limit(3)               //선착순 3개 선택
       .collect(toList());     //결과를 다른 리스트로 저장
```

연결할 수 있는 스트림 연산을 중간 연산(intermediate opertaion)이라고 하며, 스트림을 닫는 연산을 최종 연산(terminal operation)이라고 한다.

**중간 연산**

filter나 sorted같은 중간 연산은 다른 스트림을 반환한다. 따라서 여러 중간 연산을 연결해서 질의를 만들 수 있다. 
중간 연산의 중요한 특징은 단말 연산을 스트림 파이프라인에 실행하기 전까지는 아무 연산도 수행하지 않는다는 것, 
즉 게으르다는 것이다. 중간 연산을 합친 다음에 합쳐진 중간 연산을 최종 연산으로 한 번에 처리하기 때문이다.

**최종 연산**

최종 연산은 스트림 파이프라인에서 결과를 도출한다. 보통 최종 연산에 의해 List, Integer, void 등 스트림 이외의 결과가 반환된다.

스트림 이용 과정은 다음과 같이 세 가지로 요약할 수 있다.
- 질의를 수행할 (컬렉션 같은) **데이터 소스**
- 스트림 파이프라인을 구성할 **중간 연산** 연결
- 스트림 파이프라인을 실행하고 결과를 만들 **최종 연산**

스트림의 파이프라인은 빌더 패턴과 비슷하다. 빌더 패턴에서는 호출을 연결해서 설정을 만든다. 그리고 준비된 설정에 build메서드를 호출한다.

### 스트림 활용

**Predicate로 필터링**
```java
List<Dish> vegetarianMenu = menu.stream()
                               .filter(Dish::isVegetarian)
                               .collect(toList());
```

**고유 요소 필터링**
```java
List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
numbers.stream()
       .filter(i -> i%2 == 0)
       .distinct()
       .forEach(System.out::print);
```

**Predicate가 적어도 한 요소와 일치하는지 확인**
```java
if(menu.stream().anyMatch(Dish::isVegetarian)){
   System.out.println("The menu is (somewhat) vegetarian friendly!!");
}
```

**Predicate가 모든 요소와 일치하는지 검사**
```java
boolean isHealthy = menu.stream()
                       .allMatch(d -> d.getCalories() < 1000);
```

**주어진 Predicate에서 일치하는 요소가 없는지 확인.**
```java
boolean isHealthy = menu.stream()
                       .noneMatch(d -> d.getCalories() >= 1000);
```

### 리듀싱 연산

리듀싱 연산이란 모든 스트림 요소를 처리해서 값으로 도출하는 것이다.
```java
int sum = 0;
for(int x : numbers){
   sum += x;
}
위와 같은 코드를 아래와 같이 변경할 수 있다.
int sum = numList.stream().reduce(0, (a,b) -> a + b);

더하기뿐만 아니라 곱셈도 가능하다.
int product = numList.stream().reduce(1, (a,b) -> a * b);

더하기 람다 표현식을 메서드 레퍼런스로 간결하게 할 수도 있다.
int sum1 = numList.stream().reduce(0, Integer::sum);
```

**초깃값이 없는 리듀싱 연산**
```java
Optional<Integer> reduce = numList.stream().reduce((a, b) -> (a + b));
```

왜 Optional을 반환하는 걸까? 스트림에 아무 요소도 없는 경우, 초깃값이 없으므로 reduce는 합계를 반환할 수 없다. 따라서 Optional객체로 감싼 결과를 반환한다.

최댓값과 최솟값 연산

reduce는 두 인수를 받는다.
- 초깃값
- 스트림의 두 요소를 합쳐서 하나의 값으로 만드는 데 사용할 람다식.

```java
최댓값
Optional<Integer> max = numList.stream().reduce(Integer::max);

최솟값
Optional<Integer> min = numList.stream().reduce(Integer::min);

요리 스트림에서 요리의 갯수를 반환하는 문제.
Integer count = menu.stream()
       .map(d -> 1)
       .reduce(0, (a, b) -> a + b);
map과 reduce를 연결하는 기법을 맵 리듀스 패턴이라고 하며, 쉽게 병렬화하는 특징 덕분에 구글이 웹 검색에 적용하면서 유명해졌다.
```

**reduce 메서드의 장점과 병렬화**

기존의 단계적 반복으로 합계를 구하는 것과 reduce를 이용해서 합계를 구하는 것은 어떤 차이가 있을까? reduce를 이용하면 내부 반복이 추상화되면서 내부 구현에서 병렬로 reduce를 실행할 수 있게 된다. 
반복적인 합계에서는 sum 변수를 공유해야 하므로 쉽게 병렬화하기 어렵다. 
강제적으로 동기화시킨다 하더라도 결국 병렬화로 얻어야 할 이득이 스레드 간의 소모적인 경쟁때문에 상쇄되어 버린다는 사실을 알게 될 것이다.

### 컬렉터(Collectors)

Collectors에서 제공하는 메서드의 기능은 크게 세 가지로 구분할 수 있다.

- 스트림 요소를 하나의 값으로 리듀스하고 요약
- 요소 그룹화
- 요소 분할

