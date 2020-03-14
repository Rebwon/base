package ko.maeng.oop.vending.domain;

import ko.maeng.oop.vending.domain.exception.BeverageNotFoundException;
import ko.maeng.oop.vending.domain.exception.SameBeverageException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static ko.maeng.oop.vending.domain.Beverage.BeverageType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Machine 클래스")
class MachineTest {
    private Machine machine;
    private BeverageList beverages;

    @BeforeEach
    void setUp() {
        List<Beverage> beverageList = asList(
                new Beverage(COLA, 2500),
                new Beverage(SPRITE, 1500)
        );
        machine = new Machine();
        beverages = new BeverageList();
        beverages.addAll(beverageList);
    }

    @Nested
    @DisplayName("supply 메서드는")
    class Describe_supply{
        @Test
        @DisplayName("돈을 추가한다.")
        void supplyMoney() {
            machine.supply(50000);
            assertThat(machine.getPrice()).isEqualTo(50000);
        }

        @Test
        @DisplayName("음료를 추가한다.")
        void supplyBeverages() {
            machine.supply(beverages);
            assertThat(machine.getBeverages()).isNotNull();
        }
    }

    @Nested
    @DisplayName("findOne 메서드는")
    class Describe_findOne{
        @Test
        @DisplayName("음료를 리턴한다.")
        void findOne() {
            machine.supply(beverages);

            assertThat(machine.findOne(new Beverage(COLA, 2500))).isNotNull();
        }
    }

    @Nested
    @DisplayName("increase 메서드는")
    class Describe_increase{
        @Test
        @DisplayName("음료를 추가한다.")
        void increase() {
            machine.increase(new Beverage(SPRITE, 2000));

            assertThat(machine.getBeverages().getSize()).isEqualTo(1);
        }

        @Test
        @DisplayName("존재하는 음료일 경우 예외가 발생한다.")
        void exception() {
            machine.increase(new Beverage(SPRITE, 2000));

            assertThrows(SameBeverageException.class, () -> {
                machine.increase(new Beverage(SPRITE, 2000));
            });
        }
    }

    @Nested
    @DisplayName("decrease 메서드는")
    class Describe_decrease{
        @Test
        @DisplayName("음료를 제거한다.")
        void decrease() {
            machine.increase(new Beverage(COLA, 2500));
            machine.decrease(new Beverage(COLA, 2500));
            assertThat(machine.getBeverages().getSize()).isEqualTo(0);
        }

        @Test
        @DisplayName("제거할 음료가 없다면 예외가 발생한다.")
        void exception() {
            assertThrows(BeverageNotFoundException.class, () -> {
                machine.decrease(new Beverage(SPRITE, 2000));
            });
        }
    }
}