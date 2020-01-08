package ko.maeng.base.effectivejava2.chap1;

import java.io.Serializable;

public class Elvis implements Serializable {
    // 정적 팩터리 메서드를 이용한 싱글턴 구현
    // 다만, 구현한 싱글턴 클래스를 직렬화 가능 클래스로 만들려면
    // Serializable을 구현하는 것만으로는 충족되지 않는다.
    // 싱글턴 특성을 유지하려면 모든 필드를 transient로 선언하고
    // readResolve를 구현해야 한다. 그렇지 않으면 역직렬화를 시도할 때마다
    // 새로운 객체가 생기게 된다.
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() {
    }
    public static Elvis getInstance() {
        return INSTANCE;
    }

    // 싱글턴 상태를 유지하기 위한 readResolve 구현
    private Elvis readResolve() {
        // 동일한 Elvis 객체가 반환되도록 하는 동시에, 가짜 Elvis 객체는
        // 가비지 콜렉터가 처리하도록 만든다.
        return INSTANCE;
    }
}
