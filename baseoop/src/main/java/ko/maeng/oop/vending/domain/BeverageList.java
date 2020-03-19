package ko.maeng.oop.vending.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BeverageList {
    private final List<Beverage> beverages = new ArrayList<>();

    public void addBeverages(List<Beverage> beverageList) {
        beverages.addAll(beverageList);
    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public void increase(Beverage beverage) {
        beverages.add(beverage);
    }

    public void decrease(Beverage beverage) {
        beverages.remove(beverage);
    }

    public int getSize() {
        return beverages.size();
    }

    public boolean isBeverage(Beverage is) {
        return beverages.stream()
                .anyMatch(beverage -> beverage.equals(is));
    }

    public void clear() {
        beverages.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof BeverageList)) return false;
        BeverageList that = (BeverageList) o;
        return Objects.equals(beverages.toString(), that.beverages.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(beverages);
    }

    @Override
    public String toString() {
        return "BeverageList{" +
                "beverages=" + beverages +
                '}';
    }
}
