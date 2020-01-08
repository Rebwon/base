package ko.maeng.base.effectivejava2.chap1;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NutritionFactsTest {
    @Test
    public void create() {
        NutritionFacts cocaCola = new NutritionFacts
                .Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();

        assertThat(cocaCola.getServingSize()).isEqualTo(240);
    }
}