package ko.maeng.base.effectivejava2.chap1;

import java.lang.reflect.Constructor;

public class PrivateInvoker {
    public static void main(String[] args) throws Exception {
        // 리플렉션과 setAccessible 메서드를 통해 private로 선언된
        // 생성자의 호출 권한을 획득한다.
        Constructor<?> con = Private.class.getDeclaredConstructor();
        con.setAccessible(true);
        Private p = (Private) con.newInstance();

        Constructor<?> con2 = Zavis.class.getDeclaredConstructor();
        con2.setAccessible(true);
        Zavis zavis = (Zavis) con2.newInstance();
    }
}

class Private {
    private Private(){
        System.out.println("Hello!");
    }
}
