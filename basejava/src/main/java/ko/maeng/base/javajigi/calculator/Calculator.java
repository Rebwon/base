package ko.maeng.base.javajigi.calculator;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        // == 항등 연산자이다. 참조 비교(주소 비교)를 한다.
        // 참조 비교란 두 객체가 같은 메모리 공간을 가리키는지 확인하는 것.
        // 반환 타입은 boolean

        // equals 객체 비교 메소드이다. 내용을 비교한다.
        // 두 객체의 값이 같은지 확인한다. 즉, 문자열의 데이터/내용을 기반으로 비교한다.
        // 기본형 객체에 대해서는 적용이 불가하다.
        // 반환 타입은 booelan

        // compareTo Comparable인터페이스가 구현된 객체에서 사용가능한 객체 비교 메소드이다.
        // Java에서 제공되는 정렬이 가능한 클래스들은 모두 Comparable인터페이스를 구현하고 있다.
        // 따라서 자바에서 정의한 규칙에 맞게 정렬이 된다. Integer,Double 클래스는 오름차순 정렬
        // String 클래스는 사전순 정렬
        // 반환 타입은 Integer
        Scanner sc = new Scanner(System.in);
        int first = Input.getFirstValue(sc);

        int result = first;
        while(true){
            String symbol = Input.getSymbol(sc);

            if("quit".equals(symbol)){
                Output.print(result);
                break;
            }
            int second = Input.getSecondValue(sc);
            result = calculate(result, symbol, second);
        }
        sc.close();
    }

    static int calculate(int first, String symbol, int second){
        int result = 0;
        if("+".equals(symbol)){
            result = first + second;
            System.out.println("덧셈 : " + result);
        } else if("-".equals(symbol)){
            result = first - second;
            System.out.println("뺄셈 : " + result);
        } else if("*".equals(symbol)){
            result = first * second;
            System.out.println("곱셈 : " + result);
        } else if("/".equals(symbol)){
            result = first / second;
            System.out.println("나눗셈 : " + result);
        } else{
            System.out.println("사칙연산에 해당하지 않는 값을 입력했습니다.");
        }
        return result;
    }
}
