package ko.maeng.oop.vending.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static ko.maeng.oop.vending.domain.BeverageType.*;
import static org.assertj.core.api.Assertions.assertThat;

class VendingMachineTest {
    private BeverageList beverageList;

    @BeforeEach
    void setUp() {
        beverageList = new BeverageList();
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

    @Test
    void getTotalPrice() {
        VendingMachine machine = new VendingMachine();
        machine.addPrice(Money.wons(10000));

        assertThat(machine.getTotalPrice()).isEqualTo("10000원");
    }

    @Test
    void getBeverageList() {
        VendingMachine machine = new VendingMachine();
        machine.addBeverages(beverageList);

        assertThat(machine.getBeverages().getSize()).isEqualTo(4);
    }

    @Test
    void buyBeverage() {
        VendingMachine machine = new VendingMachine();
        machine.addBeverages(beverageList);
        machine.addPrice(Money.wons(10000));

        machine.buyBeverage(new Beverage(COLA, Money.wons(1500)));

        assertThat(machine.getTotalPrice()).isEqualTo("10000원");
        assertThat(machine.getBeverages().getSize()).isEqualTo(3);
    }
}