package ko.maeng.base.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> list1 = Arrays.asList(3, 4);

        list.stream()
                .flatMap(i -> list1.stream()
                        .filter(j -> (i+j) % 3 == 0)
                        .map(j -> new int[]{i,j}))
                .collect(Collectors.toList());
    }
}
