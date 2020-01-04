package ko.maeng.base.java8;

import ko.maeng.base.java8.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTest2 {
    private List<Product> products;
    private List<String> lang;

    @Before
    public void setup(){
        products = Arrays.asList(new Product(23, "potatoes"),
                new Product(14, "orange"),
                new Product(13, "lemon"),
                new Product(23, "bread"),
                new Product(13, "sugar"));

        lang = Arrays.asList("Java", "Kotlin", "Python", "JavaScript", "Scala", "Swift");
    }

    @Test
    public void 스트림_동작순서() {
        Optional<String> a = lang.stream()
                .filter(el -> {
                    System.out.println("filter() was called");
                    return el.contains("a");
                })
                .map(el -> {
                    System.out.println("map() was called");
                    return el.toUpperCase();
                })
                .findFirst();
        assertThat(a.get()).isEqualTo("JAVA");
    }

    @Test
    public void 스트림_Skip() {
        // map() 연산이 6번 실행됨.
        List<String> collect = lang.stream()
                .map(el -> {
                    System.out.println("map() was called");
                    return el.substring(0, 3);
                })
                .skip(2)    // 뒤의 2개는 스킵하여 collect에 넣지 않음.
                .collect(Collectors.toList());
        assertThat(collect).contains("Pyt", "Jav", "Sca", "Swi");
    }

    @Test
    public void 스트림_최적화() {
        // map() 연산이 4번만 실행됨.
        // 요소의 범위를 줄이는 작업을 먼저 실행하는 것이
        // 불필요한 연산을 막을 수 있어 성능을 향상 시킬 수 있다.
        List<String> collect = lang.stream()
                .skip(2)    // 먼저 skip시킴으로써 불필요한 연산을 줄인다.
                .map(el -> {
                    System.out.println("map() was called");
                    return el.substring(0, 3);
                })
                .collect(Collectors.toList());
        assertThat(collect).contains("Pyt", "Jav", "Sca", "Swi");
    }

    @Test(expected = IllegalStateException.class)
    public void 스트림_재사용() {
        Stream<String> stream = lang.stream().filter(lang -> lang.contains("a"));

        Optional<String> first = stream.findFirst();
        Optional<String> any = stream.findAny();

        assertThat(first.get()).isEqualTo("Java");
        // 스트림이 닫혔기 때문에 IllegalStateException 발생
        assertThat(any.get()).isEqualTo("");
    }

    @Test
    public void Null_Safe한_스트림() {
        Stream<String> stream = collectionToStream(lang);
        List<String> collect = stream.collect(Collectors.toList());

        assertThat(collect).contains("Java", "Kotlin", "Python", "JavaScript", "Scala", "Swift");

        lang = null;
        collectionToStream(lang)
                .filter(s -> s.contains("a"))
                .map(String::length)
                .forEach(System.out::println);  // NullPointerException 발생안함.
    }

    public <T> Stream<T> collectionToStream(Collection<T> collection) {
        return Optional
                .ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }



}
