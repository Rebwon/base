package ko.maeng.oop.elgant;

public class StringAsInteger implements Number {
  private final String text;

  public StringAsInteger(String text) {
    this.text = text;
  }

  // 객체를 초기화 후 실제로 int 값을 사용하는 시점에 파싱한다.
  public int intValue() {
    return Integer.parseInt(text);
  }
}
