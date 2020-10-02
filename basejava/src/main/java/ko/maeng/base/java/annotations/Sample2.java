package ko.maeng.base.java.annotations;

import java.util.ArrayList;
import java.util.List;

public class Sample2 {
  @ExceptedTest(ArithmeticException.class)
  public static void m1() {
    int i = 0;
    i = i / i;
  }

  @ExceptedTest(RuntimeException.class)
  public static void m2() {
    throw new RuntimeException("BOOk");
  }

  @ExceptedTest({IndexOutOfBoundsException.class, NullPointerException.class})
  public static void doublyBad() {
    List<String> list = new ArrayList<>();

    list.addAll(5, null);
  }
}
