package ko.maeng.base.javajigi.gugudan;

public class GugudanClass {
    public static void calculateFianl1(int times) {
        for(int i=2; i<=times; i++) {
            for (int j = 0; j < times; j++) {
                System.out.println(i * (j + 1));
            }
        }
    }

    public static void calculateFianl2(int dan, int num) {
        for(int i=2; i<=dan; i++) {
            for (int j = 1; j <= num; j++) {
                System.out.println(i * j);
            }
        }
    }

    public static int[] calculate(int times){
        int[] result = new int[9];
        for(int i=0; i<result.length; i++){
            result[i] = times * (i+1);
        }
        return result;
    }

    public static void print(int[] result){
        for(int i=0; i<result.length; i++){
            System.out.println(result[i]);
        }
    }
}
