package ko.maeng.oop.vending.domain;

import java.util.Objects;

public class Beverage {
    public enum BeverageType{COLA,SPRITE,LEMONADE,WATER}

    private BeverageType beverageType;
    private int price;

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
                beverageType == beverage.beverageType;
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
