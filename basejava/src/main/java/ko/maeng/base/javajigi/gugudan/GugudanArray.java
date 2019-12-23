package ko.maeng.base.javajigi.gugudan;

import java.util.Scanner;

public class GugudanArray {
    public static void main(String[] args) {
        // 구구단을 배열에 저장한다.
        int[] result = new int[9];

        // 2중 for문을 통해 중복을 제거한다.
        for(int i=2; i<10; i++){
            for(int j=0; j<result.length; j++){
                result[j] = i * (j+1);
                System.out.println(result[j]);
            }
        }

//        // 계산 결과를 배열에 저장.
//        for(int i=0; i<result.length; i++){
//            result[i] = 2 * (i+1);
//        }
//
//        // 계산 결과를 화면에 출력
//        for(int i=0; i<result.length; i++){
//            System.out.println(result[i]);
//        }
    }
}
