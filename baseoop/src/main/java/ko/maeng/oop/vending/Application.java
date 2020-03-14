package ko.maeng.oop.vending;

import ko.maeng.oop.vending.domain.Customer;
import ko.maeng.oop.vending.domain.Machine;

public class Application {
    public static void main(String[] args) {
        Customer customer = new Customer(10000);
        Machine machine = new Machine();
    }
}
