package ko.maeng.base.java.annotations;

public class Sample {

  @Test
  public static void m1() {}
  public static void m2() {}
  @Test public static void m3() {
    throw new RuntimeException("Boom");
  }
  public static void m4() {}
  @Test public void m5() {}
  public static void m6() {}
  public static void m8() {}
}
