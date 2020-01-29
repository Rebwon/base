package ko.maeng.base.java8.java8inaction.chap6;

import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class CollectReducing {
    public static void main(String[] args) {
        int totalCalories = Dish.menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        IntSummaryStatistics menuStatistics = Dish.menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        int totalCalories2 = Dish.menu.stream()
                .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories2);

        Optional<Dish> mostCaloriedish = Dish.menu.stream()
                .collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCaloriedish.get());
    }
}
