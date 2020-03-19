package ko.maeng.oop.vending.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static ko.maeng.oop.vending.domain.BeverageType.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VendingMachine 클래스")
class VendingMachineTest {
    private BeverageList beverageList = new BeverageList();

    @BeforeEach
    void setUp() {
        beverageList.addBeverages(asList(
                new Beverage(COLA, Money.wons(1500)),
                new Beverage(SPRITE, Money.wons(1000)),
                new Beverage(LEMONADE, Money.wons(2500)),
                new Beverage(WATER, Money.wons(800))
        ));
    }

    @AfterEach
    void tearDown() {
        beverageList.clear();
    }

    @DisplayName("음료 자판기에 초기 금액을 넣는다.")
    @Test
    void addPrice() {
        VendingMachine machine = new VendingMachine();
        machine.addPrice(Money.wons(10000));

        assertThat(machine.getTotalPrice()).isEqualTo(Money.wons(10000));
    }

    @DisplayName("음료 자판기에 음료를 제공한다.")
    @Test
    void addBeverages() {
        VendingMachine machine = new VendingMachine();
        machine.addBeverages(beverageList);

        assertThat(machine.getBeveragesSize()).isEqualTo(4);
    }

    @DisplayName("음료 자판기에서 음료를 구매한다.")
    @Test
    void buyBeverage() {
        VendingMachine machine = new VendingMachine();
        machine.addBeverages(beverageList);

        machine.buyBeverage(new Beverage(COLA, Money.wons(1500)));

        assertThat(machine.getBeveragesSize()).isEqualTo(3);
    }
}