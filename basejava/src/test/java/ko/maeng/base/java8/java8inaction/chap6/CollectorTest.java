package ko.maeng.base.java8.java8inaction.chap6;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.maxBy;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectorTest {
    @Test
    public void compareMax() {
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> collect = Dish.menu.stream().collect(maxBy(dishComparator));

        assertThat(collect.get()).isEqualTo("pork");
    }


}