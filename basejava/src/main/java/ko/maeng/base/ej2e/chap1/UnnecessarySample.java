package ko.maeng.base.ej2e.chap1;

public class UnnecessarySample {
    public static void main(String[] args) {
        // 불필요한 객체는 생성하지 마라.
        long start = System.currentTimeMillis();

        long sum = 0L;  // Long -> long으로 바꿔줘야함.
        for(long i=0; i<Integer.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);

        long end = System.currentTimeMillis();
        System.out.println("실행 시간: " + (end-start)/1000.0);
        // Long -> long으로 바꾸었을 시
        // 8초 초반에서 1초 대로 최적화됨.
        // 객체 표현형 대신 기본 자료형을 사용하고 생각지도 못한 자동 객체화가
        // 발생하지 않도록 유의하자.
    }
}
