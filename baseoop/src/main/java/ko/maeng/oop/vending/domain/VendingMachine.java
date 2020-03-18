package ko.maeng.oop.vending.domain;

import ko.maeng.oop.vending.domain.exception.BeverageNotFoundException;

public class VendingMachine {
    private BeverageList beverages;
    private String totalPrice;
    private String customerPrice;

    public void addPrice(String price) {
        this.totalPrice += price;
    }

    public BeverageList getBeverages() {
        return beverages;
    }

    public int getTotalPrice() {
        return Integer.parseInt(this.totalPrice);
    }

    public int getCustomerPrice() {
        return Integer.parseInt(this.customerPrice);
    }

    public void addBeverages(BeverageList beverageList) {
        this.beverages = beverageList;
    }

    public void buyBeverage(Beverage beverage) {
        if(beverages.isBeverage(beverage)) {
            beverages.decrease(beverage);
        }
        throw new BeverageNotFoundException("음료 목록에 없는 음료입니다.");
    }

    public void addCustomerPrice(String price) {
        if(Integer.parseInt(price) < 0) {
            throw new IllegalArgumentException("음수값의 돈을 넣을 수 없습니다.");
        }
        this.customerPrice += price;
    }
}
