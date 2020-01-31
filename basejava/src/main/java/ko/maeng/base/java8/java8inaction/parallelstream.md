## Parallel Stream(병렬 스트림)

병렬 스트림이란 각각의 스레드에서 처리할 수 있도록 스트림 요소를 여러 청크로 분할한 스트림이다.
따라서 병렬 스트림을 이용하면 모든 멀티코어 프로세서가 각각의 청크를 처리하도록 할당할 수 있다.

숫자 n을 인수로 받아서 1부터 n까지의 모든 숫자의 합계를 반환하는 메서드를 구현한다고 가정하자.

스트림으로 구현.
```java
public static long sequentialSum(long n){
    return Stream.iterate(1L, i -> i + 1)
                 .limit(n)
                 .reduce(0L, Long::sum);
}
```

전통적인 자바(자바8 이전)에서는 반복문으로 구현이 가능하다.
```java
public static long iterativeSum(long n){
    long result = 0;
    for(long i=1l; i<=n; i++){
        result += i;
    }
    return result;
}
```

n이 커진다면 이 연산을 병렬로 처리하는 것이 좋을 것이다. 무엇부터 건드려야 좋을까?
결과 변수는 어떻게 동기화해야 할까? 몇 개의 스레드를 사용해야 할까? 숫자는 어떻게 생성할까?
생성된 숫자는 누가 더할까?

병렬 스트림을 사용하게 되면 위와 같은 고민을 쉽게 해결할 수 있다.

순차 스트림에 parallel 메서드를 호출하면 기존의 함수형 리듀싱 연산(숫자 합계 계산)이 병렬로 처리된다.

```java
public static long sequentialSum(long n){
    return Stream.iterate(1L, i -> i + 1)
                 .limit(n)
                 .parallel()
                 .reduce(0L, Long::sum);
}
```

스트림의 parallel 메서드에서 병렬로 작업을 수행하는 스레드는 어디서 생성되는 것이며, 몇개나 생성되는지, 그리고 그 과정을
어떻게 커스터마이즈할 수 있는지 궁금할 것이다.

병렬 스트림은 내부적으로 ForkJoinPool을 사용한다. 기본적으로 ForkJoinPool은 프로세서 수, 즉 Runtime.getRuntime().availableProcessors()가
반환하는 값에 상응하는 스레드를 갖는다.

### 병렬 스트림의 올바른 사용법

병렬 스트림을 잘못 사용하면서 발생하는 많은 문제는 공유된 상태를 바꾸는 알고리즘을 사용하기 때문에 일어난다.
다음은 n까지의 자연수를 더하면서 공유된 누적자를 바꾸는 프로그램을 구현한 코드이다.
```java
public static long sideEffectSum(long n) {
    Accumulator accumulator = new Accumulator();
    LongStream.rangeClosed(1, n).forEach(accumulator::add);
    return accumulator.total;
}

public static class Accumulator {
    private long total = 0;

    public void add(long value) {
        total += value;
    }
}
```

명령형 프로그래밍 패러다임에 익숙한 개발자라면 위와 같은 코드를 자주 구현할 것이다. 리스트의 숫자를 반복할때의 코드와 비슷하다.
즉, 누적자를 초기화하고 리스트의 각 요소를 하나씩 탐색하면서 누적자에 숫자를 추가할 수 있다.

위 코드는 본질적으로 순차 실행할 수 있도록 구현되어 있으므로 병렬로 실행하면 참사가 일어난다. 특히 total을 접근할 때마다(다수의 스레드에서 동시에 데이터에 접근하는)
데이터 레이스 문제가 일어난다. 동기화로 문제를 해결하다보면 결국 병렬화라는 특성이 없어져 버릴 것이다.

병렬 스트림이 올바로 동작하려면 공유된 가변 상태를 피해야 한다.

### 병렬 스트림 효과적으로 사용하기

1. 순차 스트림과 병렬 스트림 중 어떤 것이 좋을지 모르겠다면 적절한 벤치마크로 직접 성능을 측정하는 것이 바람직하다.
2. 박싱을 주의하자. 자동 박싱과 언박싱은 성능을 크게 저하시키는 요소이다. 자바8은 박싱 동작을 피할 수 있도록 기본형 특화 스트림을
제공한다. 따라서 되도록이면 기본형 특화 스트림을 사용하자.
3. 순차 스트림보다 병렬 스트림에서 성능이 떨어지는 연산이 있다. 특히 limit나 findFirst처럼 요소의 순서에 의존하는 연산을 병렬 스트림에서 수행하려면
비싼 비용을 치러야 한다. 예를 들어 findAny는 요소의 순서와 상관없이 연산하므로 findFirst보다 성능이 좋다. 정렬된 스트림에 unordered를 호출하면 비정렬된 스트림을 얻을 수 있다.
스트림에 N개 요소가 있을 때 요소의 순서가 상관없다면(예를 들어 소스가 리스트라면) 비정렬된 스트림에 limit를 호출하는 것이 더 효율적이다.
4. 스트림에서 수행하는 전체 파이프라인의 연산 비용을 고려하라. 처리해야 할 요소 수가 N이고 하나의 요소를 처리하는데 드는 비용이 Q라면
전체 스트림 파이프라인 처리 비용은 N * Q라고 예상할 수 있다. Q가 높아진다는 것은 병렬 스트림으로 성능을 개선할 수 있는 가능성이 있음을 의미한다.
5. 소량의 데이터에서는 병렬 스트림이 도움이 되지 않는다. 소량의 데이터를 처리하는 상황에서는 병렬화 과정에서 생기는 부가 비용을 상쇄할 만큼의 이득을 얻지 못하기 때문이다.
6. 스트림을 구성하는 자료구조가 적절한지 확인하라. 예를 들어 ArrayList를 LinkedList보다 효율적으로 분할할 수 있다. LinkedList를 분할하려면 모든 요소를 탐색해야 하지만 ArrayList는
요소를 탐색하지 않고도 리스트를 분할할 수 있기 때문이다. 또한 range 팩토리 메서드로 만든 기본형 스트림도 쉽게 분할 가능하다. 마지막으로 커스텀 Spliterator를 구현해서 분할 과정을 완벽히 제어할 수 있다.
7. 스트림의 특성과 파이프라인 중간 연산이 스트림의 특성을 어떻게 바꾸는지에 따라 분할 과정의 성능이 달라질 수 있다. 사이즈가 정해진 스트림이면 반으로 나누어 분할 가능하지만,
필터 연산이 있으면 스트림의 길이를 예측할 수 없으므로 효과적으로 병렬 처리할 수 있을지 알 수 없다.
8. 최종 연산의 병합 과정 비용을 살펴보라. 병합 과정의 비용이 비싸다면, 병렬 스트림으로 얻은 성능의 이익이 서브스트림의 부분결과를 합치는 과정에서 상쇄될 수 있다.

