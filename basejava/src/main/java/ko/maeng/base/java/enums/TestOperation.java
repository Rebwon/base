package ko.maeng.base.java.enums;

public class TestOperation {

  public static void main(String[] args) {
    double x = Double.parseDouble(args[0]);
    double y = Double.parseDouble(args[1]);
    test(BasicOperation.class, x, y);
  }

  private static <T extends Enum<T> & Operation> void test(Class<T> opSet,
      double x, double y) {
    for(Operation op : opSet.getEnumConstants()) {
      System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }
  }

}
