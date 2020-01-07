package ko.maeng.base.java8.stream;

import ko.maeng.base.java8.Product;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {
    // Stream은 종료 작업을 처리하면 사용이 불가하다.
    // 종료 작업을 처리하지 않고 사용할 경우
    // 하나의 인스턴스로 여러 작업을 실행할 수 있다.
    private List<Product> products;
    private List<String> lang;

    @Before
    public void setup(){
        products = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"),
                new Product(13, "lemon"),
                new Product(23, "bread"),
                new Product(13, "sugar"));

        lang = Arrays.asList("Java", "Kotlin", "Python", "JavaScript", "Go", "Swift");
    }

    @Test
    public void 배열스트림_생성() {
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> streamOfArrayPart =
                Arrays.stream(arr, 1, 3); // 1~2 요소 [b,c]
        assertThat(stream).hasSize(3);
        assertThat(streamOfArrayPart).hasSize(2);
    }

    @Test
    public void 컬렉션스트림_생성() {
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();
        assertThat(stream).hasSize(3);
        assertThat(parallelStream).hasSize(3);
    }

    @Test
    public void 비어있는_스트림_생성() {
        Stream<Object> empty = Stream.empty();
        assertThat(empty).hasSize(0);
    }

    @Test
    public void 빌더스트림_생성() {
        Stream<String> builderStream =
                Stream.<String>builder()
                    .add("Eric").add("Elena").add("Java")
                .build();
        assertThat(builderStream).contains("Eric");
    }

    @Test
    public void 스트림_generate() {
        Stream<String> stringStream = Stream.generate(() -> "gen").limit(5);
        assertThat(stringStream).hasSize(5);
    }

    @Test
    public void 스트림_iterate() {
        Stream<Integer> integerStream = Stream.iterate(30, n -> n + 2).limit(5);
        assertThat(integerStream).contains(30, 32, 34, 36, 38);
    }

    @Test
    public void 기본타입_스트림() {
        IntStream intStream = IntStream.range(1, 5);
        LongStream longStream = LongStream.rangeClosed(1, 5);
        assertThat(intStream).contains(1, 2, 3, 4);
        assertThat(longStream).contains(1l, 2l, 3l, 4l, 5l);
    }

    @Test
    public void 기본타입_박싱() {
        Stream<Integer> integerStream = IntStream.range(1, 5).boxed();
        assertThat(integerStream).contains(1, 2, 3, 4);
    }

    @Test
    public void 난수스트림_생성() {
        DoubleStream doubles = new Random().doubles(3);
        assertThat(doubles).hasSize(3);
    }

    @Test
    public void 문자스트림_생성() {
        // Stream이라는 문자를 chars로 유니코드 숫자로 변환해서 스트림 생성
        IntStream intStream = "Stream".chars();
        assertThat(intStream).contains(83, 116, 114, 101, 97, 109);
    }

    @Test
    public void 문자열스트림_생성() {
        Stream<String> stringStream = Pattern.compile(", ").splitAsStream("Eric, Elena, Java");
        assertThat(stringStream).contains("Eric", "Elena", "Java");
    }

    @Test
    public void 파일스트림_생성() throws Exception {
        Stream<String> stringStream = Files.lines(Paths.get("src/main/resources/fp/file.txt"), Charset.forName("UTF-8"));
        assertThat(stringStream).isEmpty();
    }

    @Test
    public void 병렬스트림_생성() {
        // 컬렉션을 이용한 병렬 스트림 생성
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> parallelStream1 = list.parallelStream();

        // 배열을 이용한 병렬 스트림 생성
        String[] arr = new String[]{"Eric", "Elena", "Java"};
        Stream<String> parallelStream2 = Arrays.stream(arr).parallel();

        // 컬렉션과 배열이 아닌 경우의 병렬 스트림 생성
        IntStream parallelStream3 = IntStream.range(1, 150).parallel();

        // 병렬 여부 확인
        assertThat(parallelStream1.isParallel()).isTrue();
        assertThat(parallelStream2.isParallel()).isTrue();
        assertThat(parallelStream3.isParallel()).isTrue();
    }

    @Test
    public void 병렬스트림_처리() {
        List<String> list = Arrays.asList("a", "b", "c");
        Stream<String> parallelStream = list.parallelStream();

        List<String> collect = parallelStream.map(s -> s.toUpperCase())
                .collect(Collectors.toList());

        assertThat(collect).contains("A", "B", "C");
    }

    @Test
    public void 스트림_연결() {
        Stream<String> stream1 = Stream.of("Java", "Kotlin", "Python");
        Stream<String> stream2 = Stream.of("JavaScript", "Go", "Swift");
        Stream<String> concat = Stream.concat(stream1, stream2);

        assertThat(concat).hasSize(6);
    }

    @Test
    public void 스트림_필터링() {
        List<String> lang = Arrays.asList("Java", "Kotlin", "Python");
        Stream<String> stream = lang.stream()
                .filter(language -> language.contains("a"));

        assertThat(stream).contains("Java");
    }

    @Test
    public void 스트림_맵핑() {
        List<String> lang = Arrays.asList("Java", "Kotlin", "Python");
        Stream<String> stream = lang.stream().map(String::toUpperCase);

        assertThat(stream).contains("JAVA", "KOTLIN", "PYTHON");
    }

    @Test
    public void 플래트닝_작업() {
        // 플래트닝(flattening)이란 여러 개의 컬렉션의 중첩을 제거하고
        // 단일 컬렉션으로 만들어서 스트림을 반환해주는 작업.
        List<List<String>> list = Arrays
                .asList(Arrays.asList("Java", "Kotlin", "Python"),
                        Arrays.asList("JavaScript", "Go", "Swift"));

        // 플래트닝 처리
        List<String> collect = list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        assertThat(collect).hasSize(6);
    }

    @Test
    public void 스트림_정렬() {
        List<Integer> collect = IntStream.of(14, 11, 20, 39, 23)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
        // 오름차순 정렬
        assertThat(collect).contains(11, 14, 20, 23, 39);
    }

    @Test
    public void 스트림_역순정렬 () {
        List<String> list = lang.stream()
                .sorted()
                .collect(Collectors.toList());

        List<String> reverseList = lang.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertThat(list).contains("Go", "Java", "JavaScript", "Python", "Swift");
        assertThat(reverseList).contains("Swift", "Python", "JavaScript", "Java", "Go");
    }

    @Test
    public void 스트림_인자비교() {
        List<String> shortLang = lang.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        List<String> longLang = lang.stream()
                .sorted((s1, s2) -> s2.length() - s1.length())
                .collect(Collectors.toList());

        assertThat(shortLang).contains("Go", "Java", "Swift", "Python", "JavaScript");
        assertThat(longLang).contains("JavaScript", "Python", "Swift", "Java", "Go");
    }

    @Test
    public void 스트림_Peek() {
        int sum = IntStream.of(1, 3, 5, 7, 9)
                .peek(System.out::println)
                .sum();
        assertThat(sum).isEqualTo(25);
    }

    @Test
    public void 스트림_결과만들기() {
        long count = IntStream.of(1, 3, 5, 7, 9).count();
        long sum = LongStream.of(1, 3, 5, 7, 9).sum();

        assertThat(count).isEqualTo(5l);
        assertThat(sum).isEqualTo(25l);
    }

    @Test
    public void 스트림_평균_최소_최대 () {
        OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
        OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();

        assertThat(min.getAsInt()).isEqualTo(1);
        assertThat(max.getAsInt()).isEqualTo(9);

        OptionalDouble average = DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5)
                .average();
        assertThat(average.getAsDouble()).isEqualTo(3.3);
    }

    @Test
    public void 리듀싱_인자하나() {
        OptionalInt reduce = IntStream.range(1, 4).reduce((a, b) -> {
            return Integer.sum(a, b);
        });
        assertThat(reduce.getAsInt()).isEqualTo(6);
    }

    @Test
    public void 리듀싱_인자둘() {
        int reduce = IntStream.range(1, 4).reduce(10, Integer::sum);
        assertThat(reduce).isEqualTo(16);
    }

    @Test
    public void 리듀싱_인자셋() {
        // Combiner를 인자로 주었지만 실행 값은 16이다.
        // 왜? 병렬 스트림에서만 동작하기 때문.
        Integer reducedParams = Stream.of(1, 2, 3)
                .reduce(10,
                        Integer::sum,
                        (a, b) -> {
            System.out.println("combiner was called");
            return a + b;
                });
        assertThat(reducedParams).isEqualTo(16);
    }

    @Test
    public void 리듀싱_인자셋_Combiner() {
        Integer reducedParallel = Arrays.asList(1, 2, 3)
                .parallelStream()
                .reduce(10,
                        Integer::sum, // 총 3번 동작함.
                        (a, b) -> {   // 총 2번 동작함.
                            System.out.println("combiner was called");
                            System.out.println(a + "+" + b + "=" + (a+b));
                            return a + b;
                        });
        // accumulator의 동작 : 초기값 10을 더한 세 개의 값
        // (10+1, 10+2, 10+3)을 Combiner에게 전달
        // Combiner는 (12+13 == 25)로 먼저 더한 후 (25+11=36)으로 최종값 반환.
        // 더하는 순서는 (a + b) + c 나 (a + c) + b나 동일하기 때문에
        // 순서는 아무런 상관이 없다.
        assertThat(reducedParallel).isEqualTo(36);
    }

    @Test
    public void 스트림_Collect() {
        List<String> collect = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        assertThat(collect).contains("potatoes", "orange", "lemon", "bread", "sugar");
    }

    @Test
    public void 스트림_Joining() {
        String collect = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining());
        assertThat(collect).isEqualTo("potatoesorangelemonbreadsugar");
    }

    @Test
    public void 스트림_Joining_format() {
        String collect = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", ", "[", "]"));
        // Collectors.joining(delimiter, prefix, suffix);
        // delimiter : 각 요소 중간에 들어가 요소를 구분시켜주는 구분자
        // prefix : 결과 맨 앞에 붙는 문자
        // suffix : 결과 맨 뒤에 붙는 문자
        assertThat(collect).isEqualTo("[potatoes, orange, lemon, bread, sugar]");
    }

    @Test
    public void 스트림_averagingInt() {
        Double collect = products.stream().collect(Collectors.averagingInt(Product::getAmount));
        assertThat(collect).isEqualTo(17.2);
    }

    @Test
    public void 스트림_summingInt() {
        Integer collect = products.stream().collect(Collectors.summingInt(Product::getAmount));
        assertThat(collect).isEqualTo(86);

        // 좀 더 간단한 표현
        Integer sum = products.stream().mapToInt(Product::getAmount).sum();
        assertThat(sum).isEqualTo(86);
    }

    @Test
    public void 스트림_합계_평균구하기() {
        IntSummaryStatistics collect = products.stream().collect(Collectors.summarizingInt(Product::getAmount));
        // collect에 담긴 정보 : {count=5, sum=86, min=13, average=17.200000, max=23}
        // 개수, 합계, 평균, 최소, 최대
        assertThat(collect.getCount()).isEqualTo(5l);
        assertThat(collect.getSum()).isEqualTo(86l);
        assertThat(collect.getAverage()).isEqualTo(17.2);
        assertThat(collect.getMin()).isEqualTo(13);
        assertThat(collect.getMax()).isEqualTo(23);
    }

    @Test
    public void 스트림_groupingBy() {
        Map<Integer, List<Product>> collect = products.stream().collect(Collectors.groupingBy(Product::getAmount));
        // Map에 있는 값
        // {23=[Product{amount=23, name='potatoes'},
        //     Product{amount=23, name='bread'}],
        //  13=[Product{amount=13, name='lemon'},
        //     Product{amount=13, name='sugar'}],
        //  14=[Product{amount=14, name='orange'}]}
        assertThat(collect).containsKeys(23);
        assertThat(collect.size()).isEqualTo(3);
    }

    @Test
    public void 스트림_partitioningBy() {
        Map<Boolean, List<Product>> collect = products.stream().collect(Collectors.partitioningBy(el -> el.getAmount() > 15));
        // Map에 있는 값
        // {false=[Product{amount=14, name='orange'},
        //        Product{amount=13, name='lemon'},
        //        Product{amount=13, name='sugar'}],
        // true=[Product{amount=23, name='potatoes'},
        //       Product{amount=23, name='bread'}]}
        assertThat(collect.size()).isEqualTo(2);
    }

    @Test
    public void 스트림_collectionAndThen() {
        Set<Product> collect = products.stream().collect(Collectors.collectingAndThen(
                Collectors.toSet(),
                Collections::unmodifiableSet));
        assertThat(collect.size()).isEqualTo(5);
    }

    @Test
    public void 스트림_of(){
        // of(Supplier, BiConsumer, BinaryOperator, Characteristics)
        // Supplier로 new Collector 생성
        // BiConsumer로 계산
        // BinaryOperator로 계산 결과 수집
        Collector<Product, ?, LinkedList<Product>> toLinkedList = Collector.of(LinkedList::new,
                LinkedList::add,
                (first, second) -> {
                    first.addAll(second);
                    return first;
                });

        LinkedList<Product> collect = products.stream().collect(toLinkedList);
    }

    @Test
    public void 스트림_Matching() {
        // 하나라도 조건을 만족하는 요소가 있는지(anyMatch)
        // 모두 조건을 만족하는지(allMatch)
        // 모두 조건을 만족하지 않는지(noneMatch)
        boolean match1 = lang.stream().anyMatch(lang -> lang.contains("a"));
        boolean match2 = lang.stream().allMatch(lang -> lang.length() > 3);
        boolean match3 = lang.stream().noneMatch(lang -> lang.endsWith("s"));
        assertThat(match1).isTrue();
        assertThat(match2).isFalse();
        assertThat(match3).isTrue();
    }

    @Test
    public void 스트림_iterating() {
        lang.stream().forEach(System.out::println);
    }
}
