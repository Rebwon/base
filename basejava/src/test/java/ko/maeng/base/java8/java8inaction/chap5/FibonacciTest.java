package ko.maeng.base.java8.java8inaction.chap5;

import org.junit.Test;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonacciTest {
    @Test
    public void fibonacci() {
        // immutable
        Stream.iterate(new int[]{0,1}, (a) -> new int[]{a[1], a[0] + a[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        // mutable
        IntSupplier fib = new IntSupplier() {
            private int prev = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrev = this.prev;
                int nextVal = this.prev + this.current;
                this.prev = this.current;
                this.current = nextVal;
                return oldPrev;
            }
        };
        IntStream.generate(fib).limit(20).forEach(System.out::println);
    }
}
