package ko.maeng.base.java8.java8inaction.chap7;

import java.util.function.Function;

public class ParallelHarness {
    public static void main(String[] args) {
        System.out.println("Sequential sum done in : " +
                measureSumPerf(ParallelStreams::parallelRangedSum, 2_000_000) + " msecs");
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n){
        long fastest = Long.MAX_VALUE;
        for(int i=0; i<10; i++){
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if(duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
