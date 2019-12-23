package ko.maeng.base.javajigi.gugudan;

import java.util.Scanner;

public class Gugudan {
    public static void main(String[] args){
        System.out.println("구구단 중 출력할 단은? : ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if(num < 2){
            System.out.println("2이상, 9이하의 값만 입력할 수 있습니다.");
        } else if(num > 9){
            System.out.println("2이상, 9이하의 값만 입력할 수 있습니다.");
        } else{
            for(int i=1; i<10; i++){
                System.out.println(num + "단 : " + num * i);
            }
        }

//        // 내가 짠 방식
//        if(num < 2 || num > 9){
//            System.out.println("2이상, 9이하의 값만 입력할 수 있습니다.");
//            System.out.close();
//        }
//
//        for(int i=1; i<10; i++){
//            System.out.println(num + "단 : " + num * i);
//        }
    }
}
