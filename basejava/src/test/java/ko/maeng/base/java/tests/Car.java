package ko.maeng.base.java.tests;

public class Car {
  private int position;

  public Car(int position) {
    this.position = position;
  }

  public void move() {
    if(randomInt() > 4) {
      this.position++;
    }
  }

  // 테스트를 위해 protected로 변경 후 상속을 활용하여 단위 테스트 진행
  protected int randomInt() {
    return (int) (Math.random() * 10);
  }

  public boolean isSamePosition(int position) {
    return this.position == position;
  }
}
