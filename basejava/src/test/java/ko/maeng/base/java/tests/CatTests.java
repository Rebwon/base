package ko.maeng.base.java.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CatTests {

  @Test
  public void moveTest() {
    Car car = new Car(3) {
      @Override
      protected int randomInt() {
        return 5;
      }
    };
    car.move();
    assertThat(car.isSamePosition(4)).isTrue();
  }

  @Test
  public void notMoveTest() {
    final Car car = new Car(3) {
      @Override
      protected int randomInt() {
        return 3;
      }
    };
    car.move();
    assertThat(car.isSamePosition(3)).isTrue();
  }
}
