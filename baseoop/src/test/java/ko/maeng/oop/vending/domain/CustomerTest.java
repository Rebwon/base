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
                new Beverage(COLA, Money.wons(1500)),
                new Beverage(SPRITE, Money.wons(1000)),
                new Beverage(LEMONADE, Money.wons(2500)),
                new Beverage(WATER, Money.wons(800))
        ));
        vendingMachine = new VendingMachine();
        vendingMachine.addPrice(Money.wons(10000));
        vendingMachine.addBeverages(beverageList);
    }

    @Test
    void buyBeverage() {
        Customer customer = new Customer(Money.wons(5000));
        customer.putPrice(vendingMachine);
        customer.buy(vendingMachine, new Beverage(COLA, Money.wons(1500)));

        assertThat(vendingMachine.getCustomerPrice()).isEqualTo("3500원");
        assertThat(vendingMachine.getBeverages().getSize()).isEqualTo(3);
    }

    @Test
    void doNotInsertMinusPrice() {
        Customer customer = new Customer(Money.wons(-1500));
        assertThrows(IllegalArgumentException.class, () -> {
            customer.putPrice(vendingMachine);
        });
    }

    @Test
    void doNotNonBeverageSelect() {
        Customer customer = new Customer(Money.wons(5000));
        customer.putPrice(vendingMachine);

        assertThrows(BeverageNotFoundException.class, () -> {
            customer.buy(vendingMachine, new Beverage(COLA, Money.wons(2000)));
        });
    }
}