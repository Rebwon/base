package ko.maeng.base.javajigi.gugudan;

public class GugudanMethod {
    public static void main(String[] args) {
        // 구구단을 구현하기 위해 반복적으로 발생하는 부분이 있다.
        // 반복적으로 발생하는 부분을 메소드를 활용해 줄인다.
        for(int i=2; i<10; i++){
            System.out.println("===" + i + "단===");
            print(calculate(i));
        }
    }

    private static void print(int[] result) {
        for(int i=0; i<result.length; i++){
            System.out.println(result[i]);
        }
    }

    private static int[] calculate(int times) {
        int[] result = new int[9];
        for(int i=0; i<result.length; i++){
            result[i] = times * (i+1);
        }
        return result;
    }
}
