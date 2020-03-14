package ko.maeng.oop.vending.domain;

import java.util.Objects;

public class Beverage {
    public enum BeverageType{COLA,SPRITE,LEMONADE,WATER}

    private final BeverageType beverageType;
    private final int price;

    public Beverage(BeverageType beverageType, int price) {
        this.beverageType = beverageType;
        this.price = price;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beverage beverage = (Beverage) o;
        return price == beverage.price &&
                beverageType.equals(beverage.beverageType);
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
