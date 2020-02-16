package ko.maeng.base.java8.java8inaction.chap10;

import java.util.*;

public class Person {
    private Car car;
    private Optional<Car> optCar;

    public Optional<Car> getCar() {
        return optCar;
    }

    public Optional<Car> getCarAsOptional() {
        return Optional.ofNullable(car);
    }
}
