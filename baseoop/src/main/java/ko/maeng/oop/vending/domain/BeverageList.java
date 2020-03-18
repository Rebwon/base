package ko.maeng.oop.vending.domain;

import java.util.ArrayList;
import java.util.List;

public class BeverageList {
    private final List<Beverage> beverages = new ArrayList<>();

    public void addBeverages(List<Beverage> beverageList) {
        beverages.addAll(beverageList);
    }

    public void increase(Beverage beverage) {
        beverages.add(beverage);
    }

    public void decrease(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public int getSize() {
        return beverages.size();
    }

    @Override
    public String toString() {
        return "BeverageList{" +
                "beverages=" + beverages +
                '}';
    }

    public boolean isBeverage(Beverage is) {
        return beverages.stream()
                .anyMatch(beverage -> beverage.equals(is));
    }
}
