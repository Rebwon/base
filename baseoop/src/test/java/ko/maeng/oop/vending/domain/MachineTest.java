package ko.maeng.oop.vending.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static ko.maeng.oop.vending.domain.Beverage.BeverageType.*;
import static org.assertj.core.api.Assertions.assertThat;

class MachineTest {
    private Machine machine;
    private MachineManager manager;
    private BeverageList beverages;

    @BeforeEach
    void setUp() {
        List<Beverage> beverageList = asList(
                new Beverage(COLA, 2500),
                new Beverage(LEMONADE, 3000),
                new Beverage(SPRITE, 1500)
        );
        machine = new Machine();
        manager = new MachineManager(50000);
        beverages = new BeverageList(beverageList);
        manager.addBeverages(beverages);
    }

    @Test
    void supply() {
        manager.supplyMoney(machine);
        manager.supplyBeverages(machine);

        assertThat(machine.getPrice()).isEqualTo(50000);
        assertThat(machine.getBeverages()).isNotNull();
    }

    @Test
    void findOne() {
        manager.supplyBeverages(machine);

        assertThat(machine.findOne(new Beverage(COLA, 2500))).isNotNull();
    }
}