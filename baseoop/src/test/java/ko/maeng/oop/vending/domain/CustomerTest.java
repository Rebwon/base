package ko.maeng.oop.vending.domain;

import ko.maeng.oop.vending.domain.exception.BeverageNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static ko.maeng.oop.vending.domain.BeverageType.*;
import static ko.maeng.oop.vending.domain.BeverageType.WATER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Customer 클래스")
class CustomerTest {
    private BeverageList beverageList = new BeverageList();
    private VendingMachine vendingMachine = new VendingMachine();

    @BeforeEach
    void setUp() {
        beverageList.addBeverages(asList(
                new Beverage(COLA, Money.wons(1500)),
                new Beverage(SPRITE, Money.wons(1000)),
                new Beverage(LEMONADE, Money.wons(2500)),
                new Beverage(WATER, Money.wons(800))
        ));
        vendingMachine.addPrice(Money.wons(10000));
        vendingMachine.addBeverages(beverageList);
    }

    @DisplayName("구매자가 음료를 구매한다.")
    @Test
    void buyBeverage() {
        Customer customer = new Customer(Money.wons(5000), vendingMachine);
        customer.putPriceInVendingMachine();
        customer.buy(new Beverage(COLA, Money.wons(1500)));

        assertThat(vendingMachine.getCustomerPrice()).isEqualTo(Money.wons(3500));
        assertThat(customer.getBeverageListSize()).isEqualTo(1);
    }

    @DisplayName("구매자는 음수값의 돈을 자판기에 넣을 수 없다.")
    @Test
    void doNotInsertMinusPrice() {
        Customer customer = new Customer(Money.wons(-1500), vendingMachine);
        assertThrows(IllegalArgumentException.class,
                () -> customer.putPriceInVendingMachine());
    }

    @DisplayName("구매자는 없는 음료를 구매할 수 없다.")
    @Test
    void doNotNonBeverageSelect() {
        Customer customer = new Customer(Money.wons(5000), vendingMachine);
        customer.putPriceInVendingMachine();
        assertThrows(BeverageNotFoundException.class,
                () -> customer.buy(new Beverage(COLA, Money.wons(2000))));
    }
}