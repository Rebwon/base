package ko.maeng.base.java8.java8inaction.chap10;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {
    @Test
    public void map() {
        Insurance insurance = new Insurance();
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optionalInsurance.map(Insurance::getName);

        assertThat(name).isEmpty();
    }

    @Test
    public void flatMap() {
        Person person = null;
        Car car = new Car();
        Optional<Person> optPerson = Optional.ofNullable(person);

        assertThat(car.getCarInsuranceName(optPerson, 23)).isEqualTo("Unknown");
    }
}