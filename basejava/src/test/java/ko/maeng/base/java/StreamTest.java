package ko.maeng.base.java;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest {
    // Stream은 한 번 사용하면 닫힌다.
    // Test의 경우 한 번 테스트를 실행하면 닫히기 때문에
    // 스트림 인스턴스로 여러 테스트를 실행할 수 없다.
    // 각각의 다른 인스턴스로 다양한 테스트를 해야만 가능하다.
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
        List<String> lang =
                Arrays.asList("Java", "Kotlin", "Python", "JavaScript", "Go", "Swift");

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
        List<String> lang =
                Arrays.asList("Java", "Kotlin", "Python", "JavaScript", "Go", "Swift");

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
}
