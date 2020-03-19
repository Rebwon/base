package ko.maeng.oop.vending.domain;

public class Customer {
    private Money price;
    private BeverageList beverageList;
    private VendingMachine machine;

    public Customer(Money price, VendingMachine machine) {
        this.price = price;
        this.machine = machine;
        this.beverageList = new BeverageList();
    }

    public Money getPrice() {
        return price;
    }

    public BeverageList getBeverageList() {
        return beverageList;
    }

    public int getBeverageListSize() {
        return beverageList.getSize();
    }

    public void buy(Beverage beverage) {
        machine.buyBeverage(beverage);
        beverageList.addBeverage(beverage);
    }

    public void putPriceInVendingMachine() {
        machine.putCustomerPrice(price);
    }
}
