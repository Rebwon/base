package ko.maeng.base.java8.java8inaction.chap10;

import java.util.*;

public class Person {
    private Car car;
    private int age;
    private Optional<Car> optCar;

    public int getAge() {
        return age;
    }

    public Optional<Car> getCar() {
        return optCar;
    }

    public Optional<Car> getCarAsOptional() {
        return Optional.ofNullable(car);
    }
}
