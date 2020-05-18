package ko.maeng.oop.vending.domain;

import java.util.Objects;

public class Beverage {
    private BeverageType beverageType;
    private Money price;

    public Beverage(BeverageType beverageType, Money price) {
        this.beverageType = beverageType;
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beverage beverage = (Beverage) o;
        return Objects.equals(beverageType.getName(), beverage.beverageType.getName()) &&
                Objects.equals(price, beverage.price);
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
