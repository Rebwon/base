package ko.maeng.oop.vending.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1000);
    }

    @Test
    void putInMachineMoney() {
    }
}