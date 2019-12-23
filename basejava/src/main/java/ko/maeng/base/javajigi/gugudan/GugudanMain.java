package ko.maeng.base.javajigi.gugudan;

import java.util.Scanner;

public class GugudanMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 최종 요구사항 1 달성.
        int times = sc.nextInt();
        GugudanClass.calculateFianl1(times);

        String input = sc.nextLine();
        String[] split = input.split(",");
        int dan = Integer.parseInt(split[0]);
        int num = Integer.parseInt(split[1]);

        // 최종 요구사항 2 달성.
        GugudanClass.calculateFianl2(dan, num);

        // 프로그램을 실행하는 클래스와 구구단을 구현하는 클래스를 분리한다.
        /*for(int i=2; i<10; i++){
            System.out.println("===" + i + "단===");
            GugudanClass.print(GugudanClass.calculate(i));
        }*/
    }
}
