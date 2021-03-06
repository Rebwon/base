package ko.maeng.base.java8.java8inaction.chap6;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class PrimePartition {
    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                .noneMatch(i -> candidate % i == 0);
    }

    public static boolean isPrimeRoot(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    public static void main(String[] args) {
        // n개의 숫자를 입력받아 소수와 비소수를 나누는 프로그램.
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            //partitionPrimes(1_000_000);
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + "msecs");
    }
}
