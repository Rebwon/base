package ko.maeng.oop.blackjack;

import java.util.Scanner;

public class Client {

    public void print(String msg){
        System.out.println(msg);
    }

    public String input(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