아래의 표는 분해와 관련한 다양한 스트림 소스의 병렬화 가능성을 요약했다.

| 소스 | 분해성 |
| --- | :---| 
|ArrayList |훌륭함
|LinkedList|나쁨
|IntStream.rande|훌륭함
|Stream.iterate|나쁨
|HashSet|좋음
|TreeSet|좋음

### 포크/조인 프레임워크 알아보기.

포크/조인 프레임워크는 병렬화 할 수 있는 작업을 재귀적으로 작은 작업으로 분할한 다음에 
서브태스크 각각의 결과를 합쳐서 전체 결과를 만들도록 설계되었다. 포크/조인 프레임워크에서는 
서브태스크를 스레드 풀(ForkJoinPool)의 작업자 스레드에 분산 할당하는 ExecutorService 인터페이스를 구현한다.

포크/조이니 프레임워크를 제대로 사용하는 방법.

```java
public static long forkJoinSum(long n){
    long[] numbers = LongStream.rangeClosed(1, n).toArray();
    ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
    return new ForkJoinPool().invoke(task);
}

@Override
protected Long compute() {
    int length = end - start;
    if(length <= THRESHOLD){
        return computeSequentially();
    }
    ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start+length/2);
    leftTask.fork();
    ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start+length/2, end);
    Long rightResult = rightTask.compute();
    Long leftResult = leftTask.join();
    return leftResult + rightResult;
}
```

1. join메서드를 태스크에 호출하면 태스크가 생산하는 결과가 준비될 때까지 호출자를 블록시킨다. 따라서 두 서브 태스크가 모두 시작된 다음에 join을 호출해야 한다.
그렇지 않으면 각각의 서브태스크가 다른 태스크가 끝나길 기다리는 일이 발생하며 원래 순차 알고리즘보다 느리고 복잡한 프로그램이 되어버릴 수 있다.
2. RecursiveTask 내에서는 ForkJoinPool의 invoke메서드를 사용하지 말아야 한다. 대신 compute나 fork메서드를 직접 호출할 수 있다. 순차 코드에서 병렬 계산을 시작할
때만 invoke를 사용한다.
3. 서브태스크에 fork메서드를 호출해서 ForkJoinPool의 일정을 조절할 수 있다. 왼쪽과 오른쪽 작업 모두에 fork 메서드를 호출하는 것이 자연스러울 것 같지만, 한쪽 작업에는 fork를
호출하는 것보다는 compute를 호출하는 것이 효율적이다. 그러면 두 서브 태스크의 한 태스크에는 같은 스레드를 재사용할 수 있으므로 풀에서 불필요한 태스크를 할당하는 오버헤드를 피할 수 있다.
4. 포크/조인 프레임워크를 사용하는 병렬 계산은 디버깅이 어렵다. 보통 IDE로 디버깅할 때 스택 트레이스로 문제가 일어난 과정을 쉽게 확인할 수 있는데, 포크/조인 프레임워크에서는 fork라 
불리는 다른 스레드에서 compute를 호출하므로 스택 트레이스가 도움이 되지 않는다.
5. 병렬 스트림에서 살펴본 것처럼 멀티코어에 포크/조인 프레임워크를 사용하는 것이 순차처리보다 무조건 빠를 거라는 생각은 버려야 한다. 병렬 처리로 성능을 개선하려면 태스크를 여러 독립적인
서브태스크로 분할할 수 있어야 한다. 각 서브태스크의 실행시간은 새로운 태스크를 포킹하는 데 드는 시간보다 길어야 한다. 또한 순차 버전과 병렬 버전의 성능을 비교할 때는 다른 요소도 고려해야 한다. 
다른 자바 코드와 마찬가지로 JIT(Just In Time) 컴파일러에 의해 최적화되려면 몇 차례의 준비과정 또는 실행과정을 거쳐야 한다. 따라서 성능을 측정할 때는 여러 번 프로그램을 실행한 결과를 측정해야 한다.

