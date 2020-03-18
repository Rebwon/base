package ko.maeng.oop.vending.domain;

import java.util.Objects;

public class Beverage {
    private BeverageType beverageType;
    private String price;

    public Beverage(BeverageType beverageType, String price) {
        this.beverageType = beverageType;
        this.price = price;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        Beverage beverage = (Beverage) o;
        return price.equals(beverage.price) &&
                beverageType.getName().equals(beverage.beverageType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(beverageType, price);
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "beverageType=" + beverageType +
                ", price=" + price +
                '}';
    }
}
