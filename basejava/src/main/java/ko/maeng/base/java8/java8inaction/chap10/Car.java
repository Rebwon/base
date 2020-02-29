package ko.maeng.base.java8.java8inaction.chap10;

import java.util.*;

public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person
                .filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
