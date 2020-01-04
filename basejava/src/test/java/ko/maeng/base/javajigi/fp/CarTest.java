package ko.maeng.base.javajigi.fp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarTest {
    @Test
    public void 이동() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(() -> true);
        assertThat(actual).isEqualTo(new Car("pobi", 1));
    }

    @Test
    public void 정지(){
        Car car = new Car("pobi", 0);
        Car actual = car.move(() -> false);
        assertThat(actual).isEqualTo(new Car("pobi", 0));
    }
}