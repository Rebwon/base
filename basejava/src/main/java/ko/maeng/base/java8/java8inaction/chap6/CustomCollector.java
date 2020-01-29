package ko.maeng.base.java8.java8inaction.chap6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class CustomCollector {
    public static void main(String[] args) {
        List<Dish> dishes = Dish.menu.stream().collect(new ToListCollector<Dish>());
        System.out.println(dishes);
    }

    public static class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
        // 빈 누적자 인스턴스를 만드는 파라미터가 없는 함수이다.
        @Override
        public Supplier<List<T>> supplier() {
            //return () -> new ArrayList<>();
            return ArrayList::new;
        }

        // 리듀싱 연산을 수행하는 함수를 반환한다.
        // 스트림에서 n번째 요소를 탐색할 때 두 인수, 즉 누적자와 n번째 요소를 함수에 적용한다.
        @Override
        public BiConsumer<List<T>, T> accumulator() {
            //return (list, item) -> list.add(item);
            return List::add;
        }

        // 항등 함수
        // 무조건 x = y에 대응하고 y = x에 대응해야하는 함수.
        @Override
        public Function<List<T>, List<T>> finisher() {
            return Function.identity();
        }

        // 두 결과 리스트 병합해서 첫 번째 리스트를 반환.
        @Override
        public BinaryOperator<List<T>> combiner() {
            return (list1, list2) -> {
                list1.addAll(list2);
                return list1;
            };
        }

        // 다중 스레드에서 병렬 리듀싱을 지원 한다 : CONCURRENT
        // 리듀싱 연산을 통한 누적자 객체를 반환 : IDENTITY_FINISH
        // 추가로 IDENTITY_FINISH는 누적자 A를 결과 R로 안전하게 형변환 해줌.
        @Override
        public Set<java.util.stream.Collector.Characteristics> characteristics() {
            return Collections.unmodifiableSet(
                    EnumSet.of(IDENTITY_FINISH, CONCURRENT));
        }
    }
}
