package ko.maeng.base.java8.java8inaction.chap5;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pythagoras {
    public static void main(String[] args) {
        Stream<int[]> pythagorasNum = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b ->
                                        new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        pythagorasNum.limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> pythagorasNum2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(
                                        b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                ).filter(t -> t[2] % 1 == 0);

        pythagorasNum2.limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
