package ko.maeng.base.javajigi.calculator;

import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("사칙연산을 수행할 값을 입력하세요 : ");

        String value = sc.nextLine();
        System.out.println("입력한 값 : " + value);

        String[] values = value.split(" ");

        int first = Integer.parseInt(values[0]);
        System.out.println("first : " + first);

        int result = first;
        for(int i=1; i<values.length; i+=2){
            String symbol = values[i];
            System.out.println("symbol : " + symbol);

            int second = Integer.parseInt(values[i + 1]);
            System.out.println("second : " + second);

            result = Calculator.calculate(result, symbol, second);
        }
        Output.print(result);
    }
}
