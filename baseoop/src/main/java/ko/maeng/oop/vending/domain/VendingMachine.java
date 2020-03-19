package ko.maeng.oop.vending.domain;

import ko.maeng.oop.vending.domain.exception.BeverageNotFoundException;

public class VendingMachine {
    private BeverageList beverages;
    private Money totalPrice = Money.ZERO;
    private Money customerPrice = Money.ZERO;

    public void addPrice(Money price) {
        this.totalPrice = this.totalPrice.plus(price);
    }

    public BeverageList getBeverages() {
        return beverages;
    }

    public int getBeveragesSize() {
        return beverages.getSize();
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Money getCustomerPrice() {
        return customerPrice;
    }

    public void addBeverages(BeverageList beverageList) {
        this.beverages = beverageList;
    }

    public void buyBeverage(Beverage beverage) {
        if(!beverages.isBeverage(beverage)) throw new BeverageNotFoundException("음료 목록에 없는 음료입니다.");
        beverages.decrease(beverage);
        this.customerPrice = customerPrice.minus(beverage.getPrice());
    }

    public void putCustomerPrice(Money price) {
        if(price.longValue() < 0) {
            throw new IllegalArgumentException("음수값의 돈을 넣을 수 없습니다.");
        }
        this.customerPrice = customerPrice.plus(price);
    }
}
