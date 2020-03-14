package ko.maeng.oop.vending.domain;

import ko.maeng.oop.vending.domain.exception.BeverageNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static ko.maeng.oop.vending.domain.Beverage.BeverageType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("BeverageList 클래스")
class BeverageListTest {
    private BeverageList beverages;

    @BeforeEach
    void setUp() {
        List<Beverage> beverageList = asList(
                new Beverage(COLA, 2500),
                new Beverage(SPRITE, 1500)
        );
        beverages = new BeverageList();
        beverages.addAll(beverageList);
    }

    @Nested
    @DisplayName("hasBerverage 메서드는")
    class Describe_hasBerverage{
        @Test
        @DisplayName("Berverage가 있는지 확인한다.")
        void hasBeverage() {
            boolean hasBeverage = beverages.hasBeverage(new Beverage(COLA, 2500));

            assertThat(hasBeverage).isTrue();
        }
    }

    @Nested
    @DisplayName("increase 메서드는")
    class Describe_increase{
        @Test
        @DisplayName("Berverage를 추가한다.")
        void increase() {
            beverages.increase(new Beverage(COLA, 3000));

            assertThat(beverages.getSize()).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("decrease 메서드는")
    class Describe_decrease{
        @Test
        @DisplayName("Beverage를 삭제한다.")
        void decrease() {
            Beverage beverage = beverages.get(new Beverage(SPRITE, 1500));
            Beverage beverage1 = beverages.get(new Beverage(SPRITE, 1500));


            assertThat(beverage).isSameAs(beverage1);
        }
    }

    @Nested
    @DisplayName("get 메서드는")
    class Describe_get{
        @Test
        @DisplayName("Berverage를 리턴한다.")
        void get() {
            Beverage beverage = beverages.get(new Beverage(SPRITE, 1500));

            assertThat(beverage.getBeverageType()).isEqualTo(SPRITE);
            assertThat(beverage.getPrice()).isEqualTo(1500);
        }

        @Test
        @DisplayName("Berverage가 없다면 예외를 발생한다.")
        void exception() {
            assertThrows(BeverageNotFoundException.class, () -> {
                beverages.get(new Beverage(COLA, 1500));
            });
        }
    }
}