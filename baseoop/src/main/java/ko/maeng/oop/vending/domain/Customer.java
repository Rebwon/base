package ko.maeng.oop.vending.domain;

public class Customer {
    private String price;
    private BeverageList beverageList;

    public Customer(String price) {
        this.price = price;
    }

    public void buy(VendingMachine vendingMachine, Beverage beverage) {
        beverageList = new BeverageList();
        vendingMachine.buyBeverage(beverage);
        beverageList.addBeverage(beverage);
    }

    public void insertPrice(VendingMachine vendingMachine) {
        vendingMachine.addCustomerPrice(this.price);
    }
}
