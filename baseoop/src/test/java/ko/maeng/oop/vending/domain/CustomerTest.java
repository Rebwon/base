package ko.maeng.oop.vending.domain;

import ko.maeng.oop.vending.domain.exception.BeverageNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static ko.maeng.oop.vending.domain.BeverageType.*;
import static ko.maeng.oop.vending.domain.BeverageType.WATER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerTest {
    private BeverageList beverageList;
    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        beverageList = new BeverageList();
        beverageList.addBeverages(asList(
                new Beverage(COLA, "1500"),
                new Beverage(SPRITE, "1000"),
                new Beverage(LEMONADE, "2500"),
                new Beverage(WATER, "800")
        ));
        vendingMachine = new VendingMachine();
        vendingMachine.addPrice("30000");
        vendingMachine.addBeverages(beverageList);
    }

    @Test
    void buyBeverage() {
        Customer customer = new Customer("5000");
        customer.insertPrice(vendingMachine);
        customer.buy(vendingMachine, new Beverage(COLA, "1500"));

        assertThat(vendingMachine.getCustomerPrice()).isEqualTo("3500");
        assertThat(vendingMachine.getBeverages().getSize()).isEqualTo(3);
    }

    @Test
    void doNotInsertMinusPrice() {
        Customer customer = new Customer("-5000");
        assertThrows(IllegalArgumentException.class, () -> {
            customer.insertPrice(vendingMachine);
        });
    }

    @Test
    void doNotNonBeverageSelect() {
        Customer customer = new Customer("5000");
        customer.insertPrice(vendingMachine);

        assertThrows(BeverageNotFoundException.class, () -> {
            customer.buy(vendingMachine, new Beverage(COLA, "2000"));
        });
    }
}