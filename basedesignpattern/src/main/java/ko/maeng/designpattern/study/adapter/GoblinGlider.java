package ko.maeng.designpattern.study.adapter;

// 어댑터 패턴에서 장치 클래스가 된다.
// 예시) 110V 전원 코드라 생각해도 좋다.
public class GoblinGlider {
    public void attachGlider(){
        System.out.println("Glider attached.");
    }

    public void gainSpeed(){
        System.out.println("Gaining speed.");
    }

    public void takeOff(){
        System.out.println("Lift-Off!");
    }
}
