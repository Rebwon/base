package ko.maeng.oop.vending.domain;

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
                new Beverage(COLA, "1500"),
                new Beverage(SPRITE, "1000"),
                new Beverage(LEMONADE, "2500"),
                new Beverage(WATER, "800")
        ));
    }

    @Test
    void getTotalPrice() {
        VendingMachine machine = new VendingMachine();
        machine.addPrice("30000");

        assertThat(machine.getTotalPrice()).isEqualTo("30000");
    }

    @Test
    void getBeverageList() {
        VendingMachine machine = new VendingMachine();
        machine.addBeverages(beverageList);

        assertThat(machine.getBeverages()).isNotNull();
    }

    @Test
    void buyBeverage() {
        VendingMachine machine = new VendingMachine();
        machine.addBeverages(beverageList);
        machine.addPrice("30000");

        machine.buyBeverage(new Beverage(COLA, "1500"));

        assertThat(machine.getTotalPrice()).isEqualTo("31500");
        System.out.println(machine.getBeverages().toString());
    }
}