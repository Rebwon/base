package ko.maeng.oop.vending.domain;

public class Customer {
    private Money price;
    private BeverageList beverageList;

    public Customer(Money price) {
        this.price = price;
        this.beverageList = new BeverageList();
    }

    public void buy(VendingMachine vendingMachine, Beverage beverage) {
        vendingMachine.buyBeverage(beverage);
        beverageList.addBeverage(beverage);
    }

    public void putPrice(VendingMachine vendingMachine) {
        vendingMachine.addCustomerPrice(price);
    }
}
